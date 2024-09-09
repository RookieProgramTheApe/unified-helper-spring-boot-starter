package io.github.rookieprogramtheape.unifiedhelper.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 支付回调数据传输对象
 *
 * @author niehong
 * @date 2024/09/02
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PayCallbackDTO {

    public static final Integer SUCCESS_CODE = 2;

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 支付状态
     */
    private Integer payState;

    /**
     * 上游订单号
     */
    private String transactionId;

    /**
     * 支付订单号
     */
    private String orderId;

    /**
     * 支付成功时间
     */
    private Date successTime;

    /**
     * 支付金额 [单位：分]
     */
    private Integer amount;

    /**
     * 原始响应数据
     */
    private String originResponse;

    public Boolean isSuccess() {
        return SUCCESS_CODE.equals(this.payState);
    }
}
