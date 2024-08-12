package io.github.rookieprogramtheape.unifiedhelper.constants;

import io.github.rookieprogramtheape.unifiedhelper.exception.ErrorCode;

/**
 * 错误状态码
 *
 * @author niehong
 * @date 2024/08/12
 */
public interface ErrorCodeConstants {
    ErrorCode TO_PAY_HTTP_FAIL = new ErrorCode(10000200, "支付请求失败");
    ErrorCode TO_PAY_FAIL = new ErrorCode(10000300, "支付失败");
}
