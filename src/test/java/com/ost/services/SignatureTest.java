package com.ost.services;

import com.ost.lib.OSTRequestClient;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SignatureTest extends ServiceTestBase {

    public SignatureTest() {
        super();
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
    }

    public HashMap<String, Object> getParams() {
        HashMap<String, Object> params = new HashMap<String, Object>();
        HashMap<String, Object> emptyHash = new HashMap<String, Object>();
        ArrayList<String> emptyArray = new ArrayList<String>();
        ArrayList<String> list = new ArrayList<String>();
        list.add("");
        list.add(null);
        params.put("a", null);
        params.put("b", emptyArray);
        params.put("c", "");
        params.put("d", list);
        params.put("e", emptyHash);
        ArrayList<String> array = new ArrayList<String>();
        array.add("Hello");
        array.add("There");
        array.add("12345");
        params.put("k1", 125.45);
        params.put("k2", "Tejas");

        HashMap<String, Object> hashWithKeyValue1 = new HashMap<String, Object>();
        hashWithKeyValue1.put("a", "L21A");
        hashWithKeyValue1.put("b", "L21B");
        HashMap<String, Object> hashWithKeyValue2 = new HashMap<String, Object>();
        hashWithKeyValue2.put("a", "L22A");
        hashWithKeyValue2.put("b", "L22B");
        HashMap<String, Object> hashWithKeyValue3 = new HashMap<String, Object>();
        hashWithKeyValue3.put("a", "L23A");
        hashWithKeyValue3.put("b", "L23B");

        HashMap<String, Object> nestedparams = new HashMap<String, Object>();
        nestedparams.put("a" , hashWithKeyValue1);
        nestedparams.put("b" , hashWithKeyValue2);
        nestedparams.put("c" , hashWithKeyValue3);


        params.put("aaaaa", nestedparams);
        params.put("arrayValues", array);
        params.put("garbage_str", "~!@#$%^&*()_+-= {}[]:\";'?/<>,. this is garbage");


        return params;
    }

    @Test
    public void testSignature() throws Exception {

        HashMap<String, Object> params = getParams();
        String apiKey = "b921b46142cbfbea3328d9d257ac5b0d";
        String apiSecret = "a0431203671f42c079b2154066fd04ba";
        String apiEndpoint = "https://api.ost.com/mainnet/v2/";

        HashMap<String, Object> ostParams = getParams();
        ostParams.put("apiSecret",apiSecret);
        ostParams.put("apiKey",apiKey);
        ostParams.put("apiEndpoint",apiEndpoint);

        // Test-Case: Test Signature.
        String signature;
        OSTRequestClient obj = new OSTRequestClient(ostParams);
        signature = getSignature("/api/v2/users", params, obj);
        boolean success;
        if (signature.equalsIgnoreCase("28664cdbc613b66835d7bcf825dce719fb8e0621992c291ba9bd1767c1c5560d")) {
            success = true;
        } else {
            success = false;
        }
        Assert.assertEquals( success, true);
    }

    public String getSignature(String resource, Map<String, Object> paramValObj, OSTRequestClient obj) {
        ArrayList<OSTRequestClient.HttpParam> paramsArray = (ArrayList<OSTRequestClient.HttpParam>) obj.getRequestParam(resource,paramValObj);
        String paramKey;
        String paramVal;
        Iterator it = paramsArray.iterator();
        while (it.hasNext()) {
            OSTRequestClient.HttpParam pair = (OSTRequestClient.HttpParam) it.next();

            paramKey = pair.getParamName();
            paramVal = pair.getParamValue();

            if(paramKey.equalsIgnoreCase("api_signature"))
            {
                return paramVal;
            }
        }
        return "";
    }
}