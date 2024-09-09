package io.github.rookieprogramtheape.unifiedhelper.processor.refund;


import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import io.github.rookieprogramtheape.unifiedhelper.common.resp.CommonResponse;
import io.github.rookieprogramtheape.unifiedhelper.dto.PayCallbackDTO;
import io.github.rookieprogramtheape.unifiedhelper.dto.RefundCallbackDTO;
import io.github.rookieprogramtheape.unifiedhelper.util.BaseRequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.math.BigDecimal;
import java.util.Date;

import static io.github.rookieprogramtheape.unifiedhelper.util.SignUtils.checkSign;

/**
 * 退款处理器实现类
 *
 * @author niehong
 * @date 2024/08/12
 */
@Slf4j
@Component
public class ToRefundProcessorImpl implements IToRefundProcessor {

    @Resource
    private BaseRequestUtils baseRequestUtils;
    @Override
    public CommonResponse fullRefund(String orderNo, String refundNo, String refundAmount) {
        return baseRequestUtils.refundExecute("/refund/refund", orderNo, refundNo, refundAmount, refundAmount);
    }

    @Override
    public CommonResponse fullRefund(String orderNo, String refundNo, BigDecimal refundAmount) {
        return fullRefund(orderNo, refundNo, String.valueOf(refundAmount.multiply(new BigDecimal("100")).intValue()));
    }

    @Override
    public CommonResponse partialRefund(String orderNo, String refundNo, String payAmount, String refundAmount) {
        return baseRequestUtils.refundExecute("/refund/refund", orderNo, refundNo, payAmount, refundAmount);
    }

    @Override
    public CommonResponse partialRefund(String orderNo, String refundNo, String payAmount, BigDecimal refundAmount) {
        return partialRefund(orderNo, refundNo, payAmount, String.valueOf(refundAmount.multiply(new BigDecimal("100")).intValue()));
    }


    public CommonResponse refundCallbackDispose(HttpServletRequest request) {
        try {
            BufferedReader br = request.getReader();
            StringBuilder content = new StringBuilder();
            String str = "";
            while ((str = br.readLine()) != null) {
                content.append(str);
            }

            JSONObject result = JSONObject.parseObject(content.toString());

            log.info("退款回调参数: {}", result);
            String timestamp = request.getHeader("fysz-timestamp");
            String sign = request.getHeader("fysz-sign");
            // 验签
            String appId = baseRequestUtils.getUnifiedPaymentProperties().getAppId();
            String appSecret = baseRequestUtils.getUnifiedPaymentProperties().getAppSecret();
            checkSign(appId, appSecret, result, sign);

            Integer state = result.getInteger("state");
            String refundNo = result.getString("mchRefundNo");
            // 易票联订单号
            String transactionId = result.getString("payOrderId");
            Integer refundAmount = result.getInteger("refundAmount");
            // 支付完成时间
            String originResponse = JSONObject.toJSONString(result);
            Date successTime = null;
            if (result.getString("successTime") != null) {
                // 支付完成时间
                successTime = DateUtil.parseDate(result.getString("successTime"));
            }

            // 数据处理并返回
            RefundCallbackDTO callbackDTO = RefundCallbackDTO.builder().timestamp(Long.valueOf(timestamp))
                    .state(state)
                    .transactionId(transactionId)
                    .refundAmount(refundAmount)
                    .successTime(successTime)
                    .refundNo(refundNo)
                    .originResponse(originResponse).build();
            return CommonResponse.success(callbackDTO);
        } catch (Exception e) {
            log.error("退款回调处理异常", e);
            return CommonResponse.error("退款回调处理异常");
        }
    }
}
