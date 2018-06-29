package com.ost.services.v1_1;

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
public class BalancesTest extends ServiceTestBase {

    @Override
    protected void setUpApiEndPoint() throws Exception {
        String apiEndPoint = ApiEndPointProvider.getV1_1EndPoint();
        setApiEndPoint( apiEndPoint );
    }

    @Override
    public com.ost.services.v1_1.Balances getService() {
        return (com.ost.services.v1_1.Balances) super.getService();
    }


    String fromUserId;
    @Before @Override
    public void setUp() throws Exception {
        super.setUp();
        setService();

        fromUserId = System.getenv("OST_KIT_TRANSFER_FROM_UUID");
        if ( null == fromUserId ) {
            throw new Exception("OST_KIT_TRANSFER_FROM_UUID environment variable not set.");
        }
    }

    protected void setService() {
        com.ost.services.v1_1.Manifest services = (com.ost.services.v1_1.Manifest) getServiceManifest();
        setService( services.balances );
    }

    @Test
    public void get() throws IOException, OSTAPIService.MissingParameter {
        JsonObject response;
        String resultType = "balance";
        Boolean isArrayResultType = false;
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("id", fromUserId);
        response = getService().get( params );
        validateResponseWithSuccess( response, resultType, isArrayResultType );
    }

}