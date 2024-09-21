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
     * <p>
     *     - 使用SDK配置文件的商户信息发起交易
     * </p>
     *
     * @param openId  微信用户的openId
     * @param appId   微信小程序/公众号的appId
     * @param orderNo 订单号 （交易唯一标识） 回调时通过此确定订单
     * @param amount  支付金额 单位：分
     * @return {@link CommonResponse }
     */
    CommonResponse wxPay(String openId, String appId, Long orderNo, String amount);


    /**
     * 微信支付发起
     * <p>
     *     - 使用SDK配置文件的商户信息发起交易
     * </p>
     *
     * @param openId  微信用户的openId
     * @param appId   微信小程序/公众号的appId
     * @param orderNo 订单号 （交易唯一标识） 回调时通过此确定订单
     * @param amount  支付金额
     * @return {@link CommonResponse }
     */
    CommonResponse wxPay(String openId, String appId, Long orderNo, BigDecimal amount);


    /**
     * wx支付
     *
     * @param appId     支付平台的appId
     * @param appSecret 支付平台的appSecret
     * @param mchNo     支付平台的商户号
     * @param openId  微信用户的openId
     * @param wxAppId   微信小程序/公众号的appId
     * @param orderNo 订单号 （交易唯一标识） 回调时通过此确定订单
     * @param amount  支付金额 单位：分
     * @return {@link CommonResponse }
     */
    CommonResponse wxPay(String appId, String appSecret, String mchNo, String openId, String wxAppId, Long orderNo, String amount);


    /**
     * wx支付
     *
     * @param appId     支付平台的appId
     * @param appSecret 支付平台的appSecret
     * @param mchNo     支付平台的商户号
     * @param openId  微信用户的openId
     * @param wxAppId   微信小程序/公众号的appId
     * @param orderNo 订单号 （交易唯一标识） 回调时通过此确定订单
     * @param amount  支付金额
     * @return {@link CommonResponse }
     */
    CommonResponse wxPay(String appId, String appSecret, String mchNo, String openId, String wxAppId, Long orderNo, BigDecimal amount);


    /**
     * 微信支付回调处理
     *
     * @param request 支付回调请求
     * @return {@link CommonResponse }
     */
    CommonResponse wxPayCallbackDispose(HttpServletRequest request);
}
