package com.ost;
import com.ost.services.OSTServiceManifest;
import java.util.Map;

public class OSTSDK {
    String apiEndpoint;
    String apiEndpointMajorVersion;
    public OSTServiceManifest services;

    public OSTSDK( Map<String, Object> params) {
        if ( params.containsKey("apiEndpoint") && params.get("apiEndpoint") instanceof String ) {
            apiEndpoint = (String) params.get("apiEndpoint");
        } else {
            throw new IllegalArgumentException("Invalid apiEndpoint.");
        }

        String[] endPointSplits = apiEndpoint.split("/");
        if ( endPointSplits.length > 3 ) {
            apiEndpointMajorVersion = endPointSplits[ 3 ];
        } else {
            apiEndpointMajorVersion = "";
        }

        apiEndpointMajorVersion = apiEndpointMajorVersion.toLowerCase();

        if ( apiEndpointMajorVersion.equalsIgnoreCase("v1") ) {
            services = new com.ost.services.v1.Manifest( params );
        } else {
            services = new com.ost.services.v0.Manifest( params );
        }
    }
}
