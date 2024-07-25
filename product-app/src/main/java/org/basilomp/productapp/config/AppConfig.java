package org.basilomp.productapp.config;

import lombok.RequiredArgsConstructor;
import org.basilomp.productapp.config.properties.ExecutorProperties;
import org.basilomp.productapp.config.properties.RestTemplateProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(ExecutorProperties.class)
public class AppConfig {

    @Bean
    public RestTemplate executorRestClient(final ExecutorProperties executorProperties,
                                           final RestTemplateErrorResponseHandler errorResponseHandler) {
        final RestTemplateProperties executorClient = executorProperties.getPaymentsExecutorClient();
        return new RestTemplateBuilder()
                .rootUri(executorClient.getUrl())
                .setConnectTimeout(executorClient.getConnectTimeout())
                .setReadTimeout(executorClient.getReadTimeout())
                .errorHandler(errorResponseHandler)
                .build();
    }
}

