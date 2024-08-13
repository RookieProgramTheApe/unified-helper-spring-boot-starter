package io.github.rookieprogramtheape.unifiedhelper.core;

import io.github.rookieprogramtheape.unifiedhelper.processor.pay.IToPayProcessor;
import io.github.rookieprogramtheape.unifiedhelper.processor.refund.IRefundProcessor;
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
     * 支付相关
     */
    @Resource
    public IToPayProcessor pay;

    /**
     * 退款相关
     */
    @Resource
    public IRefundProcessor refund;
}
