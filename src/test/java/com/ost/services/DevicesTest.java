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
        setService(getServiceManifest().users);
    }

    @Override
    public Manifest getServiceManifest() {
        return (Manifest) super.getServiceManifest();
    }

    @Test
    public void get() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        String userId = System.getenv("OST_KIT_USER_ID");
        String deviceAddress = System.getenv("OST_KIT_DEVICE_ADDRESS");
        params.put("user_id", userId);
        params.put("device_address", deviceAddress);

        // Test-Case: Get User Device.
        JsonObject response;
        response = getService().get(params);
        validateResponseWithSuccess(response);

    }

    @Test
    public void create() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        String userId = System.getenv("OST_KIT_USER_ID");
        String deviceUuid = System.getenv("OST_KIT_DEVICE_UUID");
        String address = getRandomAddress();
        String apiSignerAddress = getRandomAddress();
        params.put("user_id", userId);
        params.put("address", address);
        params.put("api_signer_address", apiSignerAddress);
        params.put("device_uuid", deviceUuid);
        params.put("device_name", "Iphone S");

        // Test-Case: Create a device for User.
        JsonObject response;
        response = getService().create(params);
        validateResponseWithSuccess(response);

    }

    @Test
    public void getList() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        String userId = System.getenv("OST_KIT_USER_ID");
        params.put("user_id", userId);

        // Test-Case: Get User Device(s) List.
        JsonObject response;
        response = getService().getList(params);
        validateResponseWithSuccess(response);

    }

}