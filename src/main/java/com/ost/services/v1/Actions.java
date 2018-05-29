package com.ost.services.v1;

import com.google.gson.JsonObject;
import com.ost.lib.OSTRequestClient;
import com.ost.services.OSTAPIService;

import java.io.IOException;
import java.util.Map;

public class Actions extends OSTAPIService {

    static String servicePrefix = "/actions";
    public Actions(OSTRequestClient ostRequestClient) {
        super(ostRequestClient, servicePrefix);
    }

    /**
     * @param params Request Params
     * @return API Response
     */
    public JsonObject create( Map<String,Object> params ) throws IOException {
        String resource = this.urlPrefix + "/";
        return this.request.post(resource, params);
    }

    /**
     * @param params Request Params
     * @return API Response
     */
    public JsonObject edit( Map<String,Object> params ) throws MissingParameter, IOException {
        String resource = this.urlPrefix + "/" + this.getId( params ) + "/";
        return this.request.post(resource, params);
    }

    /**
     * @param params Request Params
     * @return API Response
     */
    public JsonObject list( Map<String,Object> params ) throws IOException {
        String resource = this.urlPrefix + "/";
        return this.request.get(resource, params);
    }

    /**
     * @param params Request Params
     * @return API Response
     */
    public JsonObject get( Map<String,Object> params ) throws MissingParameter, IOException {
        String resource = this.urlPrefix + "/" + this.getId( params ) + "/";
        return this.request.get(resource, params);
    }

}
