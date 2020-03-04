package com.ost.services;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class RedemptionsTest extends ServiceTestBase {
    @Override
    public Redemptions getService() {
        return (Redemptions) super.getService();
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        setService(getServiceManifest().redemptions);
        super.setEnvironmentVariables();
    }

    @Override
    public Manifest getServiceManifest() {
        return (Manifest) super.getServiceManifest();
    }

    @Test
    public void get() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("user_id", getEnvironmentVariables().get("userId"));
        params.put("redemption_id", getEnvironmentVariables().get("redemptionId"));

        // Test-Case: Get a redemption info.
        JsonObject response;
        response = getService().get(params);
        validateResponseWithSuccess(response);

    }

    @Test
    public void getList() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("user_id", getEnvironmentVariables().get("userId"));

        // Test-Case: Get all redemptions for a user.
        JsonObject response;
        response = getService().getList(params);
        validateResponseWithSuccess(response);

    }

}
