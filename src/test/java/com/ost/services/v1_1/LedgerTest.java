package com.ost.services.v1_1;

import com.google.gson.JsonObject;
import com.ost.services.ApiEndPointProvider;
import com.ost.services.OSTAPIService;
import com.ost.services.ServiceTestBase;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

public class LedgerTest extends ServiceTestBase {
    @Override
    protected void setUpApiEndPoint() throws Exception {
        String apiEndPoint = ApiEndPointProvider.getV1_1EndPoint();
        setApiEndPoint( apiEndPoint );
    }

    @Override
    public com.ost.services.v1_1.Manifest getServiceManifest() {
        return (com.ost.services.v1_1.Manifest) super.getServiceManifest();
    }

    @Override
    public com.ost.services.v1_1.Ledger getService() {
        return (com.ost.services.v1_1.Ledger) super.getService();
    }

    String fromUserId;

    @Before @Override
    public void setUp() throws Exception {
        super.setUp();
        setService( getServiceManifest().ledger );

        fromUserId = System.getenv("OST_KIT_TRANSFER_FROM_UUID");
        if ( null == fromUserId ) {
            throw new Exception("OST_KIT_TRANSFER_FROM_UUID environment variable not set.");
        }
    }

    @Test
    public void get() throws IOException, OSTAPIService.MissingParameter {
        JsonObject response;
        String resultType = "transactions";
        Boolean isArrayResultType = true;
        HashMap<String,Object> params = new HashMap<String, Object>();
        params.put("id", fromUserId);
        response = getService().get( params );
        validateResponseWithSuccess( response, resultType, isArrayResultType );
    }
}