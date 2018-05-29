package com.ost.services.v0;

import com.ost.services.OSTServiceManifest;

import java.util.Map;

public class Manifest extends OSTServiceManifest {

    public TransactionKind transactionKind;
    public User user;

    public Manifest(Map<String, Object> params) {
        super(params);
        init();
    }

    private void init() {
        this.transactionKind = new TransactionKind( this.request );
        this.user = new User( this.request );
    }
    public String getApiVersion() {
        return "v0";
    }
}
