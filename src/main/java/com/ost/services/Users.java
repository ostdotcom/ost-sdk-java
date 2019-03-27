package com.ost.services;

import com.google.gson.JsonObject;
import com.ost.lib.OSTRequestClient;

import java.io.IOException;
import java.util.Map;

public class Users extends OSTAPIService {
    private static String servicePrefix = "/users";
    private static String serviceSuffix = "";

    public Users(OSTRequestClient ostRequestClient) {
        super(ostRequestClient, servicePrefix, serviceSuffix);
    }

    /**
     * Create new User
     * @param params Request Params
     * @return API Response
     */
    public JsonObject create( Map<String,Object> params ) throws IOException {
        String resource = this.urlPrefix + "/";
        return this.request.post(resource, params);
    }

    /**
     * List Users
     * @param params Request Params
     * @return API Response
     */
    public JsonObject getList( Map<String,Object> params ) throws IOException {
        String resource = this.urlPrefix + "/";
        return this.request.get(resource, params);
    }

    /**
     * Get user details
     * @param params Request Params
     * @return API Response
     */
    public JsonObject get( Map<String,Object> params ) throws MissingParameter, IOException, InvalidParameter {
        String resource = this.urlPrefix + "/" + this.getUserId( params ) + "/";
        return this.request.get(resource, params);
    }
}
