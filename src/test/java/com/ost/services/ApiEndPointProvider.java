package com.ost.services;

public class ApiEndPointProvider {


    public static String getV0EndPoint() throws Exception {
        String apiEndPoint = System.getenv("OST_KIT_API_ENDPOINT");
        if ( null == apiEndPoint ) {
            throw new Exception("Environment Variable OST_KIT_API_ENDPOINT is not set.");
        }
        return apiEndPoint;
    }

    public static String getV1EndPoint() throws Exception {
        String apiEndPoint = System.getenv("OST_KIT_API_V1_ENDPOINT");
        if ( null == apiEndPoint ) {
            throw new Exception("Environment Variable OST_KIT_API_V1_ENDPOINT is not set.");
        }
        return apiEndPoint;
    }

    public static String getV1_1EndPoint() throws Exception {
        String apiEndPoint = System.getenv("OST_KIT_API_V1_1_ENDPOINT");
        if ( null == apiEndPoint ) {
            throw new Exception("Environment Variable OST_KIT_API_V1_1_ENDPOINT is not set.");
        }
        return apiEndPoint;
    }

}
