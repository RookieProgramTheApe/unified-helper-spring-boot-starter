package io.github.rookieprogramtheape.unifiedhelper.processor.pay;

import io.github.rookieprogramtheape.unifiedhelper.common.resp.CommonResponse;
import io.github.rookieprogramtheape.unifiedhelper.util.BaseRequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * 支付处理器实现类
 *
 * @author niehong
 * @date 2024/08/12
 */
@Slf4j
@Component
public class ToPayProcessorImpl implements IToPayProcessor {

    @Resource
    private BaseRequestUtils baseRequestUtils;

    @Override
    public CommonResponse wxPay(String openId, String appId, String amount) {

        HashMap<String, Object> params = new HashMap<>();
        params.put("amount", amount);
        HashMap<String, Object> payExtras = new HashMap<>();
        payExtras.put("appId", appId);
        payExtras.put("openId", openId);
        params.put("wayCode", "wx");
        params.put("payExtras", payExtras);

        return baseRequestUtils.postExecute("/unifiedPay", params);
    }
}
