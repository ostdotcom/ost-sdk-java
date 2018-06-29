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
public class TransactionsTest extends ServiceTestBase {

    @Override
    public com.ost.services.v1.Transactions getService() {
        return (com.ost.services.v1.Transactions) super.getService();
    }

    @Override
    protected void setUpApiEndPoint() throws Exception {
        String apiEndPoint = ApiEndPointProvider.getV1EndPoint();
        setApiEndPoint( apiEndPoint );
    }


    protected HashMap<String,Object> commonParams;
    protected com.ost.services.v1.Actions actionsService;
    @Before
    public void setUp() throws Exception {
        super.setUp();
        setService();
        postSetup();
    }

    protected void setService() {
        com.ost.services.v1.Manifest services = (com.ost.services.v1.Manifest) getServiceManifest();
        setService( services.transactions);
        actionsService = services.actions;
    }


    protected void postSetup()  throws Exception {

        //First Create an action
        HashMap <String,Object> params = new HashMap<String, Object>();
        params.put("name", "T1 " + generateNamePostFix() );
        params.put("kind", "user_to_user");
        params.put("currency", "BT");
        params.put("arbitrary_amount", false);
        params.put("amount", "1");
        params.put("arbitrary_commission", false);
        params.put("commission_percent", 1);

        JsonObject response;
        String resultType = "action";
        Boolean isArrayResultType = false;
        response = actionsService.create( params );
        validateResponseWithSuccess( response, resultType, isArrayResultType );
        JsonObject actionResult = response.getAsJsonObject("data").getAsJsonObject( resultType );



        commonParams = new HashMap<String, Object>();
        String fromUserId = System.getenv("OST_KIT_TRANSFER_FROM_UUID");
        if ( null == fromUserId ) {
            throw new Exception("OST_KIT_TRANSFER_FROM_UUID environment variable not set.");
        }

        String toUserId = System.getenv("OST_KIT_TRANSFER_TO_UUID");
        commonParams.put("from_user_id", fromUserId);
        commonParams.put("to_user_id", toUserId);
        commonParams.put("action_id", actionResult.get("id").getAsString() );
    }

    @Test
    public void execute() throws IOException {
        HashMap <String,Object> params = new HashMap<String, Object>();
        params.putAll( commonParams );
        JsonObject response;
        String resultType = "transaction";
        Boolean isArrayResultType = false;
        response = getService().execute( params );
        validateResponseWithSuccess( response, resultType, isArrayResultType );

        JsonObject result = response.getAsJsonObject("data").getAsJsonObject( resultType );
        validateResult(params, result);

    }

    @Test
    public void get() throws IOException, OSTAPIService.MissingParameter {
        HashMap <String,Object> params = new HashMap<String, Object>();
        params.putAll( commonParams );
        JsonObject response;
        String resultType = "transaction";
        Boolean isArrayResultType = false;
        response = getService().execute( params );
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
        String resultType = "transactions";
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