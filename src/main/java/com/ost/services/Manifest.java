package com.ost.services;

import java.util.Map;

public class Manifest extends OSTServiceManifest {

    public Users users;
    public Tokens tokens;
    public Sessions sessions;
    public DeviceManagers deviceManagers;
    public Rules rules;
    public Transactions transactions;
    public PricePoints pricePoints;
    public Balance balance;
    public RecoveryOwners recoveryOwners;
    public Devices devices;
    public Chains chains;
    public BaseTokens baseTokens;


    public Manifest( Map<String, Object> params) {
        super(params);
        init();
    }

    protected void init() {
        this.users = new Users( this.request );
        this.tokens = new Tokens( this.request );
        this.deviceManagers = new DeviceManagers( this.request );
        this.rules = new Rules( this.request );
        this.transactions = new Transactions( this.request );
        this.sessions = new Sessions( this.request );
        this.pricePoints = new PricePoints( this.request );
        this.balance = new Balance( this.request );
        this.recoveryOwners = new RecoveryOwners( this.request );
        this.devices = new Devices( this.request );
        this.chains = new Chains( this.request );
        this.baseTokens = new BaseTokens( this.request );
    }

}
