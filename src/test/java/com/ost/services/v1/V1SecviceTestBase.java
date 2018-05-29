package com.ost.services.v1;

import com.ost.services.ServiceTestBase;
import org.junit.Before;

public class V1SecviceTestBase extends ServiceTestBase {
    @Before
    public void setUp() throws Exception {
        String apiEndPoint = System.getenv("OST_KIT_API_V1_ENDPOINT");
        if ( null == apiEndPoint ) {
            throw new Exception("Environment Variable OST_KIT_API_V1_ENDPOINT is not set.");
        }
        super.setUp( apiEndPoint );
    }

    @Override
    public com.ost.services.v1.Manifest getServiceManifest() {
        return (com.ost.services.v1.Manifest) super.getServiceManifest();
    }

}
