package io.github.rookieprogramtheape.unifiedhelper.common.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用响应类
 *
 * @author niehong
 * @date 2024/08/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResponse<T> {


    /**
     * 成功
     */
    public static final Integer SUCCESS = 200;
    private static final String SUCCESS_MSG = "ok";

    /**
     * 错误
     */
    public static final Integer ERROR = 500000;
    private static final String ERROR_MSG = "error";

    private Integer code;
    private String msg;
    private T data;


    /**
     * 错误响应
     *
     * @param msg  消息
     * @param code 状态码
     * @return {@link CommonResponse }<{@link T }>
     */
    public static <T> CommonResponse<T> error(String msg, Integer code) {
        CommonResponse<T> response = new CommonResponse<>();
        response.msg = msg;
        response.code = code;
        return response;
    }

    /**
     * 错误响应
     *
     * @param msg 消息
     * @return {@link CommonResponse }<{@link T }>
     */
    public static <T> CommonResponse<T> error(String msg) {
        return error(msg, ERROR);
    }

    /**
     * 成功响应
     *
     * @param data 数据
     * @return {@link CommonResponse }<{@link T }>
     */
    public static <T> CommonResponse<T> success(T data) {
        CommonResponse<T> response = new CommonResponse<>();
        response.data = data;
        response.code = SUCCESS;
        response.msg = SUCCESS_MSG;
        return response;
    }

    /**
     * 成功响应
     *
     * @return {@link CommonResponse }<{@link T }>
     */
    public static <T> CommonResponse<T> success() {
        return success(null);
    }

}
