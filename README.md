# OST Java SDK
[![Build Status](https://travis-ci.org/ostdotcom/ost-sdk-java.svg?branch=develop)](https://travis-ci.org/ostdotcom/ost-sdk-java)

[OST](https://dev.ost.com/) Platform SDK for Java.

## Introduction

OST is a complete technology solution enabling mainstream businesses 
to easily launch blockchain-based economies without 
requiring blockchain development.

Brand Tokens (BTs) are white-label cryptocurrency tokens with utility representations 
running on highly-scalable Ethereum-based side blockchains, 
backed by value token (such as OST, USDC) staked on Ethereum mainnet. Within a business’s 
token economy, BTs can only be transferred to whitelisted user addresses. 
This ensures that they stay within the token economy.

The OST technology stack is designed to give businesses everything they need 
to integrate, test, and deploy BTs. Within the OST suite of products, developers 
can use OST Platform to create, test, and launch Brand Tokens backed by value token (such as OST, USDC). 

OST APIs and server-side SDKs make it simple and easy for developers to 
integrate blockchain tokens into their apps.

## Requirements

Integrating an OST SDK into your application can begin as soon as you create an account 
with OST Platform, requiring only three steps:
1. Sign-up on [https://platform.ost.com](https://platform.ost.com).
2. Create your Brand Token in OST Platform.
3. Obtain an API Key and API Secret from [https://platform.ost.com/mainnet/developer](https://platform.ost.com/mainnet/developer).

## Documentation

[https://dev.ost.com/](https://dev.ost.com/)

## Installation

### Maven users
#### Add this dependency to your project's POM:
```xml
<dependency>
  <groupId>com.ost</groupId>
  <artifactId>ost-sdk-java</artifactId>
  <version>2.2.0</version>
</dependency>
```

### Building from source using Maven

Clone the repository:
```bash
git clone https://github.com/ostdotcom/ost-sdk-java.git
cd ost-sdk-java
```

Package using MVN (without dependencies):
```bash
mvn clean pacakge -DskipTests
```

With dependencies:
```bash
mvn clean compile assembly:single -DskipTests
```

The jar file can be found in the target folder.

## Getting Started

Initialize the SDK object:

```java
// the latest valid API endpoint is "https://api.ost.com/mainnet/v2/", this may change in the future
HashMap <String,Object> sdkConfig = new HashMap<String,Object>();
sdkConfig.put("apiEndpoint","[YOUR_API_ENDPOINT]");
sdkConfig.put("apiKey","[YOUR_API_KEY]");
sdkConfig.put("apiSecret","[YOUR_API_SECRET]");

// The config field is optional for sdkConfig Object
HashMap <String,Object> nestedparam = new HashMap<String,Object>();
// This is the timeout in seconds for which the socket connection will remain open
// The value of timeout will always be of type long
nestedparam.put("timeout", (long) 60);
sdkConfig.put("config", nestedparam);

OSTSDK ostObj = new OSTSDK(sdkConfig);
com.ost.services.Manifest services = (com.ost.services.Manifest) ostObj.services;
```

## SDK Modules

If a user's private key is lost, they could lose access 
to their tokens. To tackle this risk, OST promotes a 
mobile-first approach and provides mobile (client) and server SDKs. 


* The server SDKs enable you to register users with OST Platform.
* The client SDKs provide the additional support required for 
the ownership and management of Brand Tokens by users so 
that they can create keys and control their tokens. 

### Users Module

To register users with OST Platform, you can use the services provided in the Users module. 

Initialize a Users object to perform user-specific actions, like creating users:

```java
com.ost.services.Users usersService = services.users;
```

Create a User with OST Platform:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
JsonObject response = usersService.create( params );
System.out.println("response: " + response.toString() );
```

Get User Detail:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
JsonObject response = usersService.get( params );
System.out.println("response: " + response.toString() );
```

Get Users List:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
//ArrayList<Object> idsArray = new ArrayList<Object>();
//idsArray.add("29f57b59-60af-4579-9d6c-2ebcb36a9142");
//idsArray.add("12f57b59-60af-4579-9d6c-2ebcb36a9123");
//params.put("ids", idsArray);
//params.put("limit", 10);
JsonObject response = usersService.getList( params );
System.out.println("response: " + response.toString() );
```

### Devices Module

Once a user is created via the API, you can register the 
user’s device with OST Platform. Next, activate the user’s 
wallet on the user's device. Multiple devices can be 
registered per user. 


Initialize a Devices object to perform device-specific actions, 
like registering devices:

```java
com.ost.services.Devices devicesService = services.devices;
```

Create a Device for User:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
params.put("address", "0x1Ea365269A3e6c8fa492eca9A531BFaC8bA1649E");
params.put("api_signer_address", "0x5F860598383868e8E8Ee0ffC5ADD92369Db37455");
JsonObject response = devicesService.create( params );
System.out.println("response: " + response.toString() );
```

Get User Device Detail:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
params.put("device_address", "0x1Ea365269A3e6c8fa492eca9A531BFaC8bA1649E");
JsonObject response = devicesService.get( params );
System.out.println("response: " + response.toString() );
```

Get User Devices List:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
//params.put("pagination_identifier", "eyJsYXN0RXZhbHVhdGVkS2V5Ijp7InVpZCI6eyJTIjoiZDE5NGFhNzUtYWNkNS00ZjQwLWIzZmItZTczYTdjZjdjMGQ5In0sIndhIjp7IlMiOiIweDU4YjQxMDY0NzQ4OWI4ODYzNTliNThmZTIyMjYwZWIxOTYwN2IwZjYifX19");
//ArrayList<Object> addressesArray = new ArrayList<Object>();
//addressesArray.add("0x5906ae461eb6283cf15b0257d3206e74d83a6bd4");
//addressesArray.add("0xab248ef66ee49f80e75266595aa160c8c1abdd5a");
//params.put("addresses", addressesArray);
//params.put("limit", 10);
JsonObject response = devicesService.getList( params );
System.out.println("response: " + response.toString() );
```

### Device Managers Module

After a user is created and their device is registered via the API, 
their wallet can be activated. Activating a wallet involves the deployment of the following contracts:

* TokenHolder - each user in the economy is represented by a TokenHolder that holds the user's token balance.
* Device Manager (multi-signature) - this contract is configured to control the user's TokenHolder contract.
* DelayedRecoveryModule - this contract enables recovery in the event a key is lost.

In order to enable a user to access their tokens, i.e., interact 
with their TokenHolder contract, from multiple devices without 
having to share private keys across devices, a multi-signature 
contract is employed. We refer to this entity as the Device 
Manager in OST APIs.

To get information about a user’s Device Manager, use services provided in the Device Managers module.

```java
com.ost.services.DeviceManagers deviceManagersService = services.deviceManagers;
```

Get Device Manager Detail:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
JsonObject response = deviceManagersService.get( params );
System.out.println("response: " + response.toString() );
```

### Sessions Module

In order to create a more seamless user experience, so that users don't have to 
sign a new transaction at every move in the application, we use session keys. 
These keys are authorized to sign transactions on the user's behalf 
for a predetermined amount of time and with a defined maximum spending 
limit per transaction.

These session keys are created in a user's wallet. A user’s TokenHolder 
contract can have multiple authorized session keys.

To get information about a user’s session keys, use services provided in the Sessions module.

```java
com.ost.services.Sessions sessionsService = services.sessions;
```

Get User Session Detail:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
params.put("session_address", "0x1Ea365269A3e6c8fa492eca9A531BFaC8bA1649E");
JsonObject response = sessionsService.get( params );
System.out.println("response: " + response.toString() );
```

Get User Sessions List:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
//params.put("pagination_identifier", "eyJsYXN0RXZhbHVhdGVkS2V5Ijp7InVpZCI6eyJTIjoiZDE5NGFhNzUtYWNkNS00ZjQwLWIzZmItZTczYTdjZjdjMGQ5In0sIndhIjp7IlMiOiIweDU4YjQxMDY0NzQ4OWI4ODYzNTliNThmZTIyMjYwZWIxOTYwN2IwZjYifX19");
//ArrayList<Object> addressesArray = new ArrayList<Object>();
//addressesArray.add("0x5906ae461eb6283cf15b0257d3206e74d83a6bd4");
//addressesArray.add("0xab248ef66ee49f80e75266595aa160c8c1abdd5a");
//params.put("addresses", addressesArray);
//params.put("limit", 10);
JsonObject response = sessionsService.getList( params );
System.out.println("response: " + response.toString() );
```

### Executing Transactions

For executing transactions, you need to understand the 4 modules described below.

#### Rules Module

When executing a token transfer, a user's TokenHolder contract 
interacts with a token rule contract. A token economy can have 
multiple token rule contracts. To enable a user to execute a 
token transfer, you need to start with fetching details of 
registered rule contracts and understanding their methods and the 
corresponding parameters passed in those methods.

To get information about deployed rule contracts, use services provided in the Rules module.

```java
com.ost.services.Rules rulesService = services.rules;
```

List Rules:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
JsonObject response = rulesService.getList( params );
System.out.println("response: " + response.toString() );
```

#### Price Points Module

To know the value token (such as OST, USDC) price point in pay currency and when it was last updated, 
use services provided by the Price Points module.

```java
com.ost.services.PricePoints pricePointsService = services.pricePoints;
```

Get Price Points Detail:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("chain_id", "200");
JsonObject response = pricePointsService.get( params );
System.out.println("response: " + response.toString() );
```

#### Transactions Module

After reviewing the rules information received using services in the Rules 
module, you will know what data is required to execute transfers 
with a token rule using the services provided in the Transaction module.

```java
com.ost.services.Transactions transactionsService = services.transactions;
```

Get a transaction info for existing user:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
params.put("transaction_id", "e96450b8-f46a-40ee-adf1-9d65a4b53241");
JsonObject response = transactionsService.get( params );
System.out.println("response: " + response.toString() );
```

Get all transactions info for a user:

```java
//ArrayList<HashMap<String, Object>> metaPropertyArray = new ArrayList<HashMap<String, Object>>();
//HashMap <String,Object> metaPropertyArrayParams = new HashMap<String,Object>();
//metaPropertyArrayParams.put("name", "transaction_name"); //like, download IMP : Max length 25 characters (numbers alphabets spaces _ - allowed)
//metaPropertyArrayParams.put("type", "user_to_user"); // user_to_user, company_to_user, user_to_company
//metaPropertyArrayParams.put("details", "test"); // memo field to add additional info about the transaction .  IMP : Max length 120 characters (numbers alphabets spaces _ - allowed)
//metaPropertyArray.add(metaPropertyArrayParams);
//Gson gsonObj = new Gson();
//String metaPropertyArrayJsonStr = gsonObj.toJson(metaPropertyArray);

//ArrayList<Object> statusArray = new ArrayList<Object>();
//statusArray.add("CREATED");
//statusArray.add("SUBMITTED");
//statusArray.add("SUCCESS");
//statusArray.add("FAILED");

HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
//params.put("statuses", statusArray);
//params.put("meta_properties", metaPropertyArrayJsonStr);
//params.put("limit", 10);
JsonObject response = transactionsService.getList( params );
System.out.println("response: " + response.toString() );
```

Execute Transaction DIRECT-TRANSFERS:

```java
//HashMap <String,Object> metaProperty = new HashMap<String,Object>();
//metaProperty.put("name", "transaction_name"); // like, download
//metaProperty.put("type", "user_to_user"); // user_to_user, company_to_user, user_to_company
//metaProperty.put("details", "test"); // memo field to add additional info about the transaction

HashMap <String,Object> params = new HashMap<String,Object>();
HashMap <String,Object> nestedparams = new HashMap<String,Object>();
String userId = "29f57b59-60af-4579-9d6c-2ebcb36a9142";
String toAddress = "0xe37906219ad67cc1301b970539c9860f9ce8d991";
String user2TokenHolderAddress = "0xa31e988eebc89d0bc3e4a9a5463545ea534593e4";
String amount = "1";
params.put("user_id", userId);
params.put("to", toAddress);
nestedparams.put("method", "directTransfers");
ArrayList<ArrayList> nestedarraylist = new ArrayList<ArrayList>();
ArrayList<Object> arrayListForUser2TokenHolderAddress = new ArrayList<Object>();
arrayListForUser2TokenHolderAddress.add(user2TokenHolderAddress);
ArrayList<Object> arrayListAmount = new ArrayList<Object>();
arrayListAmount.add(amount);
Gson gsonObj = new Gson();
nestedarraylist.add(arrayListForUser2TokenHolderAddress);
nestedarraylist.add(arrayListAmount);
nestedparams.put("parameters", nestedarraylist);
String jsonStr = gsonObj.toJson(nestedparams);
params.put("raw_calldata", jsonStr);
//params.put("meta_property", metaProperty);
JsonObject response = transactionsService.execute( params );
System.out.println("response: " + response.toString() );
```

Execute Transaction PAY:

```java
//HashMap <String,Object> metaProperty = new HashMap<String,Object>();
//metaProperty.put("name", "transaction_name"); // like, download
//metaProperty.put("type", "user_to_user"); // user_to_user, company_to_user, user_to_company
//metaProperty.put("details", "test"); // memo field to add additional info about the transaction

HashMap <String,Object> params = new HashMap<String,Object>();
HashMap <String,Object> nestedparams = new HashMap<String,Object>();
String userId = "29f57b59-60af-4579-9d6c-2ebcb36a9142";
String toAddress = "0xe37906219ad67cc1301b970539c9860f9ce8d991";
String user2TokenHolderAddress = "0xa31e988eebc89d0bc3e4a9a5463545ea534593e4";
String amount = "1";
params.put("user_id", userId);
params.put("to", toAddress);
nestedparams.put("method", "pay");
ArrayList<Object> nestedarraylist = new ArrayList<Object>();
ArrayList<Object> arrayListForUser2TokenHolderAddress = new ArrayList<Object>();
arrayListForUser2TokenHolderAddress.add(user2TokenHolderAddress);
ArrayList<Object> arrayListAmount = new ArrayList<Object>();
arrayListAmount.add(amount);
Gson gsonObj = new Gson();
String tokenHolderSender = "0xa9632350057c2226c5a10418b1c3bc9acdf7e2ee";
String payCurrencyCode = "USD";
String intendedPricePoint = "23757000000000000";
nestedarraylist.add(tokenHolderSender);
nestedarraylist.add(arrayListForUser2TokenHolderAddress);
nestedarraylist.add(arrayListAmount);
nestedarraylist.add(payCurrencyCode);
nestedarraylist.add(intendedPricePoint);
nestedparams.put("parameters", nestedarraylist);
String jsonStr = gsonObj.toJson(nestedparams);
params.put("raw_calldata", jsonStr);
//params.put("meta_property", metaProperty);
JsonObject response = transactionsService.execute( params );
System.out.println("response: " + response.toString() );
```

Get Transaction Detail:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
params.put("transaction_id", "e96450b8-f46a-40ee-adf1-9d65a4b53241");
JsonObject response = transactionsService.get( params );
System.out.println("response: " + response.toString() );
```

Get User Transactions:

```java
//ArrayList<HashMap<String, Object>> metaPropertyArray = new ArrayList<HashMap<String, Object>>();
//HashMap <String,Object> metaPropertyArrayParams = new HashMap<String,Object>();
//metaPropertyArrayParams.put("name", "transaction_name"); //like, download IMP : Max length 25 characters (numbers alphabets spaces _ - allowed)
//metaPropertyArrayParams.put("type", "user_to_user"); // user_to_user, company_to_user, user_to_company
//metaPropertyArrayParams.put("details", "test"); // memo field to add additional info about the transaction .  IMP : Max length 120 characters (numbers alphabets spaces _ - allowed)
//metaPropertyArray.add(metaPropertyArrayParams);
//Gson gsonObj = new Gson();
//String metaPropertyArrayJsonStr = gsonObj.toJson(metaPropertyArray);

//ArrayList<Object> statusArray = new ArrayList<Object>();
//statusArray.add("CREATED");
//statusArray.add("SUBMITTED");
//statusArray.add("SUCCESS");
//statusArray.add("FAILED");

HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
//params.put("status", statusArray);
//params.put("meta_property", metaPropertyArrayJsonStr);
//params.put("limit", 10);
JsonObject response = transactionsService.getList( params );
System.out.println("response: " + response.toString() );
```

#### Balances Module

Balance services offer the functionality to view a user’s balances.

```java
com.ost.services.Balance balancesService = services.balance;
```

Get User Balance:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
JsonObject response = balancesService.get( params );
System.out.println("response: " + response.toString() );
```

### Recovery Owners Module
A user’s Brand Tokens are held by a TokenHolder contract that is controlled ("owned") 
by a Device Manager; the device manager is controlled ("owned") by device keys created 
and held by the user in their wallets; and if any of those keys is lost, the Device 
Manager (which is a multi-signature contract) is programmed to allow replacement of a 
key by the recovery owner key for the user, via the DelayedRecoveryModule, which is deployed
at the time of the creation of the user's initial wallet.

To fetch the recovery owner address for a particular user, use services provided 
in the Users module. To fetch that recovery owner's information, then services 
provided in the Recovery Owners Module are used.

```java
com.ost.services.RecoveryOwners recoveryOwnersService = services.recoveryOwners;
```

Get Recovery Owner Detail:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
params.put("recovery_owner_address", "0x1Ea365269A3e6c8fa492eca9A531BFaC8bA1649E");
JsonObject response = recoveryOwnersService.get( params );
System.out.println("response: " + response.toString() );
```

### Tokens Module

To get information about the Brand Token created on the OST Platform interface, use services provided 
by the Tokens module. You can use this service to obtain the chain ID of the auxiliary 
chain on which the token economy is running, in addition to other information.

```java
com.ost.services.Tokens tokensService = services.tokens;
```

Get Token Detail:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
JsonObject response = tokensService.get( params );
System.out.println("response: " + response.toString() );
```

### Chains Module

To get information about the auxiliary chain on which the token economy is running, use services 
provided by the Chains module.

```java
com.ost.services.Chains chainsService = services.chains;
```

Get Chain Detail:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("chain_id", "200");
JsonObject response = chainsService.get( params );
System.out.println("response: " + response.toString() );
```

### Base Tokens Module

To get information about the value tokens (such as OST, USDC) available on the OST Platform interface, use services
provided by the Base Tokens module. You can use this service to obtain the base token details
on OST Platform interface.

```java
com.ost.services.BaseTokens baseTokensService = services.baseTokens;
```

Get Base Token Detail:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
JsonObject response = baseTokensService.get( params );
System.out.println("response: " + response.toString() );
```

### Webhooks Module

To manage webhooks on the OST Platform Interface, use services provided by the 
Chains module. You can use this service to create new webhooks and manage existing 
webhooks.

```java
com.ost.services.Webhooks webhooksService = services.webhooks;
```

Create Webhook:

```java
HashMap<String, Object> params = new HashMap<String, Object>();
ArrayList<String> arrayListofTopics = new ArrayList<String>();
arrayListofTopics.add("devices/authorization_initiate");
params.put("url", "https://www.yourdomain12345.com");
params.put("status", "active");
params.put("topics", arrayListofTopics);
JsonObject response = webhooksService.create( params );
System.out.println("response: " + response.toString() );
```

Update Webhook:

```java
HashMap<String, Object> params = new HashMap<String, Object>();
ArrayList<String> arrayListofTopics = new ArrayList<String>();
arrayListofTopics.add("devices/authorization_initiate");
params.put("webhook_id", "4107e308-0146-4c6f-b2f3-617e2c0d2354");
params.put("status", "active");
params.put("topics", arrayListofTopics);
JsonObject response = webhooksService.update( params );
System.out.println("response: " + response.toString() );
```

Get Webhook:

```java
HashMap<String, Object> params = new HashMap<String, Object>();
params.put("webhook_id", "4107e308-0146-4c6f-b2f3-617e2c0d2354");
JsonObject response = webhooksService.get( params );
System.out.println("response: " + response.toString() );
```

Get Webhook List:

```java
HashMap<String, Object> params = new HashMap<String, Object>();
//params.put("limit", 10);
//params.put("pagination_identifier", "eyJsYXN0RXZhbHVhdGVkS2V5Ijp7InVpZCI6eyJTIjoiZDE5NGFhNzUtYWNkNS00ZjQwLWIzZmItZTczYTdjZjdjMGQ5In0sIndhIjp7IlMiOiIweDU4YjQxMDY0NzQ4OWI4ODYzNTliNThmZTIyMjYwZWIxOTYwN2IwZjYifX19");
JsonObject response = webhooksService.getList( params );
System.out.println("response: " + response.toString() );
```

Delete Webhook:

```java
HashMap<String, Object> params = new HashMap<String, Object>();
params.put("webhook_id", "4107e308-0146-4c6f-b2f3-617e2c0d2354");
JsonObject response = webhooksService.deleteWebhook( params );
System.out.println("response: " + response.toString() );
```

Verify webhook request signature:

```java
String webhookEventData = '{"id":"54e3cd1c-afd7-4dcf-9c78-137c56a53582","topic":"transactions/success","created_at":1560838772,"webhook_id":"0823a4ea-5d87-44cf-8ca8-1e5a31bf8e46","version":"v2","data":{"result_type":"transaction","transaction":{"id":"ddebe817-b94f-4b51-9227-f543fae4715a","transaction_hash":"0x7ee737db22b58dc4da3f4ea4830ca709b388d84f31e77106cb79ee09fc6448f9","from":"0x69a581096dbddf6d1e0fff7ebc1254bb7a2647c6","to":"0xc2f0dde92f6f3a3cb13bfff43e2bd136f7dcfe47","nonce":3,"value":"0","gas_price":"1000000000","gas_used":120558,"transaction_fee":"120558000000000","block_confirmation":24,"status":"SUCCESS","updated_timestamp":1560838699,"block_timestamp":1560838698,"block_number":1554246,"rule_name":"Pricer","meta_property":{},"transfers":[{"from":"0xc2f0dde92f6f3a3cb13bfff43e2bd136f7dcfe47","from_user_id":"acfdea7d-278e-4ffc-aacb-4a21398a280c","to":"0x0a754aaab96d634337aac6556312de396a0ca46a","to_user_id":"7bc8e0bd-6761-4604-8f8e-e33f86f81309","amount":"112325386","kind":"transfer"}]}}}' // webhook response should be here

// Get webhoook version from webhook events data.
String version = "v2";

// Get ost-timestamp from the response received in event.
String requestTimestamp = "1559902637";

// Get signature from the response received in event.
String signature = "e9206f9feecccd8f9653a4bdb56ea74531e6528bae8f6de1797aa77dc5235923";

String webhookSecret = "09121ae7614856777fa36d63aca828e0ef14be77fb48fa149e0c0b50fec847a7";
String stringifiedData = webhookEventData;
Boolean response = webhooksService.verifySignature( version, stringifiedData, requestTimestamp, signature, webhookSecret );
System.out.println("response: " + response );
```