package io.github.rookieprogramtheape.unifiedhelper.exception;

import lombok.Data;

/**
 * 错误状态码
 *
 * @author niehong
 * @date 2024/08/12
 */
@Data
public class ErrorCode {
    /**
     * 错误码
     */
    private final Integer code;
    /**
     * 错误提示
     */
    private final String msg;

    public ErrorCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }
}
