package com.ost;

import com.ost.services.OSTServiceManifest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

public class OSTSDKTest {

    private HashMap<String, Object> apiParams;

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

        String apiEndpoint = System.getenv("OST_KIT_API_ENDPOINT");
        if ( null == apiEndpoint ) {
            throw new Exception("Environment Variable OST_KIT_API_ENDPOINT is not set.");
        }

        apiParams = new HashMap<String, Object>();
        apiParams.put( "apiKey", apiKey);
        apiParams.put( "apiSecret", apiSecret);
        apiParams.put( "apiEndpoint", apiEndpoint);

    }

    @Test
    public void createOSTSdkForV2Api() {
        OSTSDK ostsdk = createSDKInstanceForV2API();
        assertNotNull( ostsdk );

        OSTServiceManifest services = ostsdk.services;
        assert( services instanceof com.ost.services.Manifest);
    }


    private OSTSDK createSDKInstanceForV2API() {
        return new OSTSDK( apiParams );
    }

    @After
    public void tearDown() throws Exception {

    }
}