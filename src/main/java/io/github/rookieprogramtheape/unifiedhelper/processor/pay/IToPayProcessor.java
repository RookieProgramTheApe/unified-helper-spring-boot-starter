package io.github.rookieprogramtheape.unifiedhelper.processor.pay;

import io.github.rookieprogramtheape.unifiedhelper.common.resp.CommonResponse;

/**
 * 支付处理器接口
 *
 * @author niehong
 * @date 2024/08/12
 */
public interface IToPayProcessor {
    CommonResponse wxPay(String openId, String appId, String amount);
}
