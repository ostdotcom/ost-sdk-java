package com.ost.services;

import com.google.gson.JsonObject;
import com.ost.lib.OSTRequestClient;

import java.io.IOException;
import java.util.Map;

public class RedeemableSkus extends OSTAPIService {
    private static String servicePrefix = "/redeemable-skus";
    private static String serviceSuffix = "";

    public RedeemableSkus(OSTRequestClient ostRequestClient) {
        super(ostRequestClient, servicePrefix, serviceSuffix);
    }

    /**
     * Get all redeemable SKUs
     * @param params Request Params
     * @return API Response
     */
    public JsonObject getList( Map<String,Object> params ) throws IOException, InvalidParameter, MissingParameter {
        String resource = this.urlPrefix + "/";
        return this.request.get(resource, params);
    }

    /**
     * Get a redeemable SKU
     * @param params Request Params
     * @return API Response
     */
    public JsonObject get( Map<String,Object> params ) throws MissingParameter, IOException, InvalidParameter {
        String resource = this.urlPrefix + "/" + this.getRedeemableSkuId( params ) + "/";;
        return this.request.get(resource, params);
    }
}
