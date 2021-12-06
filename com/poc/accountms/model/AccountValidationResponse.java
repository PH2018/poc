package com.poc.accountms.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountValidationResponse {
    private String providerName;
    private boolean isValid;
}
