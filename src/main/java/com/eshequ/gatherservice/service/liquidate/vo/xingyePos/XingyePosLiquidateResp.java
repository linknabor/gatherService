package com.eshequ.gatherservice.service.liquidate.vo.xingyePos;

import java.io.Serializable;

public class XingyePosLiquidateResp <T> implements Serializable {
    private Head head;

    private RespBody<T> rsp_body;

    private String originData;

    private String formattedData;

    private String signature;

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public RespBody<T> getRsp_body() {
        return rsp_body;
    }

    public void setRsp_body(RespBody<T> rsp_body) {
        this.rsp_body = rsp_body;
    }

    public String getOriginData() {
        return originData;
    }

    public void setOriginData(String originData) {
        this.originData = originData;
    }

    public String getFormattedData() {
        return formattedData;
    }

    public void setFormattedData(String formattedData) {
        this.formattedData = formattedData;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
