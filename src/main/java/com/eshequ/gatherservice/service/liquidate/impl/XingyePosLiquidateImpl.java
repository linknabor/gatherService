package com.eshequ.gatherservice.service.liquidate.impl;

import com.eshequ.gatherservice.constant.LiquidateConfigConstant;
import com.eshequ.gatherservice.exception.BaseException;
import com.eshequ.gatherservice.mapper.LiquidateMapper;
import com.eshequ.gatherservice.service.liquidate.LiquidateService;
import com.eshequ.gatherservice.service.liquidate.vo.LiquidateData;
import com.eshequ.gatherservice.service.liquidate.vo.Mch_info;
import com.eshequ.gatherservice.service.liquidate.vo.xingyePos.*;
import com.eshequ.gatherservice.util.DateUtils;
import com.eshequ.gatherservice.util.PrimayKeyUtils;
import com.eshequ.gatherservice.util.SignUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 兴业POS刷卡清算
 * Created by jackeie on 2018.05.14
 */
@Service(value = "xingyePosliquidateService")
@RestController
public class XingyePosLiquidateImpl implements LiquidateService{

    private static Logger LOGGER = LoggerFactory.getLogger(XingyePosLiquidateImpl.class);

    @Autowired
    private LiquidateMapper liquidateMapper;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List pullLiquidateFile(String liquidate_date) {
        List<Mch_info> listMch =  liquidateMapper.getLiquidateMch(LiquidateConfigConstant.XingyePos, liquidate_date, LiquidateConfigConstant.XingyePosPayMethod);
        for (int i = 0; i < listMch.size(); i++) {
            Mch_info mch_info = listMch.get(i);
            if (mch_info==null || StringUtils.isEmpty(mch_info.getMch_id()) || "0".equals(mch_info.getMch_id())) {
                continue;
            }
            try {
                //1.商户号绑定
                bindMch(mch_info.getMch_id());

                //2.获取明细
                List<AcctDetailTrade> listDetail = getTradeDetail(mch_info);

                //3.转化成标准格式
                List<LiquidateData> listPubData = new ArrayList<LiquidateData>();
                for (int k =0; k< listDetail.size(); k++) {
                    LiquidateData data = new LiquidateData();
                    AcctDetailTrade detail = listDetail.get(k);
                    data.setTradeWaterId(detail.getTxn_id());
                    data.setTramAmt(new BigDecimal(detail.getTxn_amt()));
                    data.setRateAmt(new BigDecimal(detail.getFee_amt()));
                    data.setTranStatus(detail.getTxn_status());
                    listPubData.add(data);
                }
                //4.清算

            } catch (Exception e) {
                LOGGER.error("bind mch_id error， mch_id ：" + mch_info.getMch_id(), e);
            }
        }
        return null;
    }

    /**
     * 绑定商户号
     * @param mch_id
     * @throws Exception
     */
    public void bindMch(String mch_id) throws Exception {
        Head head = new Head();
        head.setVersion(LiquidateConfigConstant.XingyePosVersion);
        head.setSrc_sys_id(LiquidateConfigConstant.XingyePosSrcSysId);
        head.setReq_sys_id(LiquidateConfigConstant.XingyePosReqSysId);
        head.setDes_sys_id(LiquidateConfigConstant.XingyePosDesSysId);
        head.setTxn_date(DateUtils.getCurrentTimeStr("yyyyMMdd"));
        head.setTxn_time(DateUtils.getCurrentTimeStr("HHmmss"));
        head.setTxn_num(LiquidateConfigConstant.XingyePosTypeBindMch);	//具体交易号
        head.setNonce_str(PrimayKeyUtils.getRandomBy16());
        head.setTxn_ssn(PrimayKeyUtils.buildRandom());	//流水号，全系统唯一。如果一次查询有多页，每次需要传不同的txn_ssn号

        ReqBody reqBody = new ReqBody();
        reqBody.setMer_agent_code(LiquidateConfigConstant.XingyePosChannelNo);	//渠道编号
        reqBody.setMer_inner_code(mch_id); //商户编号

        XingyePosLiquidateReq<ReqBody> req = new XingyePosLiquidateReq<ReqBody>();
        req.setHead(head);
        req.setReq_body(reqBody);
        getPostToXingYe(req);
    }

