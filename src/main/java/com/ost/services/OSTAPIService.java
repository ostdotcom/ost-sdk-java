package com.ost.services;
import com.google.common.escape.Escaper;
import com.google.common.net.UrlEscapers;
import com.ost.lib.*;

import java.util.Map;
import java.util.regex.Pattern;

public abstract class OSTAPIService {
    protected OSTRequestClient request;
    protected String urlPrefix;
    protected String urlSuffix;
    private static final Escaper FormParameterEscaper = UrlEscapers.urlFormParameterEscaper();


    public OSTAPIService(OSTRequestClient ostRequestClient) {
        this.request = ostRequestClient;
        this.urlPrefix = "";
        this.urlSuffix = "";
    }

    public OSTAPIService(OSTRequestClient ostRequestClient, String servicePrefix, String serviceSuffix) {
        this(ostRequestClient);
        this.urlPrefix = servicePrefix;
        this.urlSuffix = serviceSuffix;
    }

    public String getSessionAddress(Map<String, Object> params) throws MissingParameter, InvalidParameter {
        String sessionAddress = "";
        if (null == params || !params.containsKey("session_address") || null == params.get("session_address")) {
            throw new MissingParameter("session_address");
        }
        if (!isValidParameter(params.get("session_address"))) {
            throw new InvalidParameter("session_address");
        }
        sessionAddress = params.get("session_address").toString();
        params.remove("session_address");
        return specialCharacterEscape(sessionAddress);
    }

    public String getDeviceAddress(Map<String, Object> params) throws MissingParameter, InvalidParameter {
        String deviceAddress = "";
        if (null == params || !params.containsKey("device_address") || null == params.get("device_address")) {
            throw new MissingParameter("device_address");
        }
        if (!isValidParameter(params.get("device_address"))) {
            throw new InvalidParameter("device_address");
        }
        deviceAddress = params.get("device_address").toString();
        params.remove("device_address");
        return specialCharacterEscape(deviceAddress);
    }

    public String getChainId(Map<String, Object> params) throws MissingParameter, InvalidParameter {
        String chainId = "";
        if (null == params || !params.containsKey("chain_id") || null == params.get("chain_id")) {
            throw new MissingParameter("chain_id");
        }
        if (!isValidParameter(params.get("chain_id"))) {
            throw new InvalidParameter("chain_id");
        }
        chainId = params.get("chain_id").toString();
        params.remove("chain_id");
        return specialCharacterEscape(chainId);
    }

    public String getTransactionId(Map<String, Object> params) throws MissingParameter, InvalidParameter {
        String transactionId = "";
        if (null == params || !params.containsKey("transaction_id") || null == params.get("transaction_id")) {
            throw new MissingParameter("transaction_id");
        }
        if (!isValidParameter(params.get("transaction_id"))) {
            throw new InvalidParameter("transaction_id");
        }
        transactionId = params.get("transaction_id").toString();
        params.remove("transaction_id");
        return specialCharacterEscape(transactionId);
    }

    public String getRedemptionId(Map<String, Object> params) throws MissingParameter, InvalidParameter {
            String redemptionId = "";
            if (null == params || !params.containsKey("redemption_id") || null == params.get("redemption_id")) {
                throw new MissingParameter("redemption_id");
            }
            if (!isValidParameter(params.get("redemption_id"))) {
                throw new InvalidParameter("redemption_id");
            }
            redemptionId = params.get("redemption_id").toString();
            params.remove("redemption_id");
            return specialCharacterEscape(redemptionId);
    }

    public String getRedeemableSkuId(Map<String, Object> params) throws MissingParameter, InvalidParameter {
                String redeemableSkuId = "";
                if (null == params || !params.containsKey("redeemable_sku_id") || null == params.get("redeemable_sku_id")) {
                    throw new MissingParameter("redeemable_sku_id");
                }
                if (!isValidParameter(params.get("redeemable_sku_id"))) {
                    throw new InvalidParameter("redeemable_sku_id");
                }
                redeemableSkuId = params.get("redeemable_sku_id").toString();
                params.remove("redeemable_sku_id");
                return specialCharacterEscape(redeemableSkuId);
        }

    public String getRecoveryOwnerAddress(Map<String, Object> params) throws MissingParameter, InvalidParameter {
        String recoveryOwnerAddress = "";
        if (null == params || !params.containsKey("recovery_owner_address") || null == params.get("recovery_owner_address")) {
            throw new MissingParameter("recovery_owner_address");
        }
        if (!isValidParameter(params.get("recovery_owner_address"))) {
            throw new InvalidParameter("recovery_owner_address");
        }
        recoveryOwnerAddress = params.get("recovery_owner_address").toString();
        params.remove("recovery_owner_address");
        return specialCharacterEscape(recoveryOwnerAddress);
    }

    public String getId(Map<String, Object> params) throws MissingParameter, InvalidParameter {
        String id = "";
        if (null == params || !params.containsKey("id") || null == params.get("id")) {
            throw new MissingParameter("id");
        }
        if (!isValidParameter(params.get("id"))) {
            throw new InvalidParameter("id");
        }
        id = params.get("id").toString();
        params.remove("id");
        return id;
    }

    public String getUserId(Map<String, Object> params) throws MissingParameter, InvalidParameter {
        String user_id = "";
        if (null == params || !params.containsKey("user_id") || null == params.get("user_id")) {
            throw new MissingParameter("user_id");
        }
        if (!isValidParameter(params.get("user_id"))) {
            throw new InvalidParameter("user_id");
        }

        user_id = params.get("user_id").toString();
        params.remove("user_id");

        return user_id;
    }

    public String getWebhookId(Map<String, Object> params) throws MissingParameter, InvalidParameter {
        String webhook_id = "";
        if (null == params || !params.containsKey("webhook_id") || null == params.get("webhook_id")) {
            throw new MissingParameter("webhook_id");
        }
        if (!isValidParameter(params.get("webhook_id"))) {
            throw new InvalidParameter("webhook_id");
        }

        webhook_id = params.get("webhook_id").toString();
        params.remove("webhook_id");

        return webhook_id;
    }

    public Boolean isValidParameter(Object params) throws InvalidParameter {
        if(params instanceof Number){
            return true;
        }else if(params instanceof String){
            Pattern p = Pattern.compile("[0-9a-zA-Z.-]+");
            return p.matcher((String) params).matches();
        }else{
            return false;
        }
    }

    public class MissingParameter extends Exception {
        public MissingParameter(String paramName) {
            super(paramName + " missing in request params");
        }
    }

    public class InvalidParameter extends Exception {
        public InvalidParameter(String paramName) {
            super(paramName + " is invalid");
        }
    }

    private static String specialCharacterEscape(String stringToEscape){
        stringToEscape = FormParameterEscaper.escape(stringToEscape);
        stringToEscape = stringToEscape.replace("*", "%26");
        return stringToEscape;
    }


}
