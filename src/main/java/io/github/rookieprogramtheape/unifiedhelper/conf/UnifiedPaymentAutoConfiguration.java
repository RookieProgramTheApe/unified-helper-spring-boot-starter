package io.github.rookieprogramtheape.unifiedhelper.conf;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * 统一支付自动配置类
 *
 * @author niehong
 * @date 2024/08/12
 */
@AutoConfiguration
@ComponentScan(basePackages = {"io.github.rookieprogramtheape.unifiedhelper"})
public class UnifiedPaymentAutoConfiguration {
}
