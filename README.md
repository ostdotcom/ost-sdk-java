# OST Server-Side Java SDK
[![Build Status](https://travis-ci.org/ostdotcom/ost-sdk-java.svg?branch=develop)](https://travis-ci.org/ostdotcom/ost-sdk-java)

[OST](https://dev.ost.com/) Platform SDK for Java.

## Introduction

OST is a complete technology solution enabling mainstream businesses 
to easily launch blockchain based economies without requiring blockchain development.

Brand Tokens (BTs) are white-label cryptocurrency tokens with utility representations 
running on highly-scalable Ethereum-based utility blockchains, 
backed by value token (such as OST, USDC) staked on Ethereum mainnet. Within a business`s 
token economy, BTs can only be transferred to whitelisted user addresses. 
This ensures that they stay within the token economy.

The OST technology stack is designed to give businesses everything they need 
to integrate, test, and deploy BTs. Within the OST suite of products, developers 
can use OST Platform to create, test, and launch Brand Tokens.

OST APIs and server-side SDKs make it simple and easy for developers to 
integrate blockchain tokens into their apps.

For documentation, visit [https://dev.ost.com/](https://dev.ost.com/)

## Getting Started

### Setup Brand Token
1. Sign-up on [OST Platform](https://platform.ost.com) and setup your Brand Token.
2. Obtain your API Key and API Secret from [developers page](https://platform.ost.com/mainnet/developer).

### Installation

#### Maven users
* Set JAVA_7_HOME ENV variable
```bash
export JAVA_7_HOME='dir path to java 7 home which has bin folder'
```

* Add this dependency to your project's POM
```xml
<dependency>
  <groupId>com.ost</groupId>
  <artifactId>ost-sdk-java</artifactId>
  <version>2.2.1</version>
</dependency>
```

#### Building from source using Maven

Clone the repository:
```bash
git clone https://github.com/ostdotcom/ost-sdk-java.git
cd ost-sdk-java
```

Package using MVN (without dependencies):
```bash
mvn clean package -DskipTests
```

Package using MVN (with dependencies):
```bash
mvn clean compile assembly:single -DskipTests
```

The jar file can be found in the target folder.

## Usage

* Initialize the SDK object:
	```java
	// Declare connection parameters.

	// Mandatory API parameters

	String apiKey = "__abc"; // OBTAINED FROM DEVELOPER PAGE
	String apiSecret = "_xyz";  // OBTAINED FROM DEVELOPER PAGE

	/* 
	  The valid API endpoints are:
	  1. Mainnet: "https://api.ost.com/mainnet/v2/"
	  2. Testnet: "https://api.ost.com/testnet/v2/"
	*/

	String apiEndPoint = "https://api.ost.com/testnet/v2/";

	HashMap <String,Object> sdkConfig = new HashMap<String,Object>();
	sdkConfig.put("apiEndpoint", apiEndPoint);
	sdkConfig.put("apiKey", apiKey);
	sdkConfig.put("apiSecret", apiSecret);

	// Optional API parameters

	// This is the timeout in seconds for which the socket connection will remain open.
	long timeoutInSeconds = 60; // The value of timeout will always be of type long.
	
	HashMap <String,Object> nestedparam = new HashMap<String,Object>();

	nestedparam.put("timeout", timeoutInSeconds);
	sdkConfig.put("config", nestedparam);

    // OST server side sdk object.
	OSTSDK ostObj = new OSTSDK(sdkConfig);
	com.ost.services.Manifest services = (com.ost.services.Manifest) ostObj.services;
	```

### Users Module

* Initialize Users service object to perform user specific actions.

	```java
	com.ost.services.Users usersService = services.users;
	```

* Create User. This creates a unique identifier for each user.

	```java
	HashMap <String,Object> params = new HashMap<String,Object>();
	JsonObject response = usersService.create( params );
	System.out.println("response: " + response.toString() );
	```

* Get User Detail using the userId obtained in user create.

	```java
	// Mandatory API parameters

	// UserId of user for whom user details needs to be fetched.
	String userId = "c2c__";

	HashMap <String,Object> params = new HashMap<String,Object>();
	params.put("user_id", userId);
	JsonObject response = usersService.get( params );
	System.out.println("response: " + response.toString() );
	```

* Get Users List. Pagination is supported in this API.

	```java
	// Mandatory API parameters
	// No mandatory parameters.

	// Optional API parameters  

	// Array of userIds for which data needs to be fetched.
	ArrayList<Object> userIdsArray = new ArrayList<Object>();
	userIdsArray.add("c2c__");
    userIdsArray.add("d2c__");

	// Pagination identifier from the previous API call response. Not needed for page one.
	String paginationIdentifier = "e77y___";

	// Limit.
	long limit = 10;

	HashMap <String,Object> params = new HashMap<String,Object>();
	params.put("ids", userIdsArray);
	params.put("limit", limit);
	params.put("pagination_identifier", paginationIdentifier);

	JsonObject response = usersService.getList( params );
	System.out.println("response: " + response.toString() );
	```

### Devices Module

* Initialize Devices service object to perform device specific actions.

	```java
	com.ost.services.Devices devicesService = services.devices;
	```

* Create a Device for User.

	```java
	// Mandatory API parameters

	// UserId of user for whom device needs to be created.
	String userId = "c2c___";

	// Device address of user's device.
	String deviceAddress = "0x1Ea___";

	// Device API signer address.
	String apiSignerAddress = "0x5F8___";

	HashMap <String,Object> params = new HashMap<String,Object>();
	params.put("user_id", userId);
	params.put("address", deviceAddress);
	params.put("api_signer_address", apiSignerAddress);

	JsonObject response = devicesService.create( params );
	System.out.println("response: " + response.toString() );
	```

* Get User Device Detail using userId and deviceAddress.

	```java
	// Mandatory API parameters

	// UserId of user for whom device details needs to be fetched.
	String userId = "c2c___";

	// Device address of user's device.
	String deviceAddress = "0x1E___";

	HashMap <String,Object> params = new HashMap<String,Object>();
	params.put("user_id", userId);
	params.put("device_address", deviceAddress);

	JsonObject response = devicesService.get( params );
	System.out.println("response: " + response.toString() );
	```

* Get User Devices List. Pagination is supported by this API.

	```java
	// Mandatory API parameters

	// UserId of user for whom device details needs to be fetched.
	String userId = "c2c6___";

	// Optional API parameters

	// Pagination identifier from the previous API call response. Not needed for page one.
	String paginationIdentifier = "eyJ___";

	// Array of device addresses of end user.
	ArrayList<Object> deviceAddressesArray = new ArrayList<Object>();
	deviceAddressesArray.add("0x59___");
	deviceAddressesArray.add("0xab___");

	// Limit.
	long limit = 10; 

	HashMap <String,Object> params = new HashMap<String,Object>();
	params.put("user_id", userId);
	params.put("addresses", deviceAddressesArray);
	params.put("pagination_identifier", paginationIdentifier);
	params.put("limit", limit);

	JsonObject response = devicesService.getList( params );
	System.out.println("response: " + response.toString() );
	```

### Device Managers Module

* Initialize Device Manager service object to perform device manager specific actions.

	```java
	com.ost.services.DeviceManagers deviceManagersService = services.deviceManagers;
	```

* Get Device Manager Detail using userId.

	```java
	// Mandatory API parameters

	// UserId of user for whom device manager details needs to be fetched.
	String userId = "c2c___";

	HashMap <String,Object> params = new HashMap<String,Object>();
	params.put("user_id", userId);

	JsonObject response = deviceManagersService.get( params );
	System.out.println("response: " + response.toString() );
	```

### Sessions Module

* Initialize Sessions service object to perform session specific actions.

	```java
	com.ost.services.Sessions sessionsService = services.sessions;
	```

* Get User Session Detail using userId and session address.

	```java
	// Mandatory API parameters

	// UserId of user for whom session details needs to be fetched.
	String userId = "c2c___";

	// Session address of user for which details needs to be fetched.
	String sessionAddress = "0x1Ea___";

	HashMap <String,Object> params = new HashMap<String,Object>();
	params.put("user_id", userId);
	params.put("session_address", sessionAddress);

	JsonObject response = sessionsService.get( params );
	System.out.println("response: " + response.toString() );
	```

* Get User Sessions List using userId. Pagination is supported by this API.

	```java
	// Mandatory API parameters

	// UserId of user for whom session details needs to be fetched.
	String userId = "c2c___";

	// Optional API parameters:

	// Pagination identifier from the previous API call response. Not needed for page one.
	String paginationIdentifier = "eyJs___";

	// Array of session addresses of end user.
	ArrayList<Object> sessionAddressesArray = new ArrayList<Object>();
	sessionAddressesArray.add("0x59___");
	sessionAddressesArray.add("0xab___");

	// Limit.
	long limit = 10; 

	HashMap <String,Object> params = new HashMap<String,Object>();
	params.put("user_id", userId);
	params.put("pagination_identifier", paginationIdentifier);
	params.put("addresses", sessionAddressesArray);
	params.put("limit", limit);

	JsonObject response = sessionsService.getList( params );
	System.out.println("response: " + response.toString() );
	```

### Executing Transactions

For executing transactions, you need to understand the 4 modules described below.

#### Rules Module

* Initialize Rules service object to perform rules specific actions.

	```java
	com.ost.services.Rules rulesService = services.rules;
	```

* List Rules.

	```java
	HashMap <String,Object> params = new HashMap<String,Object>();
	JsonObject response = rulesService.getList( params );
	System.out.println("response: " + response.toString() );
	```

#### Price Points Module

* Initialize Price Points service object to perform price points specific actions.

	```java
	com.ost.services.PricePoints pricePointsService = services.pricePoints;
	```

* Get Price Points Detail.

	```java
	// Mandatory API parameters

	// ChainId of your brand token economy.
	long chainId = 2000;

	HashMap <String,Object> params = new HashMap<String,Object>();
	params.put("chain_id", chainId);

	JsonObject response = pricePointsService.get( params );
	System.out.println("response: " + response.toString() );
	```

#### Transactions Module

* Initialize Transactions service object to perform transaction specific actions.

	```java
	com.ost.services.Transactions transactionsService = services.transactions;
	```

* DIRECT-TRANSFERS execute transaction should be used to transfer BTs to your end-users.

	```java
	// Mandatory API parameters

	// Token holder address of receiver.
	String transferToAddress = "0xa3___";

	// Amount of tokens to be transferred.
	String transferAmount = "1";

	// Company userId.
	String companyUserId = "ee89___";

	// Address of DirectTransfer rule. Use list rules API of Rules module to get the address of rules.
	// In the rules array which you will get in response, use the address having name "Direct Transfer".
	String directTransferRuleAddress = "0xe379___";

	// Parameters required for rule execution.
	ArrayList<Object> arrayListForReceiverTokenHolderAddress = new ArrayList<Object>();
	arrayListForReceiverTokenHolderAddress.add(transferToAddress);

	ArrayList<Object> arrayListAmount = new ArrayList<Object>();
	arrayListAmount.add(transferAmount);

	ArrayList<ArrayList> nestedArraylist = new ArrayList<ArrayList>();
	nestedArraylist.add(arrayListForReceiverTokenHolderAddress);
	nestedArraylist.add(arrayListAmount);

	// Parameters required for rule execution.
	HashMap <String,Object> nestedparams = new HashMap<String,Object>();
	nestedparams.put("method", "directTransfers");  // Rule name which needs to be passed as-is.
	nestedparams.put("parameters", nestedArraylist);

	Gson gsonObj = new Gson();
	String jsonStr = gsonObj.toJson(nestedparams);


	HashMap <String,Object> params = new HashMap<String,Object>();

	params.put("user_id", companyUserId);
	params.put("to", directTransferRuleAddress);
	params.put("raw_calldata", jsonStr);

	// Optional API parameters

	// Name of the transaction. Eg. "like", "download", etc.
	// NOTE: Max length 25 characters (Allowed characters: [A-Za-z0-9_/s])
	String transactionName = "like";

	// Transaction type. Possible values: "company_to_user", "user_to_user", "user_to_company".
	String transactionType = "company_to_user";

	// Some extra information about transaction.
	// NOTE: Max length 125 characters (Allowed characters: [A-Za-z0-9_/s])
	String details = "lorem_ipsum";

	// Additional transaction information. There is no dependency between any of the metaProperty keys. 
	// However, if a key is present, its value cannot be null or undefined.       
	HashMap <String,Object> metaProperty = new HashMap<String,Object>();
	metaProperty.put("name", transactionName); 
	metaProperty.put("type", transactionType); 
	metaProperty.put("details", details); 

	params.put("meta_property", metaProperty);

	JsonObject response = transactionsService.execute( params );
	System.out.println("response: " + response.toString() );
	```

* PAY Execute Transaction should be used when transactions of BTs equivalent to some fiat amount need to be executed.

	```java
	// Mandatory API parameters

	// Token holder address of receiver.
	String transferToAddress = "0xa31__";

	// Company token holder address.
	String companyTokenHolderAddress = "0xa963___";


	// Pay currency code. Supported currency codes are "USD", "EUR" and "GBP".
	String payCurrencyCode = "USD";

	// In pay transaction, the transfer amounts are in pay currency (fiat currency like USD) which then are converted 
	// into tokens. Use get price point detail API of Price Points module to get this value.
	double pricePoint = 0.020606673;

	// Price point needs to be passed in atto. Multiply the price point with 10^18. Also, this value should be a string.
	BigDecimal intendedPricePointBD = new BigDecimal(pricePoint).multiply((new BigDecimal(10)).pow(18));
	String intendedPricePointInAtto = intendedPricePointBD.toString().split("\\.")[0];

	// Amount of Fiat to be transferred.
	double transferAmountInFiat = 0.1;

    // Transfer amount in wei needs to be passed in atto. Multiply the fiat transfer amount with 10^18. Also, this value should be a string. 
    BigDecimal fiatTransferAmountInWeiBD = new BigDecimal(transferAmountInFiat).multiply((new BigDecimal(10)).pow(18));
	String fiatTransferAmountInAtto = fiatTransferAmountInWeiBD.toString().split("\\.")[0];
	  
	// Parameters required for rule execution.
	ArrayList<Object> arrayListForReceiverTokenHolderAddress = new ArrayList<Object>();
	arrayListForReceiverTokenHolderAddress.add(transferToAddress);

	ArrayList<Object> arrayListAmount = new ArrayList<Object>();
	arrayListAmount.add(fiatTransferAmountInAtto);
	Gson gsonObj = new Gson();

	ArrayList<Object> nestedArraylist = new ArrayList<Object>();
	nestedArraylist.add(companyTokenHolderAddress);
	nestedArraylist.add(arrayListForReceiverTokenHolderAddress);
	nestedArraylist.add(arrayListAmount);
	nestedArraylist.add(payCurrencyCode);
	nestedArraylist.add(intendedPricePointInAtto);

	HashMap <String,Object> nestedparams = new HashMap<String,Object>();
	nestedparams.put("method", "pay");  // Rule name which needs to be passed as-is.
	nestedparams.put("parameters", nestedArraylist);
  
	String jsonRawCallData = gsonObj.toJson(nestedparams);

	// Company userId.
	String companyUserId = "ee8___";

	// Address of Pay rule. Use list rules API to get the address of rules.
	// In the rules array which you will get in response, use the address having name "Pricer".
	String payRuleAddress = "0xe37___";
	  
	/* Optional API parameters: */
	    
	// Name of the transaction. Eg. "like", "download", etc.
	// NOTE: Max length 25 characters (Allowed characters: [A-Za-z0-9_/s])
	String transactionName = "like";

	// Transaction type. Possible values: "company_to_user", "user_to_user", "user_to_company".
	String transactionType = "company_to_user";

	// Some extra information about transaction.
	// NOTE: Max length 125 characters (Allowed characters: [A-Za-z0-9_/s])
	String details = "lorem_ipsum";

	HashMap <String,Object> metaProperty = new HashMap<String,Object>();
	metaProperty.put("name", transactionName);
	metaProperty.put("type", transactionType);
	metaProperty.put("details", details); 


	HashMap <String,Object> params = new HashMap<String,Object>();
	params.put("user_id", companyUserId);
	params.put("to", payRuleAddress);

	params.put("meta_property", metaProperty);
	params.put("raw_calldata", jsonRawCallData);

	JsonObject response = transactionsService.execute( params );
	System.out.println("response: " + response.toString() );
	```

* Get Transaction Detail using userId and transactionId.

	```java
	// Mandatory API parameters

	// UserId of end-user.
	String userId = "ee8___";

	// Unique identifier of the transaction to be retrieved.
	String transactionId = "f1d___";

	HashMap <String,Object> params = new HashMap<String,Object>();
	params.put("user_id", userId);
	params.put("transaction_id", transactionId);

	JsonObject response = transactionsService.get( params );
	System.out.println("response: " + response.toString() );
	```

* Get User Transactions using userId. Pagination is supported by this API.

	```java
	// Mandatory API parameters

	// UserId of end-user.
	String userId = "ee89___";

	// Optional API parameters

	// Array of status values.
	ArrayList<Object> statusesArray = new ArrayList<Object>();
	statusesArray.add("CREATED");
	statusesArray.add("SUBMITTED");
	statusesArray.add("SUCCESS");
	statusesArray.add("FAILED");

	// Name of the transaction. Eg. "like", "download", etc. 
	// NOTE: Max length 25 characters (Allowed characters: [A-Za-z0-9_/s])
	String transactionName = "like";

	// Transaction type. Possible values: "company_to_user", "user_to_user", "user_to_company".
	String transactionType = "company_to_user";

	// NOTE: Max length 125 characters (Allowed characters: [A-Za-z0-9_/s])
	String details = "lorem_ipsum";

	// Additional transaction information. There is no dependency between any of the metaProperty keys. 
	// However, if a key is present, its value cannot be null or undefined. 
	ArrayList<HashMap<String, Object>> metaPropertyArray = new ArrayList<HashMap<String, Object>>();
	HashMap <String,Object> metaPropertyArrayParams = new HashMap<String,Object>();
	metaPropertyArrayParams.put("name", transactionName); 
	metaPropertyArrayParams.put("type", transactionType); 
	metaPropertyArrayParams.put("details", details); 
	metaPropertyArray.add(metaPropertyArrayParams);

	Gson gsonObj = new Gson();
	String metaPropertyArrayJsonStr = gsonObj.toJson(metaPropertyArray);

	// Limit.
	long limit = 10;

	// Pagination identifier from the previous API call response.  Not needed for page one.
	String paginationIdentifier = "eyJsY___";

	HashMap <String,Object> params = new HashMap<String,Object>();
	params.put("user_id", userId);
	params.put("statuses", statusesArray);
	params.put("meta_properties", metaPropertyArrayJsonStr);
	params.put("limit", limit);
	params.put("pagination_identifier", paginationIdentifier);

	JsonObject response = transactionsService.getList( params );
	System.out.println("response: " + response.toString() );
	```

#### Balances Module

* Initialize Balances service object to perform balances specific actions.

	```java
	com.ost.services.Balance balancesService = services.balance;
	```

* Get User Balance using userId.

	```java
	// Mandatory API parameters

	// UserId for whom balance needs to be fetched.
	String userId = "c2c6___";

	HashMap <String,Object> params = new HashMap<String,Object>();
	params.put("user_id", userId);

	JsonObject response = balancesService.get( params );
	System.out.println("response: " + response.toString() );
	```

### Recovery Owners Module

* Initialize Recovery Owners service object to perform recovery owners specific actions.

	```java
	com.ost.services.RecoveryOwners recoveryOwnersService = services.recoveryOwners;
	```

* Get Recovery Owner Detail using userId and recovery owner address.

	```java
	// Mandatory API parameters

	// UserId for whom recovery details needs to be fetched.
	String userId = "c2c___";

	// Recovery address of user.
	String recoveryOwnerAddress = "0xe37___";

	HashMap <String,Object> params = new HashMap<String,Object>();
	params.put("user_id", userId);
	params.put("recovery_owner_address", recoveryOwnerAddress);

	JsonObject response = recoveryOwnersService.get( params );
	System.out.println("response: " + response.toString() );
	```

### Tokens Module

* Initialize Tokens service object to perform tokens specific actions.

	```java
	com.ost.services.Tokens tokensService = services.tokens;
	```

* Get Token Detail.

	```java
	HashMap <String,Object> params = new HashMap<String,Object>();
	JsonObject response = tokensService.get( params );
	System.out.println("response: " + response.toString() );
	```

### Chains Module

* Initialize Chains service object to perform chains specific actions.

	```java
	com.ost.services.Chains chainsService = services.chains;
	```

* Get Chain Detail using chainId.

	```java
	// Mandatory API parameters

	// ChainId for which details needs to be fetched. Only origin chainId and OST-specific auxiliary chainIds are allowed.
	String chainId = "2000";

	HashMap <String,Object> params = new HashMap<String,Object>();
	params.put("chain_id", chainId);

	JsonObject response = chainsService.get( params );
	System.out.println("response: " + response.toString() );
	```

### Base Tokens Module

* Initialize Base Tokens service object to perform base tokens specific actions.

	```java
	com.ost.services.BaseTokens baseTokensService = services.baseTokens;
	```

* Get Base Tokens Detail.

	```java
	HashMap <String,Object> params = new HashMap<String,Object>();
	JsonObject response = baseTokensService.get( params );
	System.out.println("response: " + response.toString() );
	```

### Webhooks Module

* Initialize Webhooks service object to perform webhooks specific actions.

	```java
	com.ost.services.Webhooks webhooksService = services.webhooks;
	```

* Create Webhook using the topics and the subscription url.

	```java
	// Mandatory API parameters

	// Array of topics.
	ArrayList<String> topicParams = new ArrayList<String>();
	topicParams.add("transactions/initiate");
	topicParams.add("transactions/success");
	  
	// URL where you want to receive the event notifications.
	String url = "https://www.testingWebhooks.com";

	// Optional API parameters

	// Status of a webhook. Possible values are "active" and "inactive".
	String status = "active"; 

	HashMap<String, Object> params = new HashMap<String, Object>();
	params.put("url", url);
	params.put("status", status);
	params.put("topics", topicParams);

	JsonObject response = webhooksService.create( params );
	System.out.println("response: " + response.toString() );
	```

* Update existing Webhook using a webhookId and an array of topics.

	```java
	// Mandatory API parameters

	// Array of topics.
	ArrayList<String> topicParams = new ArrayList<String>();
	topicParams.add("transactions/initiate");
	topicParams.add("transactions/success");

	// Unique identifier for a webhook.
	String webhookId = "a743___";

	// Optional API parameters

	// Status of a webhook. Possible values are "active" and "inactive".
	String status = "active";

	HashMap<String, Object> params = new HashMap<String, Object>();
	params.put("webhook_id", webhookId);
	params.put("status", status);
	params.put("topics", topicParams);

	JsonObject response = webhooksService.update( params );
	System.out.println("response: " + response.toString() );
	```

* Get Webhook using webhookId.

	```java
	// Mandatory API parameters

    // Unique identifier for a webhook.
	String webhookId = "a743___";


	HashMap<String, Object> params = new HashMap<String, Object>();
	params.put("webhook_id", webhookId);

	JsonObject response = webhooksService.get( params );
	System.out.println("response: " + response.toString() );
	```

* Get Webhook List. Pagination is supported by this API.

	```java
	// Mandatory API parameters
	// No mandatory parameters.

	// Optional API parameters

	// Limit.
	long limit = 10;

	// Pagination identifier from the previous API call response.  Not needed for page one.
	String paginationIdentifier = "eyJwY___";

	HashMap<String, Object> params = new HashMap<String, Object>();
	params.put("limit", limit);
	params.put("pagination_identifier", paginationIdentifier);

	JsonObject response = webhooksService.getList( params );
	System.out.println("response: " + response.toString() );
	```

* Delete Webhook using webhookId.

	```java
	// Mandatory API parameters

	// Unique identifier for a webhook.
	String webhookId = "a743___";

	HashMap<String, Object> params = new HashMap<String, Object>();
	params.put("webhook_id", webhookId);

	JsonObject response = webhooksService.deleteWebhook( params );
	System.out.println("response: " + response.toString() );
	```

* Verify webhook request signature. This can be used to validate if the webhook received at your end from OST platform is correctly signed.
	
	```java
	// Webhook data obtained.
	String webhookEventData = "{\"id\":\"54e3cd1c-afd7-4dcf-9c78-137c56a53582\",\"topic\":\"transactions/success\",\"created_at\":1560838772,\"webhook_id\":\"0823a4ea-5d87-44cf-8ca8-1e5a31bf8e46\",\"version\":\"v2\",\"data\":{\"result_type\":\"transaction\",\"transaction\":{\"id\":\"ddebe817-b94f-4b51-9227-f543fae4715a\",\"transaction_hash\":\"0x7ee737db22b58dc4da3f4ea4830ca709b388d84f31e77106cb79ee09fc6448f9\",\"from\":\"0x69a581096dbddf6d1e0fff7ebc1254bb7a2647c6\",\"to\":\"0xc2f0dde92f6f3a3cb13bfff43e2bd136f7dcfe47\",\"nonce\":3,\"value\":\"0\",\"gas_price\":\"1000000000\",\"gas_used\":120558,\"transaction_fee\":\"120558000000000\",\"block_confirmation\":24,\"status\":\"SUCCESS\",\"updated_timestamp\":1560838699,\"block_timestamp\":1560838698,\"block_number\":1554246,\"rule_name\":\"Pricer\",\"meta_property\":{},\"transfers\":[{\"from\":\"0xc2f0dde92f6f3a3cb13bfff43e2bd136f7dcfe47\",\"from_user_id\":\"acfdea7d-278e-4ffc-aacb-4a21398a280c\",\"to\":\"0x0a754aaab96d634337aac6556312de396a0ca46a\",\"to_user_id\":\"7bc8e0bd-6761-4604-8f8e-e33f86f81309\",\"amount\":\"112325386\",\"kind\":\"transfer\"}]}}}";

	// Get webhoook version from webhook events data.
	String version = "v2";

	// Get ost-timestamp from the response received in event.
	String requestTimestamp = "1559902637";

	// Get signature from the response received in event.
	String signature = "2c56c143550c603a6ff47054803f03ee4755c9c707986ae27f7ca1dd1c92a824";

	String webhookSecret = "mySecret";
	String stringifiedData = webhookEventData;

	Boolean response = webhooksService.verifySignature( version, stringifiedData, requestTimestamp, signature, webhookSecret );
	System.out.println("response: " + response );
	```
