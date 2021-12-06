package com.poc.accountms.common;

import com.poc.accountms.config.DataProviderConfig;
import com.poc.accountms.model.AccountValidationRequest;
import com.poc.accountms.model.AccountValidationResponse;
import com.poc.accountms.model.DataProviderRequest;
import com.poc.accountms.model.DataProviderResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

public class AccountValidateTestUtil {

    public static AccountValidationRequest createAccountValidationRequest(){
        AccountValidationRequest accountValidationRequest = new AccountValidationRequest();
        accountValidationRequest.setAccountNumber("12345678");
        List<String> providerNames = Arrays.asList("provider1","provider2");
        accountValidationRequest.setProviders(providerNames);
        return accountValidationRequest;
    }

    public static AccountValidationRequest createAccountValidationRequestWithEmptyProvider(){
        AccountValidationRequest accountValidationRequest = new AccountValidationRequest();
        accountValidationRequest.setAccountNumber("12345678");
        return accountValidationRequest;
    }

    public static DataProviderRequest createDataProviderRequest(){
        DataProviderRequest dataProviderRequest = new DataProviderRequest();
        dataProviderRequest.setAccountNumber("12345678");
        return dataProviderRequest;
    }

    public static DataProviderResponse creteDataProviderResponse(){
        DataProviderResponse dataProviderResponse = new DataProviderResponse();
        dataProviderResponse.setValid(true);
        return dataProviderResponse;
    }

    public static DataProviderConfig createProvider() {
        DataProviderConfig DataProviderConfig = new DataProviderConfig();
        DataProviderConfig.setProviders(getConfigProviderList());
        return DataProviderConfig;
    }

    public static List<DataProviderConfig.Provider> getConfigProviderList(){
        DataProviderConfig.Provider provider1 = createConfigProivder("provider1","https://provider1.com/v1/api/account/validate");
        DataProviderConfig.Provider provider2 = createConfigProivder("provider2","https://provider2.com/v1/api/account/validate");
        List<DataProviderConfig.Provider> configProivders = Arrays.asList(provider1,provider2);
        return configProivders;
    }

    public static DataProviderConfig.Provider createConfigProivder(String name, String url){
        DataProviderConfig.Provider provider = new DataProviderConfig.Provider();
        provider.setName(name);
        provider.setUrl(url);
        return provider;
    }

    public static Map<String,List<AccountValidationResponse>> createAccountValidateResponseList(){
        Map<String,List<AccountValidationResponse>> result = new HashMap<>();
        List<AccountValidationResponse> accountValidationResponseList= new ArrayList<>();

        AccountValidationResponse accountValidationResponse = new AccountValidationResponse();
        accountValidationResponse.setProviderName("provider1");
        accountValidationResponse.setValid(true);
        accountValidationResponseList.add(accountValidationResponse);

        result.put("result",accountValidationResponseList);
        return result;
    }











}
