package com.eshequ.gatherservice.service.liquidate.vo.xingyePos;

import java.io.Serializable;

public class XingyePosLiquidateReq <T> implements Serializable {
    private Head head;

    private T req_body;	//目前来看两个交易的req_body几乎是相同的，这里用泛型，方便以后增加类型

    public Head getHead() {
        return head;
    }

    public void setHead(Head head) {
        this.head = head;
    }

    public T getReq_body() {
        return req_body;
    }

    public void setReq_body(T req_body) {
        this.req_body = req_body;
    }
}
