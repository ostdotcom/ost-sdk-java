package com.ost;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.ost.services.Manifest;
import com.ost.services.OSTAPIService;
import com.ost.services.Webhooks;
import okio.Buffer;
import okio.ByteString;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.google.common.base.Charsets.UTF_8;

public class Test {
    private static final String HMAC_SHA256 = "HmacSHA256";

    public static void main(String args[]) throws OSTAPIService.MissingParameter, IOException, OSTAPIService.InvalidParameter {

        HashMap <String,Object> sdkConfig = new HashMap<String,Object>();
        sdkConfig.put("apiEndpoint","http://developmentost.com:7001/testnet/v2");
        sdkConfig.put("apiKey","d2e5ce61e385b444b9439a57990664ab");
        sdkConfig.put("apiSecret","92e540a9086da42efaa971dfc994ac9a5070a4ebe3eb0fabaa5e5a35478a8cf0");

        HashMap <String,Object> nestedparam = new HashMap<String,Object>();
// This is the timeout in seconds for which the socket connection will remain open
// The value of timeout will always be of type long
        nestedparam.put("timeout", (long) 60);
        sdkConfig.put("config", nestedparam);

        OSTSDK ostObj = new OSTSDK(sdkConfig);
        Manifest services = (Manifest) ostObj.services;
        Webhooks webhooksService = services.webhooks;

//        String version = System.getProperty("java.version");
//        String randomUrl = "https://www.yourdomain" + (System.currentTimeMillis() / 1000) + "-" + version + ".com";
//
        HashMap<String, Object> params = new HashMap<String, Object>();
//        ArrayList<String> arrayList = new ArrayList<String>();
//        arrayList.add("devices/authorization_initiate");
//        params.put("url", randomUrl);
//        params.put("status", "active");
//        params.put("topics", arrayList);
//        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("webhook_id", "b036aff5-75a3-466d-a20c-a956b198fd14");
        JsonObject response = webhooksService.deleteWebhook( params );
        System.out.println("response: " + response.toString() );

    }

    public static boolean verifySignature( String version, String stringifiedData, String requestTimestamp, String signature ) {

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

        String webhookSecret = "09121ae7614856777fa36d63aca828e0ef14be77fb48fa149e0c0b50fec847a7";

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

        System.out.println("The computedSignature is : "+ computedSignature);

        return signature.indexOf(computedSignature) >= 0;
    }
}