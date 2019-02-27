package com.ost.services;

import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class RulesTest extends ServiceTestBase {
    @Override
    public Rules getService() {
        return (Rules) super.getService();
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        setService(getServiceManifest().rules);
    }

    @Override
    public Manifest getServiceManifest() {
        return (Manifest) super.getServiceManifest();
    }

    @Test
    public void get() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();

        // Test-Case: List all Rules.
        JsonObject response;
        response = getService().get(params);
        validateResponseWithSuccess(response);

    }

}