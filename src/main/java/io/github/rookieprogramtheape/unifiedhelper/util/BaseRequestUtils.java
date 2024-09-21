package io.github.rookieprogramtheape.unifiedhelper.util;

import cn.hutool.core.text.CharSequenceUtil;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import io.github.rookieprogramtheape.unifiedhelper.common.resp.CommonResponse;
import io.github.rookieprogramtheape.unifiedhelper.props.UnifiedPaymentProperties;
import lombok.Cleanup;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

import static io.github.rookieprogramtheape.unifiedhelper.constants.ErrorCodeConstants.TO_PAY_FAIL;
import static io.github.rookieprogramtheape.unifiedhelper.constants.ErrorCodeConstants.TO_PAY_HTTP_FAIL;
import static io.github.rookieprogramtheape.unifiedhelper.util.ExceptionUtils.exception;
import static io.github.rookieprogramtheape.unifiedhelper.util.SignUtils.getSign;

/**
 * 基本请求工具类
 *
 * @author niehong
 * @date 2024/08/12
 */
@Slf4j
@Component
@Getter
@EnableConfigurationProperties(UnifiedPaymentProperties.class)
public class BaseRequestUtils {

    @Resource
    private UnifiedPaymentProperties unifiedPaymentProperties;

    /**
     * 支付执行
     *
     * @param url     url 地址
     * @param orderNo 订单号 （交易唯一标识） 回调时通过此确定订单
     * @param params  params 参数
     * @return {@link CommonResponse } 响应结果
     */
    public CommonResponse payExecute(String url, Long orderNo, Map<String, Object> params) {
        // 对参数进行签名
        params.put("appId", unifiedPaymentProperties.getAppId());
        params.put("appSecret", unifiedPaymentProperties.getAppSecret());
        params.put("mchNo", unifiedPaymentProperties.getMchNo());
        params.put("divisionMode", unifiedPaymentProperties.getDivisionMode());
        params.put("notifyUrl", unifiedPaymentProperties.getPayNotifyUrl());
        params.put("mchOrderNo", orderNo);
        params.put("timestamp", System.currentTimeMillis());
        params.put("currency", "CNY");
        String signStr = getSign(((String) params.get("appId")), ((String) params.get("appSecret")), params);
        params.put("sign", signStr);

        String requestUrl = CharSequenceUtil.format("{}/{}", unifiedPaymentProperties.getBaseUrl(), url);

        // 构建POST请求
        HttpRequest httpRequest = HttpRequest.post(requestUrl)
                .header("Content-Type", "application/json;charset=UTF-8")
                .timeout(unifiedPaymentProperties.getTimeOut());
        try {
            @Cleanup HttpResponse response = httpRequest.body(JSONUtil.toJsonStr(params)).execute();
            if (response.isOk()) {
                // 返回结果
                String body = response.body();
                return JSON.parseObject(body, CommonResponse.class);
            }
        } catch (HttpException e) {
            throw exception(TO_PAY_HTTP_FAIL);
        } catch (Exception e) {
            throw exception(TO_PAY_FAIL);
        }
        return null;
    }

    /**
     * 支付执行
     *
     * @param url       url 地址
     * @param orderNo   订单号 （交易唯一标识） 回调时通过此确定订单
     * @param params    params 参数
     * @param appId     支付平台的appId
     * @param appSecret 支付平台的appSecret
     * @param mchNo     支付平台的商户号
     * @return {@link CommonResponse } 响应结果
     */
    public CommonResponse payExecute(String url, Long orderNo, Map<String, Object> params, String appId, String appSecret, String mchNo) {
        // 对参数进行签名
        params.put("appId", appId);
        params.put("appSecret", appSecret);
        params.put("mchNo", mchNo);
        params.put("divisionMode", unifiedPaymentProperties.getDivisionMode());
        params.put("notifyUrl", unifiedPaymentProperties.getPayNotifyUrl());
        params.put("mchOrderNo", orderNo);
        params.put("timestamp", System.currentTimeMillis());
        params.put("currency", "CNY");
        String signStr = getSign(((String) params.get("appId")), ((String) params.get("appSecret")), params);
        params.put("sign", signStr);

        String requestUrl = CharSequenceUtil.format("{}/{}", unifiedPaymentProperties.getBaseUrl(), url);

        // 构建POST请求
        HttpRequest httpRequest = HttpRequest.post(requestUrl)
                .header("Content-Type", "application/json;charset=UTF-8")
                .timeout(unifiedPaymentProperties.getTimeOut());
        try {
            @Cleanup HttpResponse response = httpRequest.body(JSONUtil.toJsonStr(params)).execute();
            if (response.isOk()) {
                // 返回结果
                String body = response.body();
                return JSON.parseObject(body, CommonResponse.class);
            }
        } catch (HttpException e) {
            throw exception(TO_PAY_HTTP_FAIL);
        } catch (Exception e) {
            throw exception(TO_PAY_FAIL);
        }
        return null;
    }

    public CommonResponse refundExecute(String url, String orderNo, String refundNo, String payAmount, String refundAmount) {
        // 对参数进行签名
        Map<String, Object> params = new HashMap<>();
        params.put("appId", unifiedPaymentProperties.getAppId());
        params.put("appSecret", unifiedPaymentProperties.getAppSecret());
        params.put("mchNo", unifiedPaymentProperties.getMchNo());
        params.put("notifyUrl", unifiedPaymentProperties.getRefundNotifyUrl());
        params.put("mchPayOrderNo", orderNo);
        params.put("mchRefundNo", refundNo);
        params.put("payAmount", payAmount);
        params.put("refundAmount", refundAmount);
        params.put("timestamp", System.currentTimeMillis());
        params.put("currency", "CNY");
        String signStr = getSign(((String) params.get("appId")), ((String) params.get("appSecret")), params);
        params.put("sign", signStr);

        String requestUrl = CharSequenceUtil.format("{}/{}", unifiedPaymentProperties.getBaseUrl(), url);

        // 构建POST请求
        HttpRequest httpRequest = HttpRequest.post(requestUrl)
                .header("Content-Type", "application/json;charset=UTF-8")
                .timeout(unifiedPaymentProperties.getTimeOut());
        try {
            @Cleanup HttpResponse response = httpRequest.body(JSONUtil.toJsonStr(params)).execute();
            if (response.isOk()) {
                // 返回结果
                String body = response.body();
                return JSON.parseObject(body, CommonResponse.class);
            }
        } catch (HttpException e) {
            throw exception(TO_PAY_HTTP_FAIL);
        } catch (Exception e) {
            throw exception(TO_PAY_FAIL);
        }
        return null;
    }
}
