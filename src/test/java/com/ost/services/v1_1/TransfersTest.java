package com.ost.services.v1_1;

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
public class TransfersTest extends ServiceTestBase {

    @Override
    public com.ost.services.v1_1.Transfers getService() {
        return (com.ost.services.v1_1.Transfers) super.getService();
    }

    @Override
    public com.ost.services.v1_1.Manifest getServiceManifest() {
        return (com.ost.services.v1_1.Manifest) super.getServiceManifest();
    }

    HashMap<String,Object> commonParams;

    @Override
    protected void setUpApiEndPoint() throws Exception {
        String apiEndPoint = ApiEndPointProvider.getV1_1EndPoint();
        setApiEndPoint( apiEndPoint );
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        setService(getServiceManifest().transfers);
        commonParams = new HashMap<String, Object>();
        commonParams.put("to_address", "0x062ded9304cd96af6fa4780d6d6fd873e2b52410");
        commonParams.put("amount", 1);
    }

    @Test
    public void execute() throws IOException {
        HashMap <String,Object> params = new HashMap<String, Object>();
        params.putAll( commonParams );
        JsonObject response;
        String resultType = "transfer";
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
        String resultType = "transfer";
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
        String resultType = "transfers";
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