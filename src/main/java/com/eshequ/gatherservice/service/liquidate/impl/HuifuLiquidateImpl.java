package com.eshequ.gatherservice.service.liquidate.impl;

import com.eshequ.gatherservice.constant.LiquidateConfigConstant;
import com.eshequ.gatherservice.mapper.LiquidateMapper;
import com.eshequ.gatherservice.service.liquidate.LiquidateService;
import com.eshequ.gatherservice.service.liquidate.vo.LiquidateData;
import com.eshequ.gatherservice.util.FtpUtil;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 汇付刷卡清算
 * Created by jackeie on 2018.05.14
 */
@Service(value = "huifuLiquidateService")
public class HuifuLiquidateImpl implements LiquidateService {

    private static Logger LOGGER = LoggerFactory.getLogger(HuifuLiquidateImpl.class);

    @Autowired
    private LiquidateMapper liquidateMapper;

    @Override
    public List pullLiquidateFile(String liquidate_date) throws IOException {

        //1.下载文件到本地
        FtpUtil ftp = new FtpUtil(LiquidateConfigConstant.HuiFuPosFtpAddress, LiquidateConfigConstant.HuiFuPosFtpPort,
                LiquidateConfigConstant.HuiFuPosFtpUserName, LiquidateConfigConstant.HuiFuPosFtpPassword, LiquidateConfigConstant.HuiFuPosFtpBasePath);
        //根据清算日期，获取当日的文件
        String getFileName = liquidate_date+"_310000013000000131_ord.csv";
        ftp.downloadFile(getFileName, LiquidateConfigConstant.HuiFuPosDownloadPath);
        LOGGER.info("download file ");
        //2.处理文件，标准化
        List<String> dataList = FileUtils.readLines(new File(LiquidateConfigConstant.HuiFuPosDownloadPath + getFileName), LiquidateConfigConstant.INPUT_CHARSET);
        List<LiquidateData> list = new ArrayList<LiquidateData>();
        for (int i = 0; i < dataList.size(); i++) {
            String[] oldStr = dataList.get(i).split(",");
            String tran_status = oldStr[12].trim();
            String voucherNo = oldStr[3].trim();
            if (!StringUtils.isEmpty(tran_status) && LiquidateConfigConstant.HuiFuPosSUCCESS.equals(tran_status)) {
                LiquidateData data = new LiquidateData();
                String trade_water_id = liquidateMapper.getTradeByVoucherNo(voucherNo);
                data.setTradeWaterId(trade_water_id);
                data.setTramAmt(new BigDecimal(oldStr[8].trim()));
                data.setRateAmt(new BigDecimal(oldStr[9].trim()));
                data.setTranStatus("0");
                list.add(data);
            }
        }
        return list;
    }
}
