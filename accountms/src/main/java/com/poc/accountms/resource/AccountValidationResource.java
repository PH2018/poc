package com.poc.accountms.resource;

import com.poc.accountms.aggregator.AccountValidationAggregator;
import com.poc.accountms.common.AccountValidationConstant;
import com.poc.accountms.model.AccountValidationRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@Validated
public class AccountValidationResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountValidationResource.class);
    private final AccountValidationAggregator accountValidationAggregator;

    public AccountValidationResource(AccountValidationAggregator accountValidationAggregator) {
        this.accountValidationAggregator = accountValidationAggregator;
    }

    @PutMapping(value= AccountValidationConstant.VALIDATE_ACCOUNT_URI)
    public Object validateAccount(@Valid @RequestBody AccountValidationRequest accountValidationRequest){
        LOGGER.info("## Inside AccountValidationResource ##");
        return accountValidationAggregator.validateAccount(accountValidationRequest.getAccountNumber(),accountValidationRequest.getProviders());
    }

    @GetMapping(value= "/accounts")
    public ResponseEntity getAccounts(){
        LOGGER.info("## Inside AccountValidationResource ##");
        return new ResponseEntity<>("Successful test account",HttpStatus.OK);
    }
}