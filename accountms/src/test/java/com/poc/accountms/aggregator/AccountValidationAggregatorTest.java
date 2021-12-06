package com.poc.accountms.aggregator;

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
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccountValidationAggregatorTest {
    @InjectMocks
    private AccountValidationAggregator accountValidationAggregator;
    @Mock
    private DataProviderConfig dataProviderConfig;
    @Mock
    private DataProviderService dataProviderService;

    private AccountValidationRequest accountValidationRequest =null;

    @BeforeEach
    void setupThis(){
        accountValidationRequest = AccountValidateTestUtil.createAccountValidationRequest();
    }

    @Test
    public void validateAccountTest(){
        AccountValidationRequest accountValidationRequest = AccountValidateTestUtil.createAccountValidationRequestWithEmptyProvider();
        DataProviderRequest dataProviderRequest = AccountValidationUtil.createDataProviderRequest(accountValidationRequest.getAccountNumber());
        DataProviderResponse dataProviderResponse =new DataProviderResponse();
        dataProviderResponse.setValid(true);

        List<DataProviderConfig.Provider> providers = AccountValidateTestUtil.getConfigProviderList();

        ReflectionTestUtils.setField(accountValidationAggregator,"dataProviderConfig",dataProviderConfig);
        ReflectionTestUtils.setField(accountValidationAggregator,"dataProviderService",dataProviderService);
        when(dataProviderConfig.getProviders()).thenReturn(providers);
        when(dataProviderService.isAccountValid(Mockito.any(),Mockito.anyString())).thenReturn(dataProviderResponse);
        Object object =  accountValidationAggregator.validateAccount(accountValidationRequest.getAccountNumber(),accountValidationRequest.getProviders());

        Map<String,List<AccountValidationResponse>> result =(Map<String,List<AccountValidationResponse>>)object;
        Assertions.assertNotNull(result);

        List<AccountValidationResponse> accountValidationResponseList= result.get("result");
        Assertions.assertNotNull(accountValidationResponseList);
        Assertions.assertEquals(2,accountValidationResponseList.size());

        Mockito.verify(dataProviderService,Mockito.times(2)).isAccountValid(Mockito.any(),Mockito.anyString());
    }

}
