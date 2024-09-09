package io.github.rookieprogramtheape.unifiedhelper.processor.pay;

import io.github.rookieprogramtheape.unifiedhelper.common.resp.CommonResponse;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * 支付处理器接口
 *
 * @author niehong
 * @date 2024/08/12
 */
public interface IToPayProcessor {
    /**
     * 微信支付发起
     *
     * @param openId  微信用户的openId
     * @param appId   微信小程序/公众号的appId
     * @param orderNo 订单号 （交易唯一标识） 回调时通过此确定订单
     * @param amount  支付金额 单位：分
     * @return {@link CommonResponse }
     */
    CommonResponse wxPay(String openId, String appId, Long orderNo, String amount);


    /**
     * wx支付
     *
     * @param openId  微信用户的openId
     * @param appId   微信小程序/公众号的appId
     * @param orderNo 订单号 （交易唯一标识） 回调时通过此确定订单
     * @param amount  支付金额
     * @return {@link CommonResponse }
     */
    CommonResponse wxPay(String openId, String appId, Long orderNo, BigDecimal amount);

    /**
     * 微信支付回调处理
     *
     * @param request 支付回调请求
     * @return {@link CommonResponse }
     */
    CommonResponse wxPayCallbackDispose(HttpServletRequest request);
}
