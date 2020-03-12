package com.ost.services;

import com.google.gson.JsonObject;
import com.ost.lib.OSTRequestClient;

import java.io.IOException;
import java.util.Map;

public class Redemptions extends OSTAPIService {
    private static String servicePrefix = "/users";
    private static String serviceSuffix = "/redemptions";

    public Redemptions(OSTRequestClient ostRequestClient) {
        super(ostRequestClient, servicePrefix, serviceSuffix);
    }

    /**
     * Get all redemptions for a user
     * @param params Request Params
     * @return API Response
     */
    public JsonObject getList( Map<String,Object> params ) throws IOException, InvalidParameter, MissingParameter {
        String resource = this.urlPrefix + "/" + this.getUserId( params ) + this.urlSuffix + "/";
        return this.request.get(resource, params);
    }

    /**
     * Get a redemption info
     * @param params Request Params
     * @return API Response
     */
    public JsonObject get( Map<String,Object> params ) throws MissingParameter, IOException, InvalidParameter {
        String resource = this.urlPrefix + "/" + this.getUserId( params ) + this.urlSuffix + "/" + this.getRedemptionId(params);
        return this.request.get(resource, params);
    }
}
