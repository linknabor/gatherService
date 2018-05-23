package com.eshequ.gatherservice.service.liquidate.impl;

import com.eshequ.gatherservice.service.liquidate.LiquidateService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 拉卡拉刷卡清算
 * Created by jackeie on 2018.05.14
 */
@Service(value = "liquidateService")
public class LakalaLiquidateImpl implements LiquidateService {

    @Override
    public List pullLiquidateFile(String liquidate_date) {
        return null;
    }
}
