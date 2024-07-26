package org.basilomp.paymentapp.config;

import org.basilomp.paymentapp.config.properties.ExecutorProperties;
import org.basilomp.paymentapp.config.properties.ProductExecutorProperties;
import org.basilomp.paymentapp.config.properties.RestTemplateProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableConfigurationProperties(
        {
                ExecutorProperties.class,
                ProductExecutorProperties.class
        }
)
public class ApplicationConfig {

    @Bean
    public RestTemplate executorRestClient(final ExecutorProperties executorProperties,
                                           final RestTemplateErrorResponseHandler errorResponseHandler) {
        final RestTemplateProperties paymentsExecutorClient = executorProperties.getPaymentsExecutorClient();
        return new RestTemplateBuilder()
                .rootUri(paymentsExecutorClient.getUrl())
                .setConnectTimeout(paymentsExecutorClient.getConnectTimeout())
                .setReadTimeout(paymentsExecutorClient.getReadTimeout())
                .errorHandler(errorResponseHandler)
                .build();
    }

    @Bean
    public RestTemplate productRestClient(final ProductExecutorProperties productProperties,
                                          final RestTemplateErrorResponseHandler errorResponseHandler) {
        final RestTemplateProperties productExecutorClient = productProperties.getProductExecutorClient();
        return new RestTemplateBuilder()
                .rootUri(productExecutorClient.getUrl())
                .setConnectTimeout(productExecutorClient.getConnectTimeout())
                .setReadTimeout(productExecutorClient.getReadTimeout())
                .errorHandler(errorResponseHandler)
                .build();
    }
}
