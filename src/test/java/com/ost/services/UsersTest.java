package com.ost.services;


import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class UsersTest extends ServiceTestBase {
    @Override
    public Users getService() {
        return (Users) super.getService();
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
        params.put("id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");

        // Test-Case: Get an User.
        JsonObject response;
        response = getService().get(params);
        validateResponseWithSuccess(response);

    }

    @Test
    public void create() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();

        // Test-Case: Create an User.
        JsonObject response;
        response = getService().create(params);
        validateResponseWithSuccess(response);

    }

    @Test
    public void getList() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();

        // Test-Case: Get List of users.
        JsonObject response;
        response = getService().getList(params);
        validateResponseWithSuccess(response);

    }

}