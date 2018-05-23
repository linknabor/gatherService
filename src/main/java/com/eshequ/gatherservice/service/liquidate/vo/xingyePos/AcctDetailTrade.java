package com.eshequ.gatherservice.service.liquidate.vo.xingyePos;

import java.io.Serializable;

public class AcctDetailTrade implements Serializable{
    private String txn_currency; //交易币种
    private String distribution_currency; //清算币种
    private String term_id; //终端编号
    private String term_batch_no; //终端批次号
    private String txn_date; //交易日期
    private String txn_time; //交易时间
    private String acct_no; //交易卡号/账号
    private String retrivl_ref; //交易参考号
    private String settle_date; //清算日期
    private String mer_inner_code; //商户编号
    private String mer_name_ch; //商户中文名称
    private String term_ssn; //凭证号
    private String txn_amt; //交易金额
    private String txn_status; //入账状态
    private String distribution_date; //实际入账日期
    private String distribution_amt; //实际入账金额
    private String txn_type; //交易类型

    private String txn_ssn; //渠道交易流水号
    private String txn_id; //交易ID
    private String fee_amt; //应收手续费
    private String discount_fee_amt; //手续费优惠

    public String getTxn_currency() {
        return txn_currency;
    }

    public void setTxn_currency(String txn_currency) {
        this.txn_currency = txn_currency;
    }

    public String getDistribution_currency() {
        return distribution_currency;
    }

    public void setDistribution_currency(String distribution_currency) {
        this.distribution_currency = distribution_currency;
    }

    public String getTerm_id() {
        return term_id;
    }

    public void setTerm_id(String term_id) {
        this.term_id = term_id;
    }

    public String getTerm_batch_no() {
        return term_batch_no;
    }

    public void setTerm_batch_no(String term_batch_no) {
        this.term_batch_no = term_batch_no;
    }

    public String getTxn_date() {
        return txn_date;
    }

    public void setTxn_date(String txn_date) {
        this.txn_date = txn_date;
    }

    public String getTxn_time() {
        return txn_time;
    }

    public void setTxn_time(String txn_time) {
        this.txn_time = txn_time;
    }

    public String getAcct_no() {
        return acct_no;
    }

    public void setAcct_no(String acct_no) {
        this.acct_no = acct_no;
    }

    public String getRetrivl_ref() {
        return retrivl_ref;
    }

    public void setRetrivl_ref(String retrivl_ref) {
        this.retrivl_ref = retrivl_ref;
    }

    public String getSettle_date() {
        return settle_date;
    }

    public void setSettle_date(String settle_date) {
        this.settle_date = settle_date;
    }

    public String getMer_inner_code() {
        return mer_inner_code;
    }

    public void setMer_inner_code(String mer_inner_code) {
        this.mer_inner_code = mer_inner_code;
    }

    public String getMer_name_ch() {
        return mer_name_ch;
    }

    public void setMer_name_ch(String mer_name_ch) {
        this.mer_name_ch = mer_name_ch;
    }

    public String getTerm_ssn() {
        return term_ssn;
    }

    public void setTerm_ssn(String term_ssn) {
        this.term_ssn = term_ssn;
    }

    public String getTxn_amt() {
        return txn_amt;
    }

    public void setTxn_amt(String txn_amt) {
        this.txn_amt = txn_amt;
    }

    public String getTxn_status() {
        return txn_status;
    }

    public void setTxn_status(String txn_status) {
        this.txn_status = txn_status;
    }

    public String getDistribution_date() {
        return distribution_date;
    }

    public void setDistribution_date(String distribution_date) {
        this.distribution_date = distribution_date;
    }

    public String getDistribution_amt() {
        return distribution_amt;
    }

    public void setDistribution_amt(String distribution_amt) {
        this.distribution_amt = distribution_amt;
    }

    public String getTxn_type() {
        return txn_type;
    }

    public void setTxn_type(String txn_type) {
        this.txn_type = txn_type;
    }

    public String getTxn_ssn() {
        return txn_ssn;
    }

    public void setTxn_ssn(String txn_ssn) {
        this.txn_ssn = txn_ssn;
    }

    public String getTxn_id() {
        return txn_id;
    }

    public void setTxn_id(String txn_id) {
        this.txn_id = txn_id;
    }

    public String getFee_amt() {
        return fee_amt;
    }

    public void setFee_amt(String fee_amt) {
        this.fee_amt = fee_amt;
    }

    public String getDiscount_fee_amt() {
        return discount_fee_amt;
    }

    public void setDiscount_fee_amt(String discount_fee_amt) {
        this.discount_fee_amt = discount_fee_amt;
    }
}
