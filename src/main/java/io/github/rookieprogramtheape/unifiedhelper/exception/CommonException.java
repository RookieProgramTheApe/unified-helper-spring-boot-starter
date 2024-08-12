package io.github.rookieprogramtheape.unifiedhelper.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用异常类
 *
 * @author niehong
 * @date 2024/08/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonException extends RuntimeException {
    /**
     * 错误码
     */
    private Integer errorCode;

    /**
     * 错误信息
     */
    private String message;
}
