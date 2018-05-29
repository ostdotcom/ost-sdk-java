package com.ost.services.v0;

import com.google.gson.JsonObject;
import com.ost.lib.OSTRequestClient;
import com.ost.services.OSTAPIService;

import java.io.IOException;
import java.util.Map;

public class User extends OSTAPIService {

    static String servicePrefix = "/users";
    public User(OSTRequestClient ostRequestClient) {
        super(ostRequestClient, servicePrefix);
    }

    /**
     * @param params Request Params
     * @return API Response
     */
    public JsonObject create(Map<String,Object> params) throws IOException {
        String resource = this.urlPrefix + "/create";
        return this.request.post(resource, params);
    }

    /**
     * @param params Request Params
     * @return API Response
     */
    public JsonObject edit(Map<String,Object> params) throws IOException {
        String resource = this.urlPrefix + "/edit";
        return this.request.post(resource, params);
    }

    /**
     * @param params Request Params
     * @return API Response
     */
    public JsonObject list(Map<String,Object> params) throws IOException {
        String resource = this.urlPrefix + "/list";
        return this.request.get(resource, params);
    }

    /**
     * @param params Request Params
     * @return API Response
     */
    public JsonObject airdropTokens(Map<String,Object> params) throws IOException {
        String resource = this.urlPrefix + "/airdrop/drop";
        return this.request.post(resource, params);
    }

    /**
     * @param params Request Params
     * @return API Response
     */
    public JsonObject airdropStatus(Map<String,Object> params) throws IOException {
        String resource = this.urlPrefix + "/airdrop/status";
        return this.request.get(resource, params);
    }
}
