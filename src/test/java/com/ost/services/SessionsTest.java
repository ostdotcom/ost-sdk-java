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

    @Test
    public void get() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        String userId = System.getenv("OST_KIT_USER_ID");
        String sessionAddress = System.getenv("OST_KIT_SESSION_ADDRESS");
        params.put("user_id", userId);
        params.put("session_address", sessionAddress);

        // Test-Case: Get a Session.
        if (sessionAddress != null && !sessionAddress.trim().isEmpty()) {
            JsonObject response;
            response = getService().get(params);
            validateResponseWithSuccess(response);
        }
    }

    @Test
    public void getList() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        String userId = System.getenv("OST_KIT_USER_ID");
        params.put("user_id", userId);

        // Test-Case: Get List of Sessions.
        JsonObject response;
        response = getService().getList(params);
        validateResponseWithSuccess(response);

    }

}
