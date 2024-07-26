package org.basilomp.paymentapp.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "integrations.executors")
public class ExecutorProperties {

    private final RestTemplateProperties paymentsExecutorClient;

    @ConstructorBinding
    public ExecutorProperties(RestTemplateProperties paymentsExecutorClient) {
        this.paymentsExecutorClient = paymentsExecutorClient;
    }

    public RestTemplateProperties getPaymentsExecutorClient() {
        return paymentsExecutorClient;
    }
}
