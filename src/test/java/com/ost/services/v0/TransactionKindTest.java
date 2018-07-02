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
import java.math.BigInteger;
import java.util.HashMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TransactionKindTest extends ServiceTestBase {

    @Override
    public TransactionKind getService() {
        return (TransactionKind) super.getService();
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

    @Before @Override
    public void setUp() throws Exception {
        super.setUp();
        setService( getServiceManifest().transactionKind );
    }

    @Test
    public void t1Create() throws IOException {
        HashMap <String,Object> params = new HashMap<String, Object>();
        params.put("name", "C1 " + generateNamePostFix() );
        params.put("kind", "company_to_user");
        params.put("currency_type", "BT");
        params.put("currency_value", 10);

        JsonObject response;
        String resultType = "transactions";
        Boolean isArrayResultType = true;
        response = getService().create( params );
        validateResponseWithSuccess( response, resultType, isArrayResultType );
        JsonObject result = response.getAsJsonObject("data").getAsJsonArray( resultType ).get( 0 ).getAsJsonObject();
        validateResult(params, result);

    }

    @Test
    public void t2Edit() throws IOException {
        HashMap <String,Object> params = new HashMap<String, Object>();
        params.put("name", "C2 " + generateNamePostFix() );
        params.put("kind", "user_to_user");
        params.put("currency_type", "USD");
        params.put("currency_value", 10);

        JsonObject response;
        String resultType = "transactions";
        Boolean isArrayResultType = true;
        response = getService().create( params );
        validateResponseWithSuccess( response, resultType, isArrayResultType );
        JsonObject result = response.getAsJsonObject("data").getAsJsonArray( resultType ).get( 0 ).getAsJsonObject();
        //Remove currency_value.
        params.remove( "currency_value" );
        validateResult(params, result);

//        params.put("id", result.get("id").getAsString() );
        params.put("client_transaction_id", result.get("client_transaction_id").getAsString() );
        params.put("currency_type", "BT");
        params.put("currency_value", 5);
        response = getService().edit( params );
        validateResponseWithSuccess( response, resultType, isArrayResultType );
        result = response.getAsJsonObject("data").getAsJsonArray( resultType ).get( 0 ).getAsJsonObject();

        validateResult(params, result);


    }

    @Test
    public void t3list() {
        HashMap <String,Object> params = new HashMap<String, Object>();


        JsonObject response;
        String resultType = "transaction_types";
        Boolean isArrayResultType = true;
        try {
            // Test-Case: Get all available transactions.
            response = getService().list( params );
            validateResponseWithSuccess( response, resultType, isArrayResultType );
            JsonArray results = response.get("data").getAsJsonObject().get( resultType ).getAsJsonArray();
            BigInteger id1 = results.get( 0 ).getAsJsonObject().get("id").getAsBigInteger();
            String id2 = results.get( 1 ).getAsJsonObject().get("id").getAsString();

            // Get Transaction by id.
            params.put("id", id1);
            response = getService().list( params );
            validateResponseWithSuccess( response, resultType, isArrayResultType );

            // Get Transaction by id as String.
            params.put("id", id2);
            response = getService().list( params );
            validateResponseWithSuccess( response, resultType, isArrayResultType );

            // Get results by ids as String.
            params.put("id", id1.toString() + "," + id2);
            response = getService().list( params );
            validateResponseWithSuccess( response, resultType, isArrayResultType );

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void t4Execute() {
//        getService().execute();
    }

    @Test
    public void t5Status() {
//        getService().status();
    }
}