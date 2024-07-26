package org.basilomp.paymentapp.config.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@ConfigurationProperties(prefix = "integrations.products")
public class ProductExecutorProperties {

    private final RestTemplateProperties productExecutorClient;

    @ConstructorBinding
    public ProductExecutorProperties(RestTemplateProperties productExecutorClient) {
        this.productExecutorClient = productExecutorClient;
    }

    public RestTemplateProperties getProductExecutorClient() {
        return productExecutorClient;
    }
}
