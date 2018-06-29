package com.ost.services.v1_1;

import com.ost.services.ApiEndPointProvider;

public class AirDropsTest extends com.ost.services.v1.AirDropsTest {
    @Override
    public com.ost.services.v1_1.AirDrops getService() {
        return (com.ost.services.v1_1.AirDrops) super.getService();
    }

    protected void setService() {
        com.ost.services.v1_1.Manifest services = (com.ost.services.v1_1.Manifest) getServiceManifest();
        setService( services.airdrops );
    }

    @Override
    protected void setUpApiEndPoint() throws Exception {
        String apiEndPoint = ApiEndPointProvider.getV1_1EndPoint();
        setApiEndPoint( apiEndPoint );
    }

}