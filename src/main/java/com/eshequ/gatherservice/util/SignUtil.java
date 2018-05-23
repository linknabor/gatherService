package com.eshequ.gatherservice.util;

import com.eshequ.gatherservice.constant.LiquidateConfigConstant;

import com.eshequ.gatherservice.exception.BaseException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SignUtil {

    private final static String INPUT_CHARSET = "input_charset";

    /**
     * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
     */
    public static String createSign(Map<?, ?> map, String key) {
        String charset = (String) map.get(INPUT_CHARSET);
        if (StringUtils.isEmpty(charset)) {
            charset = LiquidateConfigConstant.INPUT_CHARSET;
        }
        StringBuffer sb = new StringBuffer();
        Set es = map.entrySet();
        Iterator it = es.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String k = (String) entry.getKey();
            String v = (String) entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k) && !"csp_id".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }
        sb.append("key=" + key);
        return MD5Util.MD5Encode(sb.toString(), charset).toUpperCase();
    }

    /**
     * RSA签名
     * RSA/ECB/PKCS1Padding
     * @param content	内容
     * String keyPath	密钥路径
     * @return
     */
    public static String signByKeyPath(String content, String keyPath, String charset){

        String key = getKeyStrByPath(keyPath);
        return sign(content, key, charset);

    }

    /**
     * 根据路径获取公钥
     * @param publicKeyPath	密钥路径
     * @return
     */
    private static String getKeyStrByPath(String publicKeyPath){

        InputStream inputStream = null;
        StringBuffer sb = new StringBuffer();
        try {
            inputStream = new FileInputStream(publicKeyPath);
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String readLine = null;
            while ((readLine = br.readLine()) != null) {
                if (readLine.charAt(0) == '-') {
                    continue;
                } else {
                    sb.append(readLine);
                    sb.append('\r');
                }
            }

        } catch (Exception e) {
            throw new BaseException("load public key failed !", e);
        } finally {
            if (inputStream != null){
                try {
                    inputStream.close();
                }catch (Exception e){
                    throw new BaseException(e);
                }
            }
        }
        return sb.toString();
    }

    /**
     * RSA私钥签名
     * RSA/ECB/PKCS1Padding
     * @param content
     * @param privateKey	私钥字串
     * @param charset	字符集
     * @return
     */
    public static String sign(String content, String privateKey, String charset) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey.getBytes()));
            KeyFactory key = KeyFactory.getInstance("RSA");
            PrivateKey priKey = key.generatePrivate(priPKCS8);
            Signature signature = Signature.getInstance("SHA1WithRSA");
            signature.initSign(priKey);
            if (StringUtils.isEmpty(charset)) {
                signature.update(content.getBytes());
            }else {
                signature.update(content.getBytes(charset));
            }
            byte[]signed = signature.sign();
            return new String(Base64.encodeBase64Chunked(signed));

        } catch (Exception e) {
            throw new BaseException(e);
        }
    }
}
