package io.github.rookieprogramtheape.unifiedhelper.processor.pay;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.rookieprogramtheape.unifiedhelper.common.resp.CommonResponse;
import io.github.rookieprogramtheape.unifiedhelper.dto.PayCallbackDTO;
import io.github.rookieprogramtheape.unifiedhelper.util.BaseRequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import static io.github.rookieprogramtheape.unifiedhelper.util.SignUtils.checkSign;

/**
 * 支付处理器实现类
 *
 * @author niehong
 * @date 2024/08/12
 */
@Slf4j
@Component
public class ToPayProcessorImpl implements IToPayProcessor {

    @Resource
    private BaseRequestUtils baseRequestUtils;

    @Override
    public CommonResponse wxPay(String openId, String appId, Long orderNo, String amount) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        HashMap<String, Object> payExtras = new HashMap<>();
        payExtras.put("appId", appId);
        payExtras.put("openId", openId);
        params.put("wayCode", "wx");
        params.put("payExtras", payExtras);

        return baseRequestUtils.payExecute("/unifiedPay", orderNo, params);
    }


    @Override
    public CommonResponse wxPay(String openId, String appId, Long orderNo, BigDecimal amount) {
        return wxPay(openId, appId, orderNo, String.valueOf(amount.multiply(new BigDecimal("100")).intValue()));
    }


    @Override
    public CommonResponse wxPay(String appId, String appSecret, String mchNo, String openId, String wxAppId, Long orderNo, String amount) {
        if (StrUtil.hasEmpty(appId, appSecret, mchNo)) {
            return CommonResponse.error("平台参数不能为空");
        }

        HashMap<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        HashMap<String, Object> payExtras = new HashMap<>();
        payExtras.put("appId", wxAppId);
        payExtras.put("openId", openId);
        params.put("wayCode", "wx");
        params.put("payExtras", payExtras);

        return baseRequestUtils.payExecute("/unifiedPay", orderNo, params, appId, appSecret, mchNo);
    }


    @Override
    public CommonResponse wxPay(String appId, String appSecret, String mchNo, String openId, String wxAppId, Long orderNo, BigDecimal amount) {
        return wxPay(appId, appSecret, mchNo, openId, wxAppId, orderNo, String.valueOf(amount.multiply(new BigDecimal("100")).intValue()));
    }


    @Override
    public CommonResponse wxPayCallbackDispose(HttpServletRequest request) {
        try {
            BufferedReader br = request.getReader();
            StringBuilder content = new StringBuilder();
            String str = "";
            while ((str = br.readLine()) != null) {
                content.append(str);
            }

            JSONObject result = JSONObject.parseObject(content.toString());

            log.info("支付回调参数: {}", result);
            String timestamp = request.getHeader("fysz-timestamp");
            String sign = request.getHeader("fysz-sign");
            // 验签
            String appId = baseRequestUtils.getUnifiedPaymentProperties().getAppId();
            String appSecret = baseRequestUtils.getUnifiedPaymentProperties().getAppSecret();
            checkSign(appId, appSecret, result, sign);
            // 判断支付状态
            String payState = result.getString("state");
            String orderRecordId = result.getString("mchOrderNo");
            // 易票联订单号
            String transactionId = result.getString("payOrderId");
            Integer amount = result.getInteger("amount");
            // 支付完成时间
            String originResponse = JSONObject.toJSONString(result);
            Date successTime = null;
            if (result.getString("successTime") != null) {
                // 支付完成时间
                successTime = DateUtil.parseDate(result.getString("successTime"));
            }
            // 数据处理并返回
            PayCallbackDTO callbackDTO = PayCallbackDTO.builder().timestamp(Long.valueOf(timestamp))
                    .payState(Integer.valueOf(payState))
                    .orderId(orderRecordId)
                    .transactionId(transactionId)
                    .amount(amount)
                    .originResponse(originResponse)
                    .successTime(successTime).build();
            return CommonResponse.success(callbackDTO);
        } catch (Exception e) {
            log.error("支付回调处理异常", e);
            return CommonResponse.error("支付回调处理异常");
        }
    }
}
