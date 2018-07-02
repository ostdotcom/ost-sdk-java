package com.ost.services.v0;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ost.services.ApiEndPointProvider;
import com.ost.services.ServiceTestBase;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.HashMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserTest extends ServiceTestBase {

    @Override
    public User getService() {
        return (User) super.getService();
    }

    @Before @Override
    public void setUp() throws Exception {
        super.setUp();
        setService( getServiceManifest().user );
    }

    @Override
    protected void setUpApiEndPoint() throws Exception {
        String apiEndPoint = ApiEndPointProvider.getV0EndPoint();
        setApiEndPoint( apiEndPoint );
    }

    @Override
    public com.ost.services.v0.Manifest getServiceManifest() {
        return (com.ost.services.v0.Manifest) super.getServiceManifest();
    }


    @Test
    public void create() {
    }

    @Test
    public void edit() {
    }

    @Test
    public void list() {
        HashMap <String,Object> params = new HashMap<String, Object>();

        String resultType = "economy_users";
        JsonObject response;
        Boolean isArrayResultType = true;
        // Test-Case: Get all available transactions.
        try {
            // Get all results.
            response = getService().list( params );
            validateResponseWithSuccess( response, resultType, isArrayResultType );

            JsonArray results = response.get("data").getAsJsonObject().get( resultType ).getAsJsonArray();
            String id1 = results.get( 0 ).getAsJsonObject().get("id").getAsString();
            String id2 = results.get( 1 ).getAsJsonObject().get("id").getAsString();

            // Get result by id.
            params.put("id", id1);
            response = getService().list( params );
            validateResponseWithSuccess( response, resultType, isArrayResultType );

            // Get results by ids as String.
            params.put("id", id1 + "," + id2);
            response = getService().list( params );
            validateResponseWithSuccess( response, resultType, isArrayResultType );

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void airdropTokens() {
    }

    @Test
    public void airdropStatus() {
    }

}