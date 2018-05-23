package com.eshequ.gatherservice.mapper;

import com.eshequ.gatherservice.service.liquidate.vo.Mch_info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LiquidateMapper {

    //根据清算日期，获取待清算的交易
    List<Mch_info> getLiquidateMch(@Param("plat_channel") String plat_channel, @Param("liquidate_date") String liquidate_date, @Param("payMethods") String[] payMethods);

    String getTradeByVoucherNo(@Param("voucher_no") String voucher_no);

}
