package com.eshequ.gatherservice.service.liquidate.vo.xingyePos;

import java.io.Serializable;
import java.util.List;

public class RespBody <T> implements Serializable {
    private String page_size;
    private String current_page;
    private String total_size;
    private List<T> trade;

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

    public String getTotal_size() {
        return total_size;
    }

    public void setTotal_size(String total_size) {
        this.total_size = total_size;
    }

    public List<T> getTrade() {
        return trade;
    }

    public void setTrade(List<T> trade) {
        this.trade = trade;
    }
}
