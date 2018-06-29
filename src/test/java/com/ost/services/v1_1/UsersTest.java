package com.ost.services.v1_1;


import com.ost.services.ApiEndPointProvider;

public class UsersTest extends com.ost.services.v1.UsersTest {
    @Override
    public com.ost.services.v1_1.Users getService() {
        return (com.ost.services.v1_1.Users) super.getService();
    }


    @Override
    protected void setUpApiEndPoint() throws Exception {
        String apiEndPoint = ApiEndPointProvider.getV1_1EndPoint();
        setApiEndPoint( apiEndPoint );
    }

    @Override
    protected void setService() {
        com.ost.services.v1_1.Manifest services = (com.ost.services.v1_1.Manifest) getServiceManifest();
        setService( services.users );
    }


}