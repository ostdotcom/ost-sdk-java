package com.ost.services;

import java.util.Map;

public class Manifest extends OSTServiceManifest {

    public Users users;
    public Tokens tokens;
    public Sessions sessions;
    public PricePoints pricePoints;
    public Devices devices;
    public Chains chains;


    public Manifest( Map<String, Object> params) {
        super(params);
        init();
    }

    protected void init() {
        this.users = new Users( this.request );
        this.tokens = new Tokens( this.request );
        this.sessions = new Sessions( this.request );
        this.pricePoints = new PricePoints( this.request );
        this.devices = new Devices( this.request );
        this.chains = new Chains( this.request );
    }

}
