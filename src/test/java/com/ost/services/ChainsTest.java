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
        setService(getServiceManifest().chains);
        super.setEnvironmentVariables();
    }

    @Override
    public Manifest getServiceManifest() {
        return (Manifest) super.getServiceManifest();
    }

    @Test
    public void get() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("chain_id", getEnvironmentVariables().get("auxChainId"));

        // Test-Case: Get a Chain Information.
        JsonObject response;
        response = getService().get(params);
        validateResponseWithSuccess(response);

    }

}
