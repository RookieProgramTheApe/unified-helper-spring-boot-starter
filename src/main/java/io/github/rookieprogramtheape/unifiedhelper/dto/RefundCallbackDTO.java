package io.github.rookieprogramtheape.unifiedhelper.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 退款回调数据传输对象
 *
 * @author niehong
 * @date 2024/09/06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefundCallbackDTO {

    public static final Integer SUCCESS_CODE = 2;

    /**
     * 时间戳
     */
    private Long timestamp;

    /**
     * 支付状态
     */
    private Integer state;

    /**
     * 上游订单号
     */
    private String transactionId;

    /**
     * 退款订单号
     */
    private String refundNo;

    /**
     * 退款成功时间
     */
    private Date successTime;

    /**
     * 退款金额 [单位：分]
     */
    private Integer refundAmount;

    /**
     * 原始响应数据
     */
    private String originResponse;

    public Boolean isSuccess(){
        return SUCCESS_CODE.equals(this.state);
    }
}
