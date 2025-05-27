package com.lakshan.carbonwise.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarbonInterfaceConfig {

    @Value("${carbon.api.key}")
    private String apiKey;

    public String getApiKey() {
        return apiKey;
    }
}