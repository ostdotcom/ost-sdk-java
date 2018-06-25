package com.ost.services.v1_1;

import com.google.gson.JsonObject;
import com.ost.lib.OSTRequestClient;
import com.ost.services.OSTAPIService;

import java.io.IOException;
import java.util.Map;


public class Ledger extends OSTAPIService {
	private static String servicePrefix = "/ledger";

	public Ledger(OSTRequestClient ostRequestClient) {
		super(ostRequestClient, servicePrefix);
	}

	/**
     * Get ledger for user
     * @param params Request Params
     * @return API Response
     */
    public JsonObject get( Map<String,Object> params ) throws MissingParameter, IOException {
        String resource = this.urlPrefix + "/" + this.getId( params ) + "/";
        return this.request.get(resource, params);
    }
}