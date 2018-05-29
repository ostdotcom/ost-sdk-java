package com.ost.services;
import com.ost.lib.*;

import java.util.Map;

public abstract class OSTAPIService {
    protected OSTRequestClient request;
    protected String urlPrefix;

    public OSTAPIService(OSTRequestClient ostRequestClient) {
        this.request = ostRequestClient;
        this.urlPrefix = "";
    }

    public OSTAPIService(OSTRequestClient ostRequestClient, String servicePrefix) {
        this(ostRequestClient);
        this.urlPrefix = servicePrefix;
    }

    public String getId( Map<String,Object> params) throws MissingParameter {
        if ( null == params || !params.containsKey("id") || null == params.get("id") ) {
            throw new MissingParameter("id");
        }
        return params.get("id").toString();
    }

    public class MissingParameter extends Exception {
        public MissingParameter(String paramName) {
            super(paramName + " missing in request params");
        }
    }


}
