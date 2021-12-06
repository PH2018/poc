package com.poc.accountms.resource;

import com.poc.accountms.common.AccountValidateTestUtil;
import com.poc.accountms.model.AccountValidationRequest;
import com.poc.accountms.model.AccountValidationResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AccountValidationIntegrationTest {

    private AccountValidationRequest accountValidationRequest =null;

    @BeforeEach
    void setupThis(){
        accountValidationRequest = AccountValidateTestUtil.createAccountValidationRequest();
    }

    @Autowired
    private AccountValidationResource accountValidationResource;

    @Test
    public void validateAccountTest(){
        Object object = accountValidationResource.validateAccount(accountValidationRequest);
        Assertions.assertNotNull(object);

        Map<String,List<AccountValidationResponse>> result =(Map<String,List<AccountValidationResponse>>)object;
        Assertions.assertNotNull(result);

        List<AccountValidationResponse> accountValidationResponseList= result.get("result");
        Assertions.assertNotNull(accountValidationResponseList);
        Assertions.assertEquals(2,accountValidationResponseList.size());
    }

    @Test
    public void validateAccountWithEmptyAccountNumberTest(){
        accountValidationRequest.setAccountNumber("");
        ConstraintViolationException thrown = Assertions.assertThrows(ConstraintViolationException.class, () -> {
            accountValidationResource.validateAccount(accountValidationRequest);
        }, "ConstraintViolationException was expected");
        Assertions.assertEquals("validateAccount.accountValidationRequest.accountNumber: Account Number must not be empty", thrown.getMessage());
    }


}
