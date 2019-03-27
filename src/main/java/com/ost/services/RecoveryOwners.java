package com.ost.services;

import com.google.gson.JsonObject;
import com.ost.lib.OSTRequestClient;

import java.io.IOException;
import java.util.Map;

public class RecoveryOwners extends OSTAPIService {
    private static String servicePrefix = "/users";
    private static String serviceSuffix = "/recovery-owners";

    public RecoveryOwners(OSTRequestClient ostRequestClient) {
        super(ostRequestClient, servicePrefix, serviceSuffix);
    }

    /**
     * Get recovery owner
     * @param params Request Params
     * @return API Response
     */
    public JsonObject get( Map<String,Object> params ) throws IOException, InvalidParameter, MissingParameter {
        String resource = this.urlPrefix + "/" + this.getUserId(params) + this.urlSuffix + "/" + this.getRecoveryOwnerAddress(params);
        return this.request.get(resource, params);
    }
}

