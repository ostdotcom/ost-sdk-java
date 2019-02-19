package com.ost.services;

import com.google.gson.JsonObject;
import com.ost.lib.OSTRequestClient;

import java.io.IOException;
import java.util.Map;

public class PricePoints extends OSTAPIService {
    private static String servicePrefix = "/price-points";
    private static String serviceSuffix = "";

    public PricePoints(OSTRequestClient ostRequestClient) {
        super(ostRequestClient, servicePrefix, serviceSuffix);
    }

    /**
     * Get Price Points
     * @param params Request Params
     * @return API Response
     */
    public JsonObject get( Map<String,Object> params ) throws IOException {
        String resource = this.urlPrefix + "/";
        return this.request.get(resource, params);
    }
}