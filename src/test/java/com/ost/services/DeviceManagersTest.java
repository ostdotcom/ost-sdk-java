package com.ost.services;

import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class DeviceManagersTest extends ServiceTestBase {
    @Override
    public DeviceManagers getService() {
        return (DeviceManagers) super.getService();
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        setService(getServiceManifest().deviceManagers);
    }

    @Override
    public Manifest getServiceManifest() {
        return (Manifest) super.getServiceManifest();
    }

    @Test
    public void get() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();

        // Test-Case: Get device managers detail.
        JsonObject response;
        response = getService().get(params);
        validateResponseWithSuccess(response);

    }

}
