package com.ost;

import com.ost.services.OSTServiceManifest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class OSTSDKTest {

    private HashMap<String, Object> apiV0Params;
    private HashMap<String, Object> apiV1Params;

    @Before
    public void setUp() throws Exception {


        String apiKey = System.getenv("OST_KIT_API_KEY");
        if ( null == apiKey ) {
            throw new Exception("Environment Variable OST_KIT_API_KEY is not set.");
        }

        String apiSecret = System.getenv("OST_KIT_API_SECRET");
        if ( null == apiSecret ) {
            throw new Exception("Environment Variable OST_KIT_API_SECRET is not set.");
        }

        String v0Endpoint = System.getenv("OST_KIT_API_ENDPOINT");
        if ( null == v0Endpoint ) {
            throw new Exception("Environment Variable OST_KIT_API_ENDPOINT is not set.");
        }

        String v1Endpoint = System.getenv("OST_KIT_API_V1_ENDPOINT");
        if ( null == v1Endpoint ) {
            throw new Exception("Environment Variable OST_KIT_API_V1_ENDPOINT is not set.");
        }

        apiV0Params = new HashMap<String, Object>();
        apiV0Params.put( "apiKey", apiKey);
        apiV0Params.put( "apiSecret", apiSecret);
        apiV0Params.put( "apiEndpoint", v0Endpoint);

        apiV1Params = new HashMap<String, Object>();
        apiV1Params.put( "apiKey", apiKey);
        apiV1Params.put( "apiSecret", apiSecret);
        apiV1Params.put( "apiEndpoint", v1Endpoint);
    }

    @Test
    public void createOSTSdkForV0Api() {
        OSTSDK ostsdk = createSDKInstanceForV0API();
        assertNotNull( ostsdk );

        OSTServiceManifest services = ostsdk.services;
        assert( services instanceof com.ost.services.v0.Manifest);
        assertEquals(services.getApiVersion(), "v0");
    }

    @Test
    public void createOSTSdkForV1Api() {
        OSTSDK ostsdk = createSDKInstanceForV1API();
        assertNotNull( ostsdk );

        OSTServiceManifest services = ostsdk.services;
        assert( services instanceof com.ost.services.v1.Manifest);
        assertEquals(services.getApiVersion(), "v1");
    }


    private OSTSDK createSDKInstanceForV0API() {
        return new OSTSDK( apiV0Params );
    }

    private OSTSDK createSDKInstanceForV1API() {
        return new OSTSDK( apiV1Params );
    }

    @After
    public void tearDown() throws Exception {

    }
}