package com.serhat.googlesearch.api;

import com.serhat.googlesearch.helper.Constants;

public class ApiUtils {
    public static final String BASE_URL = Constants.BASE_URL;

    public static ApiInterface getApiInterface() {
        return ApiClient.getClient(BASE_URL).create(ApiInterface.class);
    }
}
