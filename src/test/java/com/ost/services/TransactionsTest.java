package com.ost.services;


import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class TransactionsTest extends ServiceTestBase {
    @Override
    public Transactions getService() {
        return (Transactions) super.getService();
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        setService(getServiceManifest().transactions);
    }

    @Override
    public Manifest getServiceManifest() {
        return (Manifest) super.getServiceManifest();
    }

    @Test
    public void get() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        String id = System.getenv("OST_KIT_USER_ID");
        params.put("id", id);

        // Test-Case: Get a transaction info.
        JsonObject response;
        response = getService().get(params);
        validateResponseWithSuccess(response);

    }

    @Test
    public void create() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();

        // Test-Case: execute transaction from company.
        JsonObject response;
        response = getService().execute(params);
        validateResponseWithSuccess(response);

    }

    @Test
    public void getList() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();

        // Test-Case: Get all transactions for a user.
        JsonObject response;
        response = getService().getList(params);
        validateResponseWithSuccess(response);

    }

}
