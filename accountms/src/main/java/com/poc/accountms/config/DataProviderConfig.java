package com.poc.accountms.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix="config")
@Getter
@Setter
@NoArgsConstructor
public class DataProviderConfig {
    private List<Provider> providers;

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Provider{
        private String name;
        private String url;

    }
}
