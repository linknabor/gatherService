package com.eshequ.gatherservice.service.liquidate.vo.xingyePos;

import java.io.Serializable;

public class Head implements Serializable {
    private String version;	//版本号
    private String src_sys_id;	//源系统标识
    private String req_sys_id;	//请求系统标识
    private String des_sys_id;	//目标系统标识
    private String group_id;	//法人机构号
    private String txn_inst;	//交易机构号
    private String txn_date;	//交易日期
    private String txn_time;	//交易时间
    private String txn_ssn;		//交易流水号
    private String txn_num;		//交易代码
    private String telr_id;		//操作员代码
    private String nonce_str;	//随机字符串，应答时须重新生成
    private String req_reserved;	//原样返回
    private String resp_code;	//应答码
    private String resp_msg;	//应答描述

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSrc_sys_id() {
        return src_sys_id;
    }

    public void setSrc_sys_id(String src_sys_id) {
        this.src_sys_id = src_sys_id;
    }

    public String getReq_sys_id() {
        return req_sys_id;
    }

    public void setReq_sys_id(String req_sys_id) {
        this.req_sys_id = req_sys_id;
    }

    public String getDes_sys_id() {
        return des_sys_id;
    }

    public void setDes_sys_id(String des_sys_id) {
        this.des_sys_id = des_sys_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getTxn_inst() {
        return txn_inst;
    }

    public void setTxn_inst(String txn_inst) {
        this.txn_inst = txn_inst;
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

    public String getTxn_ssn() {
        return txn_ssn;
    }

    public void setTxn_ssn(String txn_ssn) {
        this.txn_ssn = txn_ssn;
    }

    public String getTxn_num() {
        return txn_num;
    }

    public void setTxn_num(String txn_num) {
        this.txn_num = txn_num;
    }

    public String getTelr_id() {
        return telr_id;
    }

    public void setTelr_id(String telr_id) {
        this.telr_id = telr_id;
    }

    public String getNonce_str() {
        return nonce_str;
    }

    public void setNonce_str(String nonce_str) {
        this.nonce_str = nonce_str;
    }

    public String getReq_reserved() {
        return req_reserved;
    }

    public void setReq_reserved(String req_reserved) {
        this.req_reserved = req_reserved;
    }

    public String getResp_code() {
        return resp_code;
    }

    public void setResp_code(String resp_code) {
        this.resp_code = resp_code;
    }

    public String getResp_msg() {
        return resp_msg;
    }

    public void setResp_msg(String resp_msg) {
        this.resp_msg = resp_msg;
    }
}
