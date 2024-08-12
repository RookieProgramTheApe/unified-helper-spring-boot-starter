package io.github.rookieprogramtheape.unifiedhelper.util;

import io.github.rookieprogramtheape.unifiedhelper.exception.CommonException;
import io.github.rookieprogramtheape.unifiedhelper.exception.ErrorCode;

/**
 * 异常工具类
 *
 * @author niehong
 * @date 2024/08/12
 */
public class ExceptionUtils {
    /**
     * 创建通用异常
     * @param errorCode 错误信息
     * @return {@link CommonException} 通用异常
     */
    public static CommonException exception(ErrorCode errorCode) {
        return new CommonException(errorCode.getCode(), errorCode.getMsg());
    }
}
