package com.ost.services.v1;

import com.ost.services.OSTServiceManifest;

import java.util.Map;

public class Manifest extends OSTServiceManifest {

    public Actions actions;
    public AirDrops airdrops;
    public Token token;
    public Transactions transactions;
    public Transfers transfers;
    public Users users;

    public Manifest( Map<String, Object> params) {
        super(params);
        init();
    }

    private void init() {
        this.actions = new Actions( this.request );
        this.airdrops = new AirDrops( this.request );
        this.token = new Token( this.request );
        this.transactions = new Transactions( this.request );
        this.transfers = new Transfers( this.request );
        this.users = new Users( this.request );
    }

    public String getApiVersion() {
        return "v1";
    }
}
