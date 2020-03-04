package com.ost.services;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class RedeemableSkusTest extends ServiceTestBase {
    @Override
    public RedeemableSkus getService() {
        return (RedeemableSkus) super.getService();
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        setService(getServiceManifest().redeemableSkus);
        super.setEnvironmentVariables();
    }

    @Override
    public Manifest getServiceManifest() {
        return (Manifest) super.getServiceManifest();
    }

    @Test
    public void get() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("redeemable_sku_id", getEnvironmentVariables().get("redeemableSkuId"));

        // Test-Case: Get a redeemable SKU info.
        JsonObject response;
        response = getService().get(params);
        validateResponseWithSuccess(response);

    }

    @Test
    public void getList() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();

        // Test-Case: Get all redeemable SKUs.
        JsonObject response;
        response = getService().getList(params);
        validateResponseWithSuccess(response);

    }

}
