package com.ost.services;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

public class TransactionsTest extends ServiceTestBase {
    @Override
    public Transactions getService() {
        return (Transactions) super.getService();
    }

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        setService(getServiceManifest().transactions);
        super.setEnvironmentVariables();
    }

    @Override
    public Manifest getServiceManifest() {
        return (Manifest) super.getServiceManifest();
    }

    @Test
    public void get() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("user_id", getEnvironmentVariables().get("userId"));
        params.put("transaction_id", getEnvironmentVariables().get("transactionId"));

        // Test-Case: Get a transaction info.
        JsonObject response;
        response = getService().get(params);
        validateResponseWithSuccess(response);

    }

    @Test
    public void execute() throws Exception {
        HashMap <String,Object> params = new HashMap<String,Object>();
        HashMap <String,Object> nestedparams = new HashMap<String,Object>();
        String parameter2 = "1";

        params.put("user_id", getEnvironmentVariables().get("companyUserId"));
        params.put("to", getEnvironmentVariables().get("ruleAddress"));
        nestedparams.put("method", "directTransfers");
        ArrayList<ArrayList> nestedarraylist = new ArrayList<ArrayList>();
        ArrayList<Object> arrayList1 = new ArrayList<Object>();
        arrayList1.add(getEnvironmentVariables().get("user2TokenHolderAddress"));
        ArrayList<Object> arrayList2 = new ArrayList<Object>();
        Gson gsonObj = new Gson();
        arrayList2.add(parameter2);
        nestedarraylist.add(arrayList1);
        nestedarraylist.add(arrayList2);
        nestedparams.put("parameters", nestedarraylist);
        String jsonStr = gsonObj.toJson(nestedparams);
        params.put("raw_calldata", jsonStr);

        // Test-Case: execute transaction from company.
        JsonObject response;
        response = getService().execute(params);
        validateResponseWithSuccess(response);

    }

    @Test
    public void getList() throws Exception {
        HashMap<String, Object> params = new HashMap<String, Object>();

        params.put("user_id", getEnvironmentVariables().get("userId"));

        // Test-Case: Get all transactions for a user.
        JsonObject response;
        response = getService().getList(params);
        validateResponseWithSuccess(response);

    }

}
