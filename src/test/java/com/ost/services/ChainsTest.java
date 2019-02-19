package com.ost.services;

import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class ChainsTest extends ServiceTestBase {
    @Override
    public Chains getService() {
        return (Chains) super.getService();
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        setService(getServiceManifest().pricePoints);
    }

    @Override
    public Manifest getServiceManifest() {
        return (Manifest) super.getServiceManifest();
    }

    @Test
    public void get() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        String chainId = System.getenv("OST_KIT_CHAIN_ID");
        params.put("chain_id", chainId);

        // Test-Case: Get a Token.
        JsonObject response;
        response = getService().get(params);
        validateResponseWithSuccess(response);

    }

}
