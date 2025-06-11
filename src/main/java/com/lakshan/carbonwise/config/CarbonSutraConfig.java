package com.lakshan.carbonwise.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CarbonSutraConfig {

    @Getter
    @Value("${carbonsutra.api.key}")
    private String rapidAPIKey;

    @Value("${carbon.api.key}")
    private String bearerAPIKey;

    public String getBearerAPIKey() {
        return bearerAPIKey;
    }

}
