package com.ost.services;


import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Random;

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
        params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
        params.put("device_address", "0x1Ea365269A3e6c8fa492eca9A531BFaC8bA1649E");

        // Test-Case: Get an User.
        JsonObject response;
        response = getService().get(params);
        validateResponseWithSuccess(response);

    }

    @Test
    public void create() throws Exception {
        char[] chars = "abcdefABCDEF0123456789".toCharArray();
        StringBuilder sb = new StringBuilder(40);
        Random random = new Random();
        for (int i = 0; i < 40; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String address = "0x" + sb.toString();

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
        params.put("address", address);
        params.put("api_signer_address", "0x5F860598383868e8E8Ee0ffC5ADD92369Db37455");
        params.put("device_uuid", "593a967f-87bd-49a6-976c-52edf46c4df4");
        params.put("device_name", "Iphone S");

        // Test-Case: Create an User.
        JsonObject response;
        response = getService().create(params);
        validateResponseWithFaliure(response);

    }

    @Test
    public void getList() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");

        // Test-Case: Get List of users.
        JsonObject response;
        response = getService().getList(params);
        validateResponseWithSuccess(response);

    }

}