package com.ost.services;

import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class SessionsTest extends ServiceTestBase {
    @Override
    public Sessions getService() {
        return (Sessions) super.getService();
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        setService(getServiceManifest().sessions);
    }

    @Override
    public Manifest getServiceManifest() {
        return (Manifest) super.getServiceManifest();
    }

//    @Test
//    public void get() throws Exception {
//        HashMap<String, Object> params = new HashMap<String, Object>();
//        params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
//        params.put("session_address", "0x1Ea365269A3e6c8fa492eca9A531BFaC8bA1649E");
//
//        // Test-Case: Get a Token.
//        JsonObject response;
//        response = getService().get(params);
//        validateResponseWithFaliure(response);
//
//    }

    @Test
    public void getList() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");

        // Test-Case: Get a Token.
        JsonObject response;
        response = getService().getList(params);
        validateResponseWithSuccess(response);

    }

}
