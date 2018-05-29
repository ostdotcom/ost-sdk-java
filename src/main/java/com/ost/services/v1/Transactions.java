package com.ost.services.v1;

import com.google.gson.JsonObject;
import com.ost.lib.OSTRequestClient;
import com.ost.services.OSTAPIService;

import java.io.IOException;
import java.util.Map;

public class Transactions extends OSTAPIService {
    private static String servicePrefix = "/transactions";

    public Transactions(OSTRequestClient ostRequestClient) {
        super(ostRequestClient, servicePrefix);
    }

    /**
     * Execute transaction
     * @param params Request Params
     * @return API Response
     */
    public JsonObject execute( Map<String,Object> params ) throws IOException {
        String resource = this.urlPrefix + "/";
        return this.request.post(resource, params);
    }

    /**
     * Get transaction details
     * @param params Request Params
     * @return API Response
     */
    public JsonObject get( Map<String,Object> params ) throws MissingParameter, IOException {
        String resource = this.urlPrefix + "/" + this.getId( params ) + "/";
        return this.request.get(resource, params);
    }

    /**
     * List of transactions
     * @param params Request Params
     * @return API Response
     */
    public JsonObject list( Map<String,Object> params ) throws IOException {
        String resource = this.urlPrefix + "/";
        return this.request.get(resource, params);
    }

}
