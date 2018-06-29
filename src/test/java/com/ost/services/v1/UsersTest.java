package com.ost.services.v1;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ost.services.ApiEndPointProvider;
import com.ost.services.OSTAPIService;
import com.ost.services.ServiceTestBase;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.HashMap;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UsersTest extends ServiceTestBase {
    @Override
    public com.ost.services.v1.Users getService() {
        return (com.ost.services.v1.Users) super.getService();
    }

    @Override
    protected void setUpApiEndPoint() throws Exception {
        String apiEndPoint = ApiEndPointProvider.getV1EndPoint();
        setApiEndPoint( apiEndPoint );
    }


    @Before
    public void setUp() throws Exception {
        super.setUp();
        setService();
    }


    protected void setService() {
        com.ost.services.v1.Manifest services = (com.ost.services.v1.Manifest) getServiceManifest();
        setService( services.users );
    }


    @Test
    public void create() throws IOException {
        //First Create.
        HashMap <String,Object> params = new HashMap<String, Object>();
        params.put("name", "Tom" + generateNamePostFix() );

        JsonObject response;
        String resultType = "user";
        Boolean isArrayResultType = false;
        response = getService().create( params );
        validateResponseWithSuccess( response, resultType, isArrayResultType );
        JsonObject result = response.getAsJsonObject("data").getAsJsonObject( resultType );
        validateResult(params, result);

    }

    @Test
    public void edit() throws IOException, OSTAPIService.MissingParameter {
        //First Create.
        HashMap <String,Object> params = new HashMap<String, Object>();
        params.put("name", "Ben " + generateNamePostFix() );

        JsonObject response;
        String resultType = "user";
        Boolean isArrayResultType = false;
        response = getService().create( params );
        validateResponseWithSuccess( response, resultType, isArrayResultType );
        JsonObject result = response.getAsJsonObject("data").getAsJsonObject( resultType );
        validateResult(params, result);

        params.put("id", result.get("id").getAsString() );
        params.put("name", "Jax " + generateNamePostFix() );
        response = getService().edit( params );
        validateResponseWithSuccess( response, resultType, isArrayResultType );
        result = response.getAsJsonObject("data").getAsJsonObject( resultType );
        validateResult(params, result);

    }

    @Test
    public void get() throws IOException, OSTAPIService.MissingParameter {
        //First Create.
        HashMap <String,Object> params = new HashMap<String, Object>();
        params.put("name", "Bob " + generateNamePostFix() );

        JsonObject response;
        String resultType = "user";
        Boolean isArrayResultType = false;
        response = getService().create( params );
        validateResponseWithSuccess( response, resultType, isArrayResultType );
        JsonObject result = response.getAsJsonObject("data").getAsJsonObject( resultType );
        validateResult(params, result);

        //Now get.
        params = new HashMap<String, Object>();
        params.put("id", result.get("id").getAsString() );
        response = getService().get( params );
        validateResponseWithSuccess( response, resultType, isArrayResultType );
        result = response.getAsJsonObject("data").getAsJsonObject( resultType );
        validateResult(params, result);
    }

    @Test
    public void list() throws IOException {
        HashMap <String,Object> params = new HashMap<String, Object>();
        JsonObject response;
        String resultType = "users";
        Boolean isArrayResultType = true;

        response = getService().list( params );
        validateResponseWithSuccess( response, resultType, isArrayResultType );

        JsonArray results = response.getAsJsonObject("data").getAsJsonArray( resultType );
        if ( results.size() > 2 ) {
            String ids;
            JsonObject result;
            result = results.get( 0 ).getAsJsonObject();
            ids = result.get("id").getAsString();

            result = results.get( 1 ).getAsJsonObject();
            ids = ids + "," + result.get("id").getAsString();

            params.put("id", ids);
            response = getService().list( params );
            validateResponseWithSuccess( response, resultType, isArrayResultType );
        }

    }
}