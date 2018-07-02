package com.ost.services.v1_1;

import com.ost.services.ApiEndPointProvider;

public class TokenTest extends com.ost.services.v1.TokenTest {
    @Override
    public com.ost.services.v1_1.Token getService() {
        return (com.ost.services.v1_1.Token) super.getService();
    }

    @Override
    protected void setService() {
        com.ost.services.v1_1.Manifest services = (com.ost.services.v1_1.Manifest) getServiceManifest();
        setService( services.token );
    }


    @Override
    protected void setUpApiEndPoint() throws Exception {
        String apiEndPoint = ApiEndPointProvider.getV1_1EndPoint();
        setApiEndPoint( apiEndPoint );
    }

}