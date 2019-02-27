package com.ost.services;

import com.google.gson.JsonObject;
import com.ost.lib.OSTRequestClient;

import java.io.IOException;
import java.util.Map;

public class Transactions extends OSTAPIService {
    private static String servicePrefix = "/users";
    private static String serviceSuffix = "/transactions";

    public Transactions(OSTRequestClient ostRequestClient) {
        super(ostRequestClient, servicePrefix, serviceSuffix);
    }

    /**
     * execute transaction from company
     * @param params Request Params
     * @return API Response
     */
    public JsonObject execute( Map<String,Object> params ) throws IOException, InvalidParameter, MissingParameter {
        String resource = this.urlPrefix + "/" + this.getUserId( params ) + this.urlSuffix + "/";
        return this.request.post(resource, params);
    }

    /**
     * Get all transactions for a user
     * @param params Request Params
     * @return API Response
     */
    public JsonObject getList( Map<String,Object> params ) throws IOException, InvalidParameter, MissingParameter {
        String resource = this.urlPrefix + "/" + this.getUserId( params ) + this.urlSuffix + "/";
        return this.request.get(resource, params);
    }

    /**
     * Get a transaction info
     * @param params Request Params
     * @return API Response
     */
    public JsonObject get( Map<String,Object> params ) throws MissingParameter, IOException, InvalidParameter {
        String resource = this.urlPrefix + "/" + this.getUserId( params ) + this.urlSuffix + "/" + this.getTransactionId(params);
        return this.request.get(resource, params);
    }
}
