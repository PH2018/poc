package com.poc.accountms.aggregator;

import com.poc.accountms.common.AccountValidationUtil;
import com.poc.accountms.config.DataProviderConfig;
import com.poc.accountms.model.AccountValidationResponse;
import com.poc.accountms.model.DataProviderRequest;
import com.poc.accountms.model.DataProviderResponse;
import com.poc.accountms.service.DataProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AccountValidationAggregator {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountValidationAggregator.class);
    private final DataProviderService dataProviderService;

    @Autowired
    private DataProviderConfig dataProviderConfig;

    public AccountValidationAggregator(DataProviderService dataProviderService) {
        this.dataProviderService = dataProviderService;
    }

    public Object validateAccount(final String accountNumber, final List<String> dataProviderNames) {
        LOGGER.info("## inside method validateAccount of class AccountValidationAggregator##");
        Map<String,List<AccountValidationResponse>> result =new HashMap<>();
        List<AccountValidationResponse> accountValidationResponseList= new ArrayList<>();
        DataProviderRequest dataProviderRequest = AccountValidationUtil.createDataProviderRequest(accountNumber);
        if(dataProviderNames != null && !dataProviderNames.isEmpty()){
            for(String dataProvierName : dataProviderNames){
                String url=AccountValidationUtil.getUriForDataProvider(dataProvierName,dataProviderConfig.getProviders());
                DataProviderResponse dataProviderResponse = dataProviderService.isAccountValid(dataProviderRequest,url);
                accountValidationResponseList.addAll(AccountValidationUtil.createAccountValidationResponse(dataProviderResponse.isValid(),dataProvierName));
            }
        }else{
            for(DataProviderConfig.Provider provider : dataProviderConfig.getProviders()){
                DataProviderResponse dataProviderResponse = dataProviderService.isAccountValid(dataProviderRequest,provider.getUrl());
                accountValidationResponseList.addAll(AccountValidationUtil.createAccountValidationResponse(dataProviderResponse.isValid(),provider.getName()));
            }
        }
        result.put("result",accountValidationResponseList);
        return result;
    }
}
