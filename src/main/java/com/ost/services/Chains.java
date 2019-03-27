package com.ost.services;

import com.google.gson.JsonObject;
import com.ost.lib.OSTRequestClient;

import java.io.IOException;
import java.util.Map;

public class Chains extends OSTAPIService {
    private static String servicePrefix = "/chains";
    private static String serviceSuffix = "";

    public Chains(OSTRequestClient ostRequestClient) {
        super(ostRequestClient, servicePrefix, serviceSuffix);
    }

    /**
     * Get chain details
     * @param params Request Params
     * @return API Response
     */
    public JsonObject get( Map<String,Object> params ) throws MissingParameter, IOException, InvalidParameter {
        String resource = this.urlPrefix + "/" + this.getChainId( params ) + "/";
        return this.request.get(resource, params);
    }
}