    private List<AcctDetailTrade> getTradeDetail(Mch_info mch_info) throws Exception {

        Head head = new Head();
        head.setVersion(LiquidateConfigConstant.XingyePosVersion);
        head.setSrc_sys_id(LiquidateConfigConstant.XingyePosSrcSysId);
        head.setReq_sys_id(LiquidateConfigConstant.XingyePosReqSysId);
        head.setDes_sys_id(LiquidateConfigConstant.XingyePosDesSysId);
        head.setTxn_date(DateUtils.getCurrentTimeStr("yyyyMMdd"));
        head.setTxn_time(DateUtils.getCurrentTimeStr("HHmmss"));
        head.setTxn_num(LiquidateConfigConstant.XingyePosTypeQueryDetail);	//具体交易号
        head.setNonce_str(PrimayKeyUtils.getRandomBy16());

        List<AcctDetailTrade> tradeList = new ArrayList<AcctDetailTrade>();

		/*请求兴业获取数据*/
        int beginPage = 1;
        while(true){

            ReqBody reqBody = new ReqBody();
            reqBody.setMer_inner_code(mch_info.getMch_id());	//商户编号
            reqBody.setQuery_start_date(mch_info.getAcct_date()); //查询起始日期
            reqBody.setQuery_end_date(mch_info.getAcct_date()); //查询结束日期
            reqBody.setPage_size(LiquidateConfigConstant.XingyePosQueryPageSize); //每页条数
            reqBody.setCurrent_page(String.valueOf(beginPage));	//当前页数
            head.setTxn_ssn(PrimayKeyUtils.buildRandom());	//流水号，全系统唯一。如果一次查询有多页，每次需要传不同的txn_ssn号

            XingyePosLiquidateReq<ReqBody> req = new XingyePosLiquidateReq<ReqBody>();

            req.setHead(head);
            req.setReq_body(reqBody);
            XingyePosLiquidateResp<AcctDetailTrade> resp = getPostToXingYe(req);

            RespBody<AcctDetailTrade> respBody = (RespBody<AcctDetailTrade>) resp.getRsp_body();
            String total_size = respBody.getTotal_size();
            String current_page = respBody.getCurrent_page();

            if (respBody.getTrade()!=null) {
                tradeList.addAll(respBody.getTrade());
            }

            if (respBody.getTrade()==null) {
                break;
            }

            if (current_page.equals(total_size)) {
                break;
            }

            beginPage++;

        }
        return tradeList;
    }

    /**
     * 发送POST请求
     * @param req
     * @return
     * @throws Exception
     */
    private XingyePosLiquidateResp getPostToXingYe(XingyePosLiquidateReq req) throws Exception {

        String json = objectMapper.writeValueAsString(req);
        LOGGER.debug("request bind mchId is context :"+json);
        String content = json.replace("head", "HEAD").replace("req_body", "REQ_BODY");

		/*对发送内容进行签名*/
        String sign = SignUtil.signByKeyPath(content, LiquidateConfigConstant.XingyePosPriKeyPath, LiquidateConfigConstant.INPUT_CHARSET);

        String sendStr = content+"&SIGN="+sign.replace("\r\n", "").replace("\n", "").replace("\r", "");

        String responseStr = restTemplate.postForEntity(LiquidateConfigConstant.XingyePosRequestUrl, sendStr, String.class).getBody();
        LOGGER.debug("xing ye balance response : " + responseStr);

        if (responseStr == null) {
            throw new BaseException("未接收到服务器返回信息。");
        }

		/*
		 * 1.这里直接转DTO取head中的返回码，如果交易未成功直接抛异常，不需要后面的业务类做处理，因为对应的业务类可能有多个，每个都写相同的判断，无意义
		 * 2.没有成功的业务不做签名校验，因为兴业返回的未成功交易签名校验不了（兴业的BUG）。成功的交易业务处理类自行校验签名，校验签名调用公共函数即可。
		 */
        XingyePosLiquidateResp<?> dto = formateResp(responseStr);
        Head head = dto.getHead();
        String resp_code = head.getResp_code();
        String resp_msg = head.getResp_msg();

        if (!LiquidateConfigConstant.XingyePosSuccess.equals(resp_code)) {
            throw new BaseException(resp_msg);
        }
        return dto;
    }

    /**
     * 格式化response字串为对象
     * @param responseStr
     * @return
     */
    private XingyePosLiquidateResp<?> formateResp(String responseStr) throws IOException {

        int signIndex = responseStr.indexOf("&SIGN=");
        if (signIndex <=0) {
            throw new BaseException("响应主体内容不合法，存在错误信息");
        }
        String originData = responseStr.substring(0, signIndex);
        String respSignStr = responseStr.substring(signIndex+1, responseStr.length());
        String[]signArr = respSignStr.split("=");
        String respSign = signArr[1];
        String formattedData = originData.replaceAll("\r|\n", "");

        formattedData = formattedData.replace("HEAD", "head").replace("RSP_BODY", "rsp_body");

        XingyePosLiquidateResp<?> dto = objectMapper.readValue(formattedData, XingyePosLiquidateResp.class);
        dto.setSignature(respSign);
        dto.setFormattedData(formattedData);
        dto.setOriginData(originData);
        return dto;
    }

}
