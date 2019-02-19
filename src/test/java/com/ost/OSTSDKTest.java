package com.ost;

import com.ost.services.OSTServiceManifest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class OSTSDKTest {

    private HashMap<String, Object> apiV2Params;

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

        apiV2Params = new HashMap<String, Object>();
        apiV2Params.put( "apiKey", apiKey);
        apiV2Params.put( "apiSecret", apiSecret);
        apiV2Params.put( "apiEndpoint", v0Endpoint);

    }

    @Test
    public void createOSTSdkForV2Api() {
        OSTSDK ostsdk = createSDKInstanceForV2API();
        assertNotNull( ostsdk );

        OSTServiceManifest services = ostsdk.services;
        assert( services instanceof com.ost.services.Manifest);
    }


    private OSTSDK createSDKInstanceForV2API() {
        return new OSTSDK( apiV2Params );
    }

    @After
    public void tearDown() throws Exception {

    }
}