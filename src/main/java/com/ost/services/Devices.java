package com.ost.services;

import com.google.gson.JsonObject;
import com.ost.lib.OSTRequestClient;

import java.io.IOException;
import java.util.Map;

public class Devices extends OSTAPIService {
    private static String servicePrefix = "/users";
    private static String serviceSuffix = "/devices";

    public Devices(OSTRequestClient ostRequestClient) {
        super(ostRequestClient, servicePrefix, serviceSuffix);
    }

    /**
     * Associate a Device with a User
     * @param params Request Params
     * @return API Response
     */
    public JsonObject create( Map<String,Object> params ) throws IOException, InvalidParameter, MissingParameter {
        String resource = this.urlPrefix + "/" + this.getUserId( params ) + this.urlSuffix + "/";
        return this.request.post(resource, params);
    }

    /**
     * List devices of a user
     * @param params Request Params
     * @return API Response
     */
    public JsonObject getList( Map<String,Object> params ) throws MissingParameter, IOException, InvalidParameter {
        String resource = this.urlPrefix + "/" + this.getUserId( params ) + this.urlSuffix + "/";
        return this.request.get(resource, params);
    }

    /**
     * Get device of a user
     * @param params Request Params
     * @return API Response
     */
    public JsonObject get( Map<String,Object> params ) throws MissingParameter, IOException, InvalidParameter {
        String resource = this.urlPrefix + "/" + this.getUserId( params ) + this.urlSuffix + "/" + this.getDeviceAddress(params) + "/";
        return this.request.get(resource, params);
    }
}















