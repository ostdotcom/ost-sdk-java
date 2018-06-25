package com.ost.services.v1_1;
import com.ost.services.ApiEndPointProvider;


public class ActionsTest extends com.ost.services.v1.ActionsTest {
    @Override
    public com.ost.services.v1_1.Actions getService() {
        return (com.ost.services.v1_1.Actions) super.getService();
    }

    @Override
    public com.ost.services.v1_1.Manifest getServiceManifest() {
        return (com.ost.services.v1_1.Manifest) super.getServiceManifest();
    }

    @Override
    protected void setUpApiEndPoint() throws Exception {
        String apiEndPoint = ApiEndPointProvider.getV1_1EndPoint();
        setApiEndPoint( apiEndPoint );
    }

}