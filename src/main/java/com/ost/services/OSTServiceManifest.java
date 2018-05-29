package com.ost.services;

import com.ost.lib.OSTRequestClient;

import java.util.Map;

public abstract class OSTServiceManifest {

    protected OSTRequestClient request;
    public OSTServiceManifest( Map<String,Object> params) {
        request = new OSTRequestClient( params );
    }

    public abstract String getApiVersion();
}
