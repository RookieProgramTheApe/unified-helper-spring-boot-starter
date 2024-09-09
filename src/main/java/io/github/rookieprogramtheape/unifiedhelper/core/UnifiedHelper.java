package io.github.rookieprogramtheape.unifiedhelper.core;

import io.github.rookieprogramtheape.unifiedhelper.processor.pay.IToPayProcessor;
import io.github.rookieprogramtheape.unifiedhelper.processor.refund.IToRefundProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 统一支付助手
 *
 * @author niehong
 * @date 2024/08/12
 */
@Component
public class UnifiedHelper {

    /**
     * 支付
     */
    @Resource
    public IToPayProcessor toPay;

    /**
     * 退款
     */
    @Resource
    public IToRefundProcessor toRefund;
}
