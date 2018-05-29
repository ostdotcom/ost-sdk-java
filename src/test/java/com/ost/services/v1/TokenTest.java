package com.ost.services.v1;

import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TokenTest extends V1SecviceTestBase {

    @Override
    public com.ost.services.v1.Token getService() {
        return (com.ost.services.v1.Token) super.getService();
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        setService( getServiceManifest().token );
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