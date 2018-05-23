package com.eshequ.gatherservice.service.liquidate.vo;

public class Mch_info {
    private String mch_id;
    private String secret;
    private String acct_date;

    public String getMch_id() {
        return mch_id;
    }

    public void setMch_id(String mch_id) {
        this.mch_id = mch_id;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getAcct_date() {
        return acct_date;
    }

    public void setAcct_date(String acct_date) {
        this.acct_date = acct_date;
    }
}
