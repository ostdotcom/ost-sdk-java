package com.ost.services;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class WebhooksTest extends ServiceTestBase {
    public static String webhookId = "";
    @Override
    public Webhooks getService() {
        return (Webhooks) super.getService();
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        setService(getServiceManifest().webhooks);
        super.setEnvironmentVariables();
    }

    @Override
    public Manifest getServiceManifest() {
        return (Manifest) super.getServiceManifest();
    }

    @Test
    public void create() throws Exception {
        String version = System.getProperty("java.version");
        String randomUrl = "https://testingWebhooks.com/" + (System.currentTimeMillis() / 1000) + "/" + version;

        HashMap<String, Object> createParams = new HashMap<String, Object>();
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("devices/authorization_initiate");
        createParams.put("url", randomUrl);
        createParams.put("status", "active");
        createParams.put("topics", arrayList);

        // Test-Case: Create a Webhook.

        JsonObject createResponse;
        createResponse = getService().create(createParams);
        validateResponseWithSuccess(createResponse);

        JsonObject createResponseWithData = (JsonObject) createResponse.get("data");
        JsonObject createResponseWithWebhook = (JsonObject) createResponseWithData.get("webhook");
        JsonElement JsonElementWithWebhookId = createResponseWithWebhook.get("id");
        webhookId = JsonElementWithWebhookId.getAsString();

    }


    @Test
    public void get() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("webhook_id", this.webhookId);

        // Test-Case: Get a Webhook.
        JsonObject response;
        response = getService().get(params);
        validateResponseWithSuccess(response);

    }


    @Test
    public void update() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList.add("devices/authorization_initiate");
        params.put("webhook_id", getEnvironmentVariables().get("webhookId"));
        params.put("status", "active");
        params.put("topics", arrayList);

        // Test-Case: Create an User.
        JsonObject response;
        response = getService().update(params);
        validateResponseWithSuccess(response);

    }

    @Test
    public void getList() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();

        // Test-Case: Get List of webhooks.
        JsonObject response;
        response = getService().getList(params);
        validateResponseWithSuccess(response);

    }

    @Test
    public void deleteWebhook() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("webhook_id", this.webhookId);

        // Test-Case: Delete a Webhook.
        JsonObject response;
        response = getService().deleteWebhook(params);
        validateResponseWithSuccess(response);

    }

    @Test
    public void verifySignature() throws Exception {
        String version = "2";
        String stringifiedData = "{\"id\":\"40ae9f25-ec90-44ec-989a-a6d2dd9de715\",\"topic\":\"transactions/success\",\"created_at\":1559702117,\"webhook_id\":\"d17e2f5a-8831-46f1-bd83-ffdc2cd83c29\",\"version\":\"2\",\"data\":{\"result_type\":\"transaction\",\"transaction\":{\"id\":\"6fbb90f4-6a3a-4a1a-8e14-e7f702eddce1\",\"transaction_hash\":\"0x9acdcfc16a66f366fbcc5f150a637cb6b8097b5f2e6943abdd348cac5dc18cb8\",\"from\":\"0x64378ee8ae32a127ad933c39784518178afdd7fd\",\"to\":\"0x18ab0eb5169776a0fc7c8e2f922d6ad53ef098d2\",\"nonce\":48,\"value\":\"0\",\"gas_price\":\"1000000000\",\"gas_used\":109362,\"transaction_fee\":\"109362000000000\",\"block_confirmation\":6,\"status\":\"SUCCESS\",\"updated_timestamp\":1559721898,\"block_timestamp\":1559721897,\"block_number\":1208982,\"rule_name\":\"Direct Transfer\",\"meta_property\":{\"name\":\"Tokens sent from Android\",\"type\":\"user_to_user\",\"details\":null},\"transfers\":[{\"from\":\"0x18ab0eb5169776a0fc7c8e2f922d6ad53ef098d2\",\"from_user_id\":\"a73c02eb-beef-4db3-b48a-f638bde8cc0b\",\"to\":\"0xc51be551a2904c321c10e9615d2ba7ee110f5b96\",\"to_user_id\":\"5a0aec54-a0c5-4349-9235-e6b9ba8d9d34\",\"amount\":\"10000000000000000\",\"kind\":\"transfer\"}]}}}";
        String requestTimestamp = "1559902637";
        String signature = "e9206f9feecccd8f9653a4bdb56ea74531e6528bae8f6de1797aa77dc5c3bc51";
        String webhookSecret = "09121ae7614856777fa36d63aca828e0ef14be77fb48fa149e0c0b50fec847a7";

        // Test-Case: Verify Signature for webhook.
        boolean success;
        success = getService().verifySignature(version, stringifiedData, requestTimestamp, signature, webhookSecret);

        Assert.assertEquals( success, true);
    }

}
