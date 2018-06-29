package com.ost.services.v1;

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
public class TokenTest extends ServiceTestBase {

    @Override
    public com.ost.services.v1.Token getService() {
        return (com.ost.services.v1.Token) super.getService();
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
        setService( services.token);
    }


    @Test
    public void get() throws IOException {
        JsonObject response;
        String resultType = "token";
        Boolean isArrayResultType = false;

        HashMap <String,Object> params = new HashMap<String, Object>();
        response = getService().get( params );

        validateResponseWithSuccess( response, resultType, isArrayResultType );
    }
}