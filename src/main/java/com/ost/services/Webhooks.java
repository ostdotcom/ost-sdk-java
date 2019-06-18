package com.ost.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ost.lib.OSTRequestClient;
import okio.Buffer;
import okio.ByteString;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import static com.google.common.base.Charsets.UTF_8;

public class Webhooks extends OSTAPIService {
    private static String servicePrefix = "/webhooks";
    private static String serviceSuffix = "";
    private static final String HMAC_SHA256 = "HmacSHA256";

    public Webhooks(OSTRequestClient ostRequestClient) {
        super(ostRequestClient, servicePrefix, serviceSuffix);
    }

    /**
     * Create webhook
     * @param params Request Params
     * @return API Response
     */
    public JsonObject create( Map<String,Object> params ) throws IOException {
        String resource = this.urlPrefix + "/";
        return this.request.post(resource, params);
    }

    /**
     * Update webhook
     * @param params Request Params
     * @return API Response
     */
    public JsonObject update( Map<String,Object> params ) throws IOException, InvalidParameter, MissingParameter {
        String resource = this.urlPrefix + "/" + this.getWebhookId( params ) + "/";
        return this.request.post(resource, params);
    }

    /**
     * Get webhook
     * @param params Request Params
     * @return API Response
     */
    public JsonObject get( Map<String,Object> params ) throws MissingParameter, IOException, InvalidParameter {
        String resource = this.urlPrefix + "/" +  this.getWebhookId( params ) + "/";
        return this.request.get(resource, params);
    }

    /**
     * Get list of webhooks
     * @param params Request Params
     * @return API Response
     */
    public JsonObject getList( Map<String,Object> params ) throws  IOException {
        String resource = this.urlPrefix + "/";
        return this.request.get(resource, params);
    }

    /**
     * Delete webhook
     * @param params Request Params
     * @return API Response
     */
    public JsonObject deleteWebhook( Map<String,Object> params ) throws MissingParameter, InvalidParameter, IOException {
        String resource = this.urlPrefix + "/" +  this.getWebhookId( params ) + "/";
        return this.request.delete(resource, params);
    }

    /**
     * Verify signature
     * @param version
     * @param stringifiedData
     * @param requestTimestamp
     * @param signature
     * @return {boolean}
     */
    public boolean verifySignature(String version, String stringifiedData, String requestTimestamp, String signature, String webhookSecret) {

        if(!(stringifiedData instanceof String)){
            Gson gsonObj = new Gson();
            stringifiedData = gsonObj.toJson(stringifiedData);
        }

        Buffer hmacInputBuffer = new Buffer();
        hmacInputBuffer.writeUtf8(requestTimestamp);
        hmacInputBuffer.writeByte('.');
        hmacInputBuffer.writeUtf8(version);
        hmacInputBuffer.writeByte('.');
        hmacInputBuffer.writeUtf8(stringifiedData);

        SecretKeySpec keySpec = new SecretKeySpec( webhookSecret.getBytes( UTF_8 ), HMAC_SHA256);
        Mac mac;
        try {
            mac = Mac.getInstance(HMAC_SHA256);
            mac.init(keySpec);
        } catch ( NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        } catch (InvalidKeyException e) {
            throw new IllegalStateException(e);
        }

        byte[] bytes = hmacInputBuffer.readByteArray();
        byte[] result = mac.doFinal(bytes);

        String computedSignature = ByteString.of(result).hex();

        return signature.indexOf(computedSignature) >= 0;
    }

}
