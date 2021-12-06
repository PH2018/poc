package com.poc.accountms.common;

import com.poc.accountms.config.DataProviderConfig;
import com.poc.accountms.model.AccountValidationResponse;
import com.poc.accountms.model.DataProviderRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountValidationUtil {

    public static DataProviderRequest createDataProviderRequest(String accountNumber) {
        DataProviderRequest dataProviderRequest = new DataProviderRequest();
        dataProviderRequest.setAccountNumber(accountNumber);
        return dataProviderRequest;
    }

    public static String getUriForDataProvider(String dataProvierName, List<DataProviderConfig.Provider> providers){
        String url="";
        for(DataProviderConfig.Provider provider : providers){
            if(dataProvierName.equalsIgnoreCase(provider.getName())){
                url=provider.getUrl();
                break;
            }
        }
        return url;
    }

    public static List<AccountValidationResponse> createAccountValidationResponse(boolean isValid,String providerName) {
        List<AccountValidationResponse> accountValidationResponseList= new ArrayList<>();
        AccountValidationResponse accountValidationResponse = new AccountValidationResponse();
        accountValidationResponse.setValid(isValid);
        accountValidationResponse.setProviderName(providerName);
        accountValidationResponseList.add(accountValidationResponse);
        return accountValidationResponseList;
    }
}
