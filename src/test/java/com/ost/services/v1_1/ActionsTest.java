package com.ost.services.v1_1;
import com.ost.services.ApiEndPointProvider;
import org.junit.Before;


public class ActionsTest extends com.ost.services.v1.ActionsTest {
    @Override
    public com.ost.services.v1_1.Actions getService() {
        return (com.ost.services.v1_1.Actions) super.getService();
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        com.ost.services.v1_1.Manifest services = (com.ost.services.v1_1.Manifest) getServiceManifest();
        setService( services.actions );
    }

    @Override
    protected void setService() {
        com.ost.services.v1_1.Manifest services = (com.ost.services.v1_1.Manifest) getServiceManifest();
        setService( services.actions );
    }

    @Override
    protected void setUpApiEndPoint() throws Exception {
        String apiEndPoint = ApiEndPointProvider.getV1_1EndPoint();
        setApiEndPoint( apiEndPoint );
    }

}