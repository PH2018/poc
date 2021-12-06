package com.poc.accountms.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DataProvidermsConfig {

    @Bean(name = "myRestTemplate")
    @Primary
  //  @LoadBalanced
    public RestTemplate getRestTemplate(RestTemplateBuilder rtb) {
        return rtb.build();
    }

}
