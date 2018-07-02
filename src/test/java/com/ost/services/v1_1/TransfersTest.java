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
public class TransfersTest extends com.ost.services.v1.TransfersTest {

    @Override
    public com.ost.services.v1_1.Transfers getService() {
        return (com.ost.services.v1_1.Transfers) super.getService();
    }

    @Override
    protected void setUpApiEndPoint() throws Exception {
        String apiEndPoint = ApiEndPointProvider.getV1_1EndPoint();
        setApiEndPoint( apiEndPoint );
    }

    @Override
    protected void setService() {
        com.ost.services.v1_1.Manifest services = (com.ost.services.v1_1.Manifest) getServiceManifest();
        setService( services.transfers );
    }

}