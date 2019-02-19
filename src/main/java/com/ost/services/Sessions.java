package com.ost.services;

import com.google.gson.JsonObject;
import com.ost.lib.OSTRequestClient;

import java.io.IOException;
import java.util.Map;

public class Sessions extends OSTAPIService {
    private static String servicePrefix = "/users";
    private static String serviceSuffix = "/sessions";

    public Sessions(OSTRequestClient ostRequestClient) {
        super(ostRequestClient, servicePrefix, serviceSuffix);
    }

    /**
     * Get session of a user
     * @param params Request Params
     * @return API Response
     */
    public JsonObject get( Map<String,Object> params ) throws MissingParameter, IOException, InvalidParameter {
        String resource = this.urlPrefix + "/" + this.getUserId( params ) + this.urlSuffix + "/" + this.getSessionAddress(params) + "/";
        return this.request.get(resource, params);
    }

    /**
     * List sessions of a user
     * @param params Request Params
     * @return API Response
     */
    public JsonObject getList( Map<String,Object> params ) throws MissingParameter, IOException, InvalidParameter {
        String resource = this.urlPrefix + "/" + this.getUserId( params ) + this.urlSuffix + "/";
        return this.request.get(resource, params);
    }
}
