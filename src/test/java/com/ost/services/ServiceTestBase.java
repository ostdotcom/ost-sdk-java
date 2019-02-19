package com.ost.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ost.OSTSDK;
import org.junit.After;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Random;

public abstract class ServiceTestBase {
    private OSTSDK ostsdk;
    private HashMap<String, Object> apiParams;
    protected com.ost.services.OSTServiceManifest services;
    private OSTAPIService service;

    public void setUp() throws Exception {

        String apiKey = System.getenv("OST_KIT_API_KEY");
        if ( null == apiKey ) {
            throw new Exception("Environment Variable OST_KIT_API_KEY is not set.");
        }

        String apiSecret = System.getenv("OST_KIT_API_SECRET");
        if ( null == apiSecret ) {
            throw new Exception("Environment Variable OST_KIT_API_SECRET is not set.");
        }


        String apiEndPoint = System.getenv("OST_KYC_API_ENDPOINT");
        if ( null == apiEndPoint ) {
            throw new Exception("apiEndPoint can not be null.");
        }

        apiParams = new HashMap<String, Object>();
        apiParams.put( "apiKey", apiKey);
        apiParams.put( "apiSecret", apiSecret);
        apiParams.put( "apiEndpoint", apiEndPoint);

        ostsdk = new OSTSDK(apiParams);
        services = ostsdk.services;
    }


    public static void validateResponseWithSuccess( JsonObject response ) {
        validateResponseWithSuccess(response, null, false);
    }
    public static void validateResponseWithSuccess( JsonObject response, String resultType ) {
        validateResponseWithSuccess(response, resultType, false);
    }
    protected static void validateResponseWithSuccess(JsonObject response, String resultType, Boolean isArrayResultType) {


        // Validate presence of basic keys in response.
        Assert.assertEquals( "success key missing in response.", true, response.has("success") );
        Assert.assertEquals( "data key missing in response.", true,  response.has("data") );

        // Validate Success Flag.
        boolean success = response.get("success").getAsBoolean();
        Assert.assertEquals( success, true);

        // Validate data is not an empty Object.
        JsonObject data = response.get("data").getAsJsonObject();
        Assert.assertTrue( data.size() > 0 );

        if ( null == resultType ) {
            return;
        }

        Assert.assertEquals(resultType + " key missing in response.", data.has( resultType ), true );

        if ( !isArrayResultType ) {
            return;
        }

        JsonArray results = data.get( resultType ).getAsJsonArray();
        Assert.assertTrue( results.size() > 0 );
    }

    protected static void validateResponseWithFaliure(JsonObject response) {

        // Validate presence of success key in response.
        Assert.assertEquals( "success key missing in response.", true, response.has("success") );

        // Validate Success Flag.
        boolean success = response.get("success").getAsBoolean();
        Assert.assertEquals( success, false);

    }

    protected static String getRandomAddress() {
        char[] chars = "abcdefABCDEF0123456789".toCharArray();
        StringBuilder sb = new StringBuilder(40);
        Random random = new Random();
        for (int i = 0; i < 40; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String address = "0x" + sb.toString();
        return address;
    }

    @After
    public void tearDown() throws Exception {
        ostsdk = null;
        apiParams.clear();
        apiParams = null;
    }

    protected void setService(OSTAPIService service) {
        this.service = service;
    }

    public OSTAPIService getService() {
        return service;
    }
    public OSTServiceManifest getServiceManifest() {
        return services;
    }
}
