package com.ost.services;

import com.google.gson.JsonObject;
import com.ost.lib.OSTRequestClient;

import java.io.IOException;
import java.util.Map;

public class Rules extends OSTAPIService {
    private static String servicePrefix = "/rules";
    private static String serviceSuffix = "";

    public Rules(OSTRequestClient ostRequestClient) {
        super(ostRequestClient, servicePrefix, serviceSuffix);
    }

    /**
     * List all Rules
     * @param params Request Params
     * @return API Response
     */
    public JsonObject getList( Map<String,Object> params ) throws IOException {
        String resource = this.urlPrefix + "/";
        return this.request.get(resource, params);
    }
}

