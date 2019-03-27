package com.ost.services;

import com.google.gson.JsonObject;
import com.ost.lib.OSTRequestClient;

import java.io.IOException;
import java.util.Map;

public class Balance extends OSTAPIService {
    private static String servicePrefix = "/users";
    private static String serviceSuffix = "/balance";

    public Balance(OSTRequestClient ostRequestClient) {
        super(ostRequestClient, servicePrefix, serviceSuffix);
    }

    /**
     * Get User's Balance
     * @param params Request Params
     * @return API Response
     */
    public JsonObject get( Map<String,Object> params ) throws IOException, InvalidParameter, MissingParameter {
        String resource = this.urlPrefix + "/" + this.getUserId(params) + this.urlSuffix + "/";
        return this.request.get(resource, params);
    }
}

