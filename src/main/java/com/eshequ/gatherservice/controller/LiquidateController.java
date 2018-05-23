package com.eshequ.gatherservice.controller;

import com.eshequ.gatherservice.service.liquidate.LiquidateService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LiquidateController {

    @Autowired
    private LiquidateService xingyeOnLineliquidateService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LiquidateService huifuLiquidateService;

    @ResponseBody
    @RequestMapping(value = "/a")
    public void a() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("device_id", "N7NL00373531");
        map.put("sect_id", "180323100350980269");
        map.put("build_id", "0");
        map.put("unit_id", "180323100350987268");
        String ss = restTemplate.postForEntity("https://www.e-shequ.com/shanghai/servplat/fastacct/getHouInfoSDO.do", map, String.class).getBody();
        System.out.println(ss);
    }

    @ResponseBody
    @RequestMapping(value = "/b")
    public void b() {
        huifuLiquidateService.pullLiquidateFile("20180520");
    }
}
