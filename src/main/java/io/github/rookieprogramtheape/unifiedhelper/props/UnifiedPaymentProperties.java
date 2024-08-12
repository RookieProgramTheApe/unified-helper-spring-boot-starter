package io.github.rookieprogramtheape.unifiedhelper.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 统一支付属性配置类
 *
 * @author niehong
 * @date 2024/08/12
 */
@Data
@ConfigurationProperties(prefix = "unified-payment")
public class UnifiedPaymentProperties {
    /**
     * 平台应用id
     */
    private String appId;

    /**
     * 平台应用密钥
     */
    private String appSecret;

    /**
     * 平台请求基地址
     */
    private String baseUrl;

    /**
     * 平台商户号
     */
    private String mchNo;

    /**
     * 请求超时时间 - 单位：秒
     */
    private Integer timeOut = 10000;

    /**
     * 微信appId
     */
    private String wxAppId;

    /**
     * 支付回调地址
     */
    private String payNotifyUrl;

    /**
     * 退款回调地址
     */
    private String refundNotifyUrl;

    /**
     * 分账模式 - 0:不允许分账 1:自动分账 2:商户手动分账 4:拆单分账
     */
    private Integer divisionMode = 0;
}
