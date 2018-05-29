package com.ost.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ost.OSTSDK;
import org.junit.After;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

public class ServiceTestBase {
    private OSTSDK ostsdk;
    private HashMap<String, Object> apiV0Params;
    protected com.ost.services.OSTServiceManifest services;
    private OSTAPIService service;
    private static final int sleepMilliSeconds = 300;


    public void setUp( String apiEndPoint ) throws Exception {
        String apiKey = System.getenv("OST_KIT_API_KEY");
        if ( null == apiKey ) {
            throw new Exception("Environment Variable OST_KIT_API_KEY is not set.");
        }

        String apiSecret = System.getenv("OST_KIT_API_SECRET");
        if ( null == apiSecret ) {
            throw new Exception("Environment Variable OST_KIT_API_SECRET is not set.");
        }


        if ( null == apiEndPoint ) {
            throw new Exception("apiEndPoint can not be null.");
        }

        apiV0Params = new HashMap<String, Object>();
        apiV0Params.put( "apiKey", apiKey);
        apiV0Params.put( "apiSecret", apiSecret);
        apiV0Params.put( "apiEndpoint", apiEndPoint);

        ostsdk = new OSTSDK( apiV0Params );
        services = ostsdk.services;
    }


    public static void validateResponseWithSuccess( JsonObject response ) {
        validateResponseWithSuccess(response, null, false);
    }
    public static void validateResponseWithSuccess( JsonObject response, String resultType ) {
        validateResponseWithSuccess(response, resultType, false);
    }
    protected static void validateResponseWithSuccess(JsonObject response, String resultType, Boolean isArrayResultType) {
        // Lets sleep for a while.
        try {
            Thread.sleep(sleepMilliSeconds);
        } catch (InterruptedException e) {
            //Ignore it.
        }

        //
        Assert.assertEquals( response.has("success"), true );
        Assert.assertEquals( response.has("data"), true );

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

    protected static void validateResult( Map<String,Object> params, JsonObject result ) {
        for( Map.Entry<String,Object> param: params.entrySet() ) {
            String paramKey  = param.getKey();

            Assert.assertEquals("result does not contain key" + paramKey, true, result.has( paramKey ) );

            String paramVal  = param.getValue().toString();
            String resultVal = result.get( paramKey ).getAsString();
            Assert.assertEquals("result value for key " + paramKey + " does not match expected value.", paramVal, resultVal );

        }
    }

    @After
    public void tearDown() throws Exception {
        ostsdk = null;
        apiV0Params.clear();
        apiV0Params = null;
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
