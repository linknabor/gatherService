package com.eshequ.gatherservice.service.liquidate.vo.xingyePos;

import java.io.Serializable;

public class ReqBody implements Serializable {
    private String mer_agent_code;	//渠道商编号
    private String mer_inner_code;	//商户编号
    private String page_size;	//单页最大记录数
    private String current_page;	//当前页数

    private String query_start_date;
    private String query_end_date;

    public String getMer_agent_code() {
        return mer_agent_code;
    }

    public void setMer_agent_code(String mer_agent_code) {
        this.mer_agent_code = mer_agent_code;
    }

    public String getMer_inner_code() {
        return mer_inner_code;
    }

    public void setMer_inner_code(String mer_inner_code) {
        this.mer_inner_code = mer_inner_code;
    }

    public String getPage_size() {
        return page_size;
    }

    public void setPage_size(String page_size) {
        this.page_size = page_size;
    }

    public String getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(String current_page) {
        this.current_page = current_page;
    }

    public String getQuery_start_date() {
        return query_start_date;
    }

    public void setQuery_start_date(String query_start_date) {
        this.query_start_date = query_start_date;
    }

    public String getQuery_end_date() {
        return query_end_date;
    }

    public void setQuery_end_date(String query_end_date) {
        this.query_end_date = query_end_date;
    }
}
