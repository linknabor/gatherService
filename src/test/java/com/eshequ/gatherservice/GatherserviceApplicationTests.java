package com.eshequ.gatherservice;

import com.eshequ.gatherservice.constant.LiquidateConfigConstant;
import com.eshequ.gatherservice.exception.BaseException;
import com.eshequ.gatherservice.mapper.LiquidateMapper;
import com.eshequ.gatherservice.service.liquidate.LiquidateService;
import com.eshequ.gatherservice.service.liquidate.impl.XingyePosLiquidateImpl;
import com.eshequ.gatherservice.service.liquidate.vo.Mch_info;
import com.eshequ.gatherservice.service.liquidate.vo.xingyePos.Head;
import com.eshequ.gatherservice.service.liquidate.vo.xingyePos.ReqBody;
import com.eshequ.gatherservice.service.liquidate.vo.xingyePos.XingyePosLiquidateReq;
import com.eshequ.gatherservice.service.liquidate.vo.xingyePos.XingyePosLiquidateResp;
import com.eshequ.gatherservice.util.DateUtils;
import com.eshequ.gatherservice.util.PrimayKeyUtils;
import com.eshequ.gatherservice.util.SignUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GatherserviceApplicationTests {

	@Test
	public void contextLoads() throws Exception {

	}

}
