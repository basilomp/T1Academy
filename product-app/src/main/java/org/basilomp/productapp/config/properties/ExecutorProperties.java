package org.basilomp.productapp.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@Getter
@Setter
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
