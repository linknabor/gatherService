package com.eshequ.gatherservice.service.liquidate.vo;

import java.io.Serializable;
import java.math.BigDecimal;

public class LiquidateData implements Serializable {

    private String tradeWaterId; //交易ID
    private BigDecimal tramAmt; //交易金额
    private BigDecimal rateAmt; //税率金额
    private BigDecimal rate; //税率
    private String tranStatus; //交易状态
    private String refundStatus; //退款状态

    public String getTradeWaterId() {
        return tradeWaterId;
    }

    public void setTradeWaterId(String tradeWaterId) {
        this.tradeWaterId = tradeWaterId;
    }

    public BigDecimal getTramAmt() {
        return tramAmt;
    }

    public void setTramAmt(BigDecimal tramAmt) {
        this.tramAmt = tramAmt;
    }

    public BigDecimal getRateAmt() {
        return rateAmt;
    }

    public void setRateAmt(BigDecimal rateAmt) {
        this.rateAmt = rateAmt;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getTranStatus() {
        return tranStatus;
    }

    public void setTranStatus(String tranStatus) {
        this.tranStatus = tranStatus;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }
}
