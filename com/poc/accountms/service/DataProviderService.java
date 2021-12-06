package com.poc.accountms.service;

import com.poc.accountms.model.DataProviderRequest;
import com.poc.accountms.model.DataProviderResponse;

public interface DataProviderService {
    DataProviderResponse isAccountValid(final DataProviderRequest dataProviderRequest, final String url);
}
