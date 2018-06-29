package com.ost.services.v1_1;

import com.ost.services.ApiEndPointProvider;

import static org.junit.Assert.*;

public class TransactionsTest extends com.ost.services.v1.TransactionsTest {
    @Override
    public com.ost.services.v1_1.Transactions getService() {
        return (com.ost.services.v1_1.Transactions) super.getService();
    }

    @Override
    protected void setUpApiEndPoint() throws Exception {
        String apiEndPoint = ApiEndPointProvider.getV1_1EndPoint();
        setApiEndPoint( apiEndPoint );
    }

    @Override
    protected void setService() {
        com.ost.services.v1_1.Manifest services = (com.ost.services.v1_1.Manifest) getServiceManifest();
        setService( services.transactions);
        actionsService = services.actions;
    }
}