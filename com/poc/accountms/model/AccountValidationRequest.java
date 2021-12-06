package com.poc.accountms.model;

import lombok.*;

import java.util.List;
import javax.validation.constraints.NotEmpty;
@Getter
@Setter
@NoArgsConstructor
public class AccountValidationRequest {
    @NotEmpty(message = "Account Number must not be empty")
    private String accountNumber;
    private List<String> providers;
}
