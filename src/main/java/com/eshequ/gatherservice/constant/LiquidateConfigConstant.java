package com.eshequ.gatherservice.constant;

public class LiquidateConfigConstant {

    public final static String INPUT_CHARSET = "UTF-8";

    /* 兴业上线支付清算配置 start */
    public static final String swiftPassDownloadUrl = ""; //威富通清算文件请求URL
    public static final String swiftPassDownloadService = ""; //
    public static final String Swiftpass = "1"; //威富通
    public static final String[] SwiftPassPayMethod = new String[]{"04","05","06","07","08","09"}; //兴业线上支付支付方式
    /* 兴业上线支付清算配置 end */

    /* 兴业POS刷卡清算配置 start */
    public static final String XingyePosRequestUrl = "http://43.254.150.86:7710/unify"; //"http://43.254.150.86:7710/SHNB";
    public static final String XingyePosSuccess = "00";
    public static final String XingyePosChannelNo = "BUSICHNL0100000003"; //渠道商编码
    public static final String XingyePos = "Z"; //兴业银联刷卡
    public static final String[] XingyePosPayMethod = new String[]{"01"}; //兴业POS刷卡支付方式

    public static final String XingyePosVersion = "1.0.0";
    public static final String XingyePosSrcSysId = "SHNB";
    public static final String XingyePosReqSysId = "SHNB";
    public static final String XingyePosDesSysId = "MMSP";
    public static final String XingyePosTypeQueryDetail = "A11010"; //获取交易明细
    public static final String XingyePosTypeBindMch = "A11012"; //绑定商户号

    public static final String XingyePosQueryPageSize = "50"; //每页记录数
    public static final String XingyePosPriKeyPath =  "d:/key/prikey.txt";
    public static final String XingyePosPubKeyPath = "d:/key/publicKey_shnb.keystore";

    /* 兴业POS刷卡清算配置 end */

    /* 汇付刷卡清算配置 start*/
    public static final String HuiFuPosFtpAddress = "www.e-shequ.com"; //FTP地址
    public static final int HuiFuPosFtpPort = 21; //FTP端口
    public static final String HuiFuPosFtpUserName = "ftp-huifu"; //FTP用户名
    public static final String HuiFuPosFtpPassword = "Abcd1234"; //FTP密码
    public static final String HuiFuPosFtpBasePath = "/ftp-huifu"; //FTP汇付根目录
    public static final String HuiFuPosDownloadPath = "d:/huifu/data/"; //文件下载本地路径
    public static final String HuiFuPosSUCCESS = "成功";
    /* 汇付刷卡清算配置 end*/
}
