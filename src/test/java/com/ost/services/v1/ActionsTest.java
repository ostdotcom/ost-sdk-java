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
public class ActionsTest extends ServiceTestBase {

    @Override
    public com.ost.services.v1.Actions getService() {
        return (com.ost.services.v1.Actions) super.getService();
    }

    @Override
    protected void setUpApiEndPoint() throws Exception {
        String apiEndPoint = ApiEndPointProvider.getV1EndPoint();
        setApiEndPoint( apiEndPoint );
    }

    @Before @Override
    public void setUp() throws Exception {
        super.setUp();
        setService();
    }

    protected void setService() {
        com.ost.services.v1.Manifest services = (com.ost.services.v1.Manifest) getServiceManifest();
        setService( services.actions );
        super.setService( services.actions );
    }

    @Test
    public void create() throws IOException {
        HashMap <String,Object> params = new HashMap<String, Object>();
        params.put("name", "C1 " + generateNamePostFix() );
        params.put("kind", "company_to_user");
        params.put("currency", "BT");
        params.put("arbitrary_amount", true);

        JsonObject response;
        String resultType = "action";
        Boolean isArrayResultType = false;
        response = getService().create( params );
        validateResponseWithSuccess( response, resultType, isArrayResultType );
        JsonObject result = response.getAsJsonObject("data").getAsJsonObject( resultType );
        validateResult(params, result);

    }

    @Test
    public void edit() throws IOException, OSTAPIService.MissingParameter {
        // First Create.
        HashMap <String,Object> params = new HashMap<String, Object>();
        params.put("name", "C2 " + generateNamePostFix() );
        params.put("kind", "user_to_user");
        params.put("currency", "BT");
        params.put("arbitrary_amount", true);
        params.put("arbitrary_commission", true);

        JsonObject response;
        String resultType = "action";
        Boolean isArrayResultType = false;
        response = getService().create( params );
        validateResponseWithSuccess( response, resultType, isArrayResultType );
        JsonObject result = response.getAsJsonObject("data").getAsJsonObject( resultType );
        validateResult(params, result);

        String newActionId = result.get("id").getAsString();

        // Now Edit.
        params.put("id", newActionId);
        params.put("currency", "USD");
        params.put("arbitrary_amount", false );
        params.put( "amount", 0.01 );
        params.put( "arbitrary_commission", false );
        params.put( "commission_percent", 10);

        response = getService().edit( params );
        validateResponseWithSuccess( response, resultType, isArrayResultType );

        result = response.getAsJsonObject("data").getAsJsonObject( resultType );
        validateResult(params, result);
    }

    @Test
    public void get() throws IOException, OSTAPIService.MissingParameter {
        //First Create.
        HashMap <String,Object> params = new HashMap<String, Object>();
        params.put("name", "C3 " + generateNamePostFix() );
        params.put("kind", "user_to_company");
        params.put("currency", "BT");
        params.put("arbitrary_amount", true);

        JsonObject response;
        String resultType = "action";
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
        String resultType = "actions";
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