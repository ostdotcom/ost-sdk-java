package com.ost.services.v1_1;

import java.util.Map;

public class Manifest extends com.ost.services.v1.Manifest {

    public Balances balances;
    public Ledger ledger;

    public Manifest( Map<String, Object> params) {
        super(params);
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
