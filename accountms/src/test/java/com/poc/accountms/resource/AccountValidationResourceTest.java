package com.poc.accountms.resource;

import com.poc.accountms.aggregator.AccountValidationAggregator;
import com.poc.accountms.common.AccountValidateTestUtil;
import com.poc.accountms.common.AccountValidationUtil;
import com.poc.accountms.config.DataProviderConfig;
import com.poc.accountms.model.AccountValidationRequest;
import com.poc.accountms.model.AccountValidationResponse;
import com.poc.accountms.model.DataProviderRequest;
import com.poc.accountms.model.DataProviderResponse;
import com.poc.accountms.service.DataProviderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountValidationResourceTest {

    private AccountValidationRequest accountValidationRequest =null;
    @InjectMocks
    private AccountValidationResource accountValidationResource;
    @Mock
    private AccountValidationAggregator accountValidationAggregator;
    @Mock
    private DataProviderConfig dataProviderConfig;
    @Mock
    private DataProviderService dataProviderService;


    @BeforeEach
    void setupThis(){
        accountValidationRequest = AccountValidateTestUtil.createAccountValidationRequest();
    }

    @Test
    public void validateAccountTest(){
        Map<String,List<AccountValidationResponse>> result = AccountValidateTestUtil.createAccountValidateResponseList();
        Object resultObj = result;
        AccountValidationRequest accountValidationRequest = AccountValidateTestUtil.createAccountValidationRequestWithEmptyProvider();
        DataProviderRequest dataProviderRequest = AccountValidationUtil.createDataProviderRequest(accountValidationRequest.getAccountNumber());
        DataProviderResponse dataProviderResponse =new DataProviderResponse();
        dataProviderResponse.setValid(true);
        List<DataProviderConfig.Provider> providers = AccountValidateTestUtil.getConfigProviderList();

        ReflectionTestUtils.setField(accountValidationResource,"accountValidationAggregator",accountValidationAggregator);
        ReflectionTestUtils.setField(accountValidationAggregator,"dataProviderConfig",dataProviderConfig);
        ReflectionTestUtils.setField(accountValidationAggregator,"dataProviderService",dataProviderService);

       when(accountValidationAggregator.validateAccount(Mockito.anyString(),Mockito.any())).thenReturn(resultObj);
         resultObj = accountValidationResource.validateAccount(accountValidationRequest);
        Assertions.assertNotNull(resultObj);
    }

}
