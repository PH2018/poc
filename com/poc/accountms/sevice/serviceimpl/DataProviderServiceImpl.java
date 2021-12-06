package com.poc.accountms.sevice.serviceimpl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.poc.accountms.model.DataProviderRequest;
import com.poc.accountms.model.DataProviderResponse;
import com.poc.accountms.service.DataProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DataProviderServiceImpl implements DataProviderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataProviderServiceImpl.class);
    private final RestTemplate restTemplate;

    public DataProviderServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @HystrixCommand(fallbackMethod = "accountValidate", groupKey = "def", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")})
    public DataProviderResponse isAccountValid(final DataProviderRequest dataProviderRequest, final String url) {
        LOGGER.info("## Getting all orders from Orderms ##");
        DataProviderResponse dataProviderResponse=restTemplate.postForObject(url,dataProviderRequest,DataProviderResponse.class);
        return dataProviderResponse;
    }

    public DataProviderResponse accountValidate(final DataProviderRequest dataProviderRequest, final String url){
        LOGGER.info("inside ## fall back method of dataprovider service");
        DataProviderResponse fallBackResponse = new DataProviderResponse();
        fallBackResponse.setValid(false);
        return fallBackResponse;
    }
}
