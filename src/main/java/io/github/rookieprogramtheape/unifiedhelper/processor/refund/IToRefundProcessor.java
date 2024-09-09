package io.github.rookieprogramtheape.unifiedhelper.processor.refund;


import io.github.rookieprogramtheape.unifiedhelper.common.resp.CommonResponse;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * 退款处理器接口
 *
 * @author niehong
 * @date 2024/08/12
 */
public interface IToRefundProcessor {
    /**
     * 全额退款
     *
     * @param orderNo      订单号 [要进行退款的订单号，已成功支付完成的订单号]
     * @param refundNo     退款单号 [由商户生成的退款单号]
     * @param refundAmount 退款金额 单位：分
     * @return {@link CommonResponse }
     */
    CommonResponse fullRefund(String orderNo, String refundNo, String refundAmount);


    /**
     * 全额退款
     *
     * @param orderNo      订单号 [要进行退款的订单号，已成功支付完成的订单号]
     * @param refundNo     退款单号 [由商户生成的退款单号]
     * @param refundAmount 退款金额
     * @return {@link CommonResponse }
     */
    CommonResponse fullRefund(String orderNo, String refundNo, BigDecimal refundAmount);

    /**
     * 部分退款
     *
     * @param orderNo      订单号
     * @param refundNo     退款编号
     * @param payAmount    支付金额
     * @param refundAmount 退款金额 单位：分
     * @return {@link CommonResponse }
     */
    CommonResponse partialRefund(String orderNo, String refundNo, String payAmount, String refundAmount);


    /**
     * 部分退款
     *
     * @param orderNo      订单号
     * @param refundNo     退款编号
     * @param payAmount    支付金额
     * @param refundAmount 退款金额
     * @return {@link CommonResponse }
     */
    CommonResponse partialRefund(String orderNo, String refundNo, String payAmount, BigDecimal refundAmount);


    /**
     * 退款回调处理
     *
     * @param request 退款回调请求
     * @return {@link CommonResponse }
     */
    CommonResponse refundCallbackDispose(HttpServletRequest request);
}
