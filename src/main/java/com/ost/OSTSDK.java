package com.ost;
import com.ost.services.Manifest;
import com.ost.services.OSTServiceManifest;
import java.util.Map;

public class OSTSDK {
    String apiEndpoint;
    String apiKey;
    String apiSecret;
    String apiEndpointVersion;
    public OSTServiceManifest services;

    public OSTSDK( Map<String, Object> params) {
        if (params.containsKey("apiEndpoint") && params.get("apiEndpoint") instanceof String) {
            apiEndpoint = (String) params.get("apiEndpoint");
        } else {
            throw new IllegalArgumentException("Invalid apiEndpoint.");
        }

        if (params.containsKey("apiKey") && params.get("apiKey") instanceof String &&
                params.get("apiKey") != "") {
            apiKey = (String) params.get("apiKey");
        } else {
            throw new IllegalArgumentException("Invalid apiKey.");
        }

        if (params.containsKey("apiSecret") && params.get("apiSecret") instanceof String &&
                params.get("apiSecret") != "") {
            apiSecret = (String) params.get("apiSecret");
        } else {
            throw new IllegalArgumentException("Invalid apiSecret.");
        }

        String[] endPointSplits = apiEndpoint.split("/");
        if (endPointSplits[4] instanceof String && endPointSplits[4].equalsIgnoreCase("v2")) {
            apiEndpointVersion = endPointSplits[4];
        } else {
            throw new IllegalArgumentException("Invalid apiEndpointVersion.");
        }

        services = new Manifest(params);
    }
}
