package com.ost.services.v1;

import com.google.gson.JsonObject;
import com.ost.lib.OSTRequestClient;
import com.ost.services.OSTAPIService;

import java.io.IOException;
import java.util.Map;

public class Token extends OSTAPIService {
    private static String servicePrefix = "/token";

    public Token(OSTRequestClient ostRequestClient) {
        super(ostRequestClient, servicePrefix);
    }

    /**
     * Get transaction details
     * @param params Request Params
     * @return API Response
     */
    public JsonObject get( Map<String,Object> params ) throws IOException {
        String resource = this.urlPrefix + "/";
        return this.request.get(resource, params);
    }
}
