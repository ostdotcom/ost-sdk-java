package com.ost.services;


import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class DevicesTest extends ServiceTestBase {
    @Override
    public Devices getService() {
        return (Devices) super.getService();
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        setService(getServiceManifest().devices);
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
        params.put("device_address", getEnvironmentVariables().get("deviceUserAddress"));

        // Test-Case: Get User Device.
        JsonObject response;
        response = getService().get(params);
        validateResponseWithSuccess(response);

    }

    @Test
    public void create() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        String address = getRandomAddress();
        String apiSignerAddress = getRandomAddress();
        params.put("user_id", getEnvironmentVariables().get("userId"));
        params.put("address", address);
        params.put("api_signer_address", apiSignerAddress);

        // Test-Case: Create a device for User.
        JsonObject response;
        response = getService().create(params);
        validateResponseWithSuccess(response);

    }

    @Test
    public void getList() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", getEnvironmentVariables().get("userId"));

        // Test-Case: Get User Device(s) List.
        JsonObject response;
        response = getService().getList(params);
        validateResponseWithSuccess(response);

    }

}