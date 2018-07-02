package com.ost.services.v1_1;

import com.ost.services.OSTServiceManifest;

import java.util.Map;

public class Manifest extends OSTServiceManifest {

    public Actions actions;
    public AirDrops airdrops;
    public Token token;
    public Transactions transactions;
    public Transfers transfers;
    public Users users;

    public Balances balances;
    public Ledger ledger;


    public Manifest( Map<String, Object> params) {
        super(params);
        init();
    }

    protected void init() {
        this.actions = new Actions( this.request );
        this.airdrops = new AirDrops( this.request );
        this.token = new Token( this.request );
        this.transactions = new Transactions( this.request );
        this.transfers = new Transfers( this.request );
        this.users = new Users( this.request );
        this.balances = new Balances( this.request );
        this.ledger = new Ledger( this.request );
    }

    @Override
    public String getApiVersion() {
        return "v1.1";
    }

}
