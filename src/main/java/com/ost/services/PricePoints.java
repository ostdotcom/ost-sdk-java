package com.ost.services;

import com.google.gson.JsonObject;
import com.ost.lib.OSTRequestClient;

import java.io.IOException;
import java.util.Map;

public class PricePoints extends OSTAPIService {
    private static String servicePrefix = "/chains";
    private static String serviceSuffix = "/price-points";

    public PricePoints(OSTRequestClient ostRequestClient) {
        super(ostRequestClient, servicePrefix, serviceSuffix);
    }

    /**
     * Get Price Points
     * @param params Request Params
     * @return API Response
     */
    public JsonObject get( Map<String,Object> params ) throws IOException, InvalidParameter, MissingParameter {
        String resource = this.urlPrefix + "/" + this.getChainId(params) + this.urlSuffix + "/";
        return this.request.get(resource, params);
    }
}