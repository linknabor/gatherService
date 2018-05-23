package com.eshequ.gatherservice.util;

import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Map;

public class ObjectUtil {

    /**
     * 创建请求xml
     * @param map 请求以键值对形势封装成map，根据请求的map来组装xml
     */
    public static String createRequestXML(Map<String,String> map){

        StringBuffer buffer = new StringBuffer();
        buffer.append("<xml>");

        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String>entry = it.next();
            String key = entry.getKey();
            String value = entry.getValue();
            if (!StringUtils.isEmpty(value)) {
                buffer.append("<").append(key).append(">").append(value).append("</").append(key).append(">");
            }
        }
        buffer.append("</xml>");
        return buffer.toString();

    }
}
