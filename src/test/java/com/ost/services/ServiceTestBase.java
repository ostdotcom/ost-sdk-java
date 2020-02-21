package com.ost.services;

import com.google.gson.JsonObject;
import com.ost.OSTSDK;
import org.junit.After;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Random;

public abstract class ServiceTestBase {
    private OSTSDK ostsdk;
    private HashMap<String, Object> apiParams;
    protected com.ost.services.OSTServiceManifest services;
    private OSTAPIService service;
    private HashMap<String, Object> environmentVariables;

    public void setUp() throws Exception {

        String apiKey = System.getenv("OST_KIT_API_KEY");
        if ( null == apiKey ) {
            throw new Exception("Environment Variable OST_KIT_API_KEY is not set.");
        }

        String apiSecret = System.getenv("OST_KIT_API_SECRET");
        if ( null == apiSecret ) {
            throw new Exception("Environment Variable OST_KIT_API_SECRET is not set.");
        }


        String apiEndPoint = System.getenv("OST_KIT_API_ENDPOINT");
        if ( null == apiEndPoint ) {
            throw new Exception("apiEndPoint can not be null.");
        }

        apiParams = new HashMap<String, Object>();
        apiParams.put( "apiKey", apiKey);
        apiParams.put( "apiSecret", apiSecret);
        apiParams.put( "apiEndpoint", apiEndPoint);

        ostsdk = new OSTSDK(apiParams);
        services = ostsdk.services;
    }

    protected static void validateResponseWithSuccess(JsonObject response) {

        // Validate presence of success key in response.
        Assert.assertEquals( "success key missing in response.", true, response.has("success") );

        // Validate Success Flag.
        boolean success = response.get("success").getAsBoolean();
        Assert.assertEquals( success, true);

    }

    protected static void validateResponseWithFaliure(JsonObject response) {

        // Validate presence of success key in response.
        Assert.assertEquals( "success key missing in response.", true, response.has("success") );

        // Validate Success Flag.
        boolean success = response.get("success").getAsBoolean();
        Assert.assertEquals( success, false);

    }

    protected static String getRandomAddress() {
        char[] chars = "abcdefABCDEF0123456789".toCharArray();
        StringBuilder sb = new StringBuilder(40);
        Random random = new Random();
        for (int i = 0; i < 40; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String address = "0x" + sb.toString();
        return address;
    }

    @After
    public void tearDown() throws Exception {
        ostsdk = null;
        apiParams.clear();
        apiParams = null;
    }

    protected void setService(OSTAPIService service) {
        this.service = service;
    }

    public void setEnvironmentVariables() throws Exception {
        String userId = System.getenv("OST_KIT_USER_ID");
        if ( null == userId ) {
            throw new Exception("userId can not be null.");
        }

        String auxChainId = System.getenv("OST_KIT_AUX_CHAIN_ID");
        if ( null == auxChainId ) {
            throw new Exception("auxChainId can not be null.");
        }

        String deviceUserAddress = System.getenv("OST_KIT_USER_DEVICE_ADDRESS");
        if ( null == deviceUserAddress ) {
            throw new Exception("deviceUserAddress can not be null.");
        }

        String recoveryOwnerAddress = System.getenv("OST_KIT_RECOVERY_OWNER_ADDRESS");
        if ( null == recoveryOwnerAddress ) {
            throw new Exception("recoveryOwnerAddress can not be null.");
        }

        String sessionAddress = System.getenv("OST_KIT_SESSION_ADDRESS");
        if ( null == sessionAddress ) {
            throw new Exception("sessionAddress can not be null.");
        }

        String ruleAddress = System.getenv("OST_KIT_RULE_ADDRESS");
        if ( null == ruleAddress ) {
            throw new Exception("ruleAddress can not be null.");
        }

        String user2TokenHolderAddress = System.getenv("OST_KIT_USER2_TOKEN_HOLDER_ADDRESS");
        if ( null == user2TokenHolderAddress ) {
            throw new Exception("user2TokenHolderAddress can not be null.");
        }

        String transactionId = System.getenv("OST_KIT_TRANSACTION_ID");
        if ( null == transactionId ) {
            throw new Exception("transactionId can not be null.");
        }

        String companyUserId = System.getenv("OST_KIT_COMPANY_USER_ID");
        if ( null == companyUserId ) {
            throw new Exception("companyUserId can not be null.");
        }

        String redemptionId = System.getenv("OST_KIT_REDEMPTION_ID");
        if ( null == redemptionId ) {
            throw new Exception("redemptionId can not be null.");
        }

        String redeemableSkuId = System.getenv("OST_KIT_REDEEMABLE_SKU_ID");
        if ( null == redeemableSkuId ) {
            throw new Exception("redeemableSkuId can not be null.");
        }


        environmentVariables = new HashMap<String, Object>();
        environmentVariables.put("userId", userId);
        environmentVariables.put("auxChainId", auxChainId);
        environmentVariables.put("deviceUserAddress", deviceUserAddress);
        environmentVariables.put("recoveryOwnerAddress", recoveryOwnerAddress);
        environmentVariables.put("sessionAddress", sessionAddress);
        environmentVariables.put("ruleAddress", ruleAddress);
        environmentVariables.put("user2TokenHolderAddress", user2TokenHolderAddress);
        environmentVariables.put("transactionId", transactionId);
        environmentVariables.put("companyUserId", companyUserId);
        environmentVariables.put("redemptionId", redemptionId);
        environmentVariables.put("redeemableSkuId", redeemableSkuId);
    }

    public OSTAPIService getService() {
        return service;
    }
    public OSTServiceManifest getServiceManifest() {
        return services;
    }

    public HashMap<String, Object> getEnvironmentVariables() {
        return environmentVariables;
    }
}
