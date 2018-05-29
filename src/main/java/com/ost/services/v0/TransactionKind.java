package com.ost.services.v0;

import com.google.gson.JsonObject;
import com.ost.lib.OSTRequestClient;
import com.ost.services.OSTAPIService;

import java.io.IOException;
import java.util.Map;

public class TransactionKind extends OSTAPIService {

    static String servicePrefix = "/transaction-types";
    public TransactionKind(OSTRequestClient ostRequestClient) {
        super(ostRequestClient, servicePrefix);
    }

    /**
     * @param params Request Params
     * @return API Response
     */
    public JsonObject create( Map<String,Object> params ) throws IOException {
        String resource = this.urlPrefix + "/create";
        return this.request.post(resource, params);
    }

    /**
     * @param params Request Params
     * @return API Response
     */
    public JsonObject edit( Map<String,Object> params ) throws IOException {
        String resource = this.urlPrefix + "/edit";
        return this.request.post(resource, params);
    }

    /**
     * @param params Request Params
     * @return API Response
     */
    public JsonObject list( Map<String,Object> params ) throws IOException {
        String resource = this.urlPrefix + "/list";
        return this.request.get(resource, params);
    }

    /**
     * @param params Request Params
     * @return API Response
     */
    public JsonObject execute( Map<String,Object> params ) throws IOException {
        String resource = this.urlPrefix + "/execute";
        return this.request.post(resource, params);
    }

    /**
     * @param params Request Params
     * @return API Response
     */
    public JsonObject status( Map<String,Object> params ) throws IOException {
        String resource = this.urlPrefix + "/status";
        return this.request.post(resource, params);
    }
}
