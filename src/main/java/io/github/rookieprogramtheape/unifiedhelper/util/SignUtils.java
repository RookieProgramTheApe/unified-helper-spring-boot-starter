package io.github.rookieprogramtheape.unifiedhelper.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;

import java.util.*;

/**
 * 签名工具类
 *
 * @author niehong
 * @date 2024/08/12
 */
public class SignUtils {
    public static String getParamsContentStr(Map<String, Object> params) {
        SortedMap<String, String> sortedParams = new TreeMap<>();
        Set<Map.Entry<String, Object>> entries = params.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            Object value = entry.getValue();
            String str = null;
            if (value instanceof Map) {
                str = getAllParamsContentStr((Map<String, Object>) entry.getValue());
            } else {
                str = String.valueOf(value);
            }
            sortedParams.put(entry.getKey(), str);
        }

        Set<Map.Entry<String, String>> sortedEntries = sortedParams.entrySet();
        Iterator<Map.Entry<String, String>> iterator = sortedEntries.iterator();
        StringBuilder valueStr = new StringBuilder();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            // 签名结果字段不处理
            if (StrUtil.equalsAnyIgnoreCase(entry.getKey(), new String[]{"sign", "appId", "appSecret"})) {
                continue;
            }
            String v = entry.getValue();
            String value = StrUtil.trimToEmpty(entry.getValue());
            if (StrUtil.isNotEmpty(value)) {
                valueStr.append(entry.getKey());
                valueStr.append(value);
            }
        }
        return valueStr.toString();
    }

    // 不过滤签名相关字段
    public static String getAllParamsContentStr(Map<String, Object> params) {
        SortedMap<String, String> sortedParams = new TreeMap<>();
        Set<Map.Entry<String, Object>> entries = params.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            Object value = entry.getValue();
            String str = null;
            if (value instanceof Map) {
                str = getParamsContentStr((Map<String, Object>) entry.getValue());
            } else {
                str = String.valueOf(value);
            }
            sortedParams.put(entry.getKey(), str);
        }

        Set<Map.Entry<String, String>> sortedEntries = sortedParams.entrySet();
        Iterator<Map.Entry<String, String>> iterator = sortedEntries.iterator();
        StringBuilder valueStr = new StringBuilder();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            String v = entry.getValue();
            String value = StrUtil.trimToEmpty(entry.getValue());
            if (StrUtil.isNotEmpty(value)) {
                valueStr.append(entry.getKey());
                valueStr.append(value);
            }
        }
        return valueStr.toString();
    }

    public static boolean checkSign(String appId, String appSecret, Map<String, Object> params, String sign) {
        String md5Result = getSign(appId, appSecret, params);
        return md5Result.equals(sign);
    }

    public static String getSign(String appId, String appSecret, Map<String, Object> params) {
        String paramsContentStr = getParamsContentStr(params);
        paramsContentStr = paramsContentStr + appId + appSecret;
        return SecureUtil.md5(paramsContentStr).toUpperCase();
    }

    public static String getAllParamsSign(String appId, String appSecret, Map<String, Object> params) {
        String paramsContentStr = getAllParamsContentStr(params);
        paramsContentStr = paramsContentStr + appId + appSecret;
        System.out.println("签名字符串："+paramsContentStr);
        return SecureUtil.md5(paramsContentStr).toUpperCase();
    }
}
