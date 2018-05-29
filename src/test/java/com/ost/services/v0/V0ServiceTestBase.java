package com.ost.services.v0;
import org.junit.Before;


public class V0ServiceTestBase extends com.ost.services.ServiceTestBase {

    @Before
    public void setUp() throws Exception {
        String apiEndPoint = System.getenv("OST_KIT_API_ENDPOINT");
        if ( null == apiEndPoint ) {
            throw new Exception("Environment Variable OST_KIT_API_ENDPOINT is not set.");
        }
        super.setUp( apiEndPoint );
    }

    @Override
    public com.ost.services.v0.Manifest getServiceManifest() {
        return (com.ost.services.v0.Manifest) super.getServiceManifest();
    }
}
