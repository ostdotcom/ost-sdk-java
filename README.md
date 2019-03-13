# OST Java SDK
[![Build Status](https://travis-ci.org/ostdotcom/ost-sdk-java.svg?branch=master)](https://travis-ci.org/ostdotcom/ost-sdk-java)

The official [OST Java SDK](https://dev.ost.com/).

## Introduction

OST is a complete technology solution enabling mainstream businesses 
to easily launch blockchain-based economies at low risk and without 
requiring blockchain development.

At the core of OST is the concept of OST-powered Brand Tokens (BTs). 
BTs are white-label cryptocurrency tokens running on highly scalable 
Ethereum-based side blockchains, backed by staking OST Tokens into smart 
contracts on Ethereum mainnet. BTs can only be transferred to whitelisted 
user addresses within a business’s community. This ensures that they stay 
within a specific BT community.

The OST technology stack is designed to give businesses everything they need 
to integrate, test, and deploy BTs.Within the OST suite of products developers 
can use OST KIT to create, test and launch Branded Tokens backed by OST. 

OST APIs and Server Side SDKs make it simple and easy for developers to 
integrate blockchain tokens into their apps.

## Advantages

Using the OST SDKs provides a number of advantages
* Simplicity: The SDKs reduce the complexity of integration by handling multiple authentication scenarios automatically. This means that the appropriate method call will be to minimize the end-user interactions.
* Performance: Caching, key management and nonce management ensure that end-users overall experience is improved.
* Security: Separating the Server Side API interactions from the client ensures that user's private keys are generated and stored securely on the user's device and not shared across the network.

## Requirements

Integrating OST SDK into your application can begin as soon as you create an account, 
with OST KIT requiring only three steps:
1. Sign-up on [https://kit.ost.com](https://kit.ost.com).
2. Create your branded token in OST KIT.
3. Obtain an API Key and API Secret from [https://kit.ost.com/mainnet/developer](https://kit.ost.com/mainnet/developer).

## Documentation

[https://dev.ost.com/](https://dev.ost.com/)

## Installation

### Maven users
#### Add this dependency to your project's POM:
```xml
<dependency>
  <groupId>com.ost</groupId>
  <artifactId>ost-sdk-java</artifactId>
  <version>2.0.0</version>
</dependency>
```

### Building from source using Maven

Clone the repository
```bash
git clone https://github.com/ostdotcom/ost-sdk-java.git
cd ost-sdk-java
```

Package using MVN (without dependencies)
```bash
mvn clean pacakge -DskipTests
```

With dependencies
```bash
mvn clean compile assembly:single -DskipTests
```

The jar file can be found in the target folder.

## Example Usage

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
nestedparam.put("timeout", (long) 15);
sdkConfig.put("config", nestedparam);

OSTSDK ostObj = new OSTSDK(sdkConfig);
com.ost.services.Manifest services = (com.ost.services.Manifest) ostObj.services;
```

### Users Module

Given that Brand Tokens are valuable to users,  if their private 
keys are compromised it could result in the user being unable to 
access their tokens. To tackle this OST promotes a mobile-first 
approach and provides mobile(client) and server SDKs. 


* The server SDKs enable you to register users with KIT server.
* The client SDKs provides the additional support required for 
the ownership and management of Brand Tokens by end users so 
that they can create keys and control their tokens. 

To register user with KIT you can use the services provided in user module. 

Initialize a Users object to perform user specific actions, like creating users:

```java
com.ost.services.Users userService = services.users;
```

Creating a user with KIT:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
JsonObject response = userService.create( params );
System.out.println("response: " + response.toString() );
```

Get an existing user:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
JsonObject response = userService.get( params );
System.out.println("response: " + response.toString() );
```

Get a list of users and other data:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
//ArrayList<Object> idsArray = new ArrayList<Object>();
//idsArray.add("29f57b59-60af-4579-9d6c-2ebcb36a9142");
//idsArray.add("12f57b59-60af-4579-9d6c-2ebcb36a9123");
//params.put("ids", idsArray);
//params.put("limit", 10);
JsonObject response = userService.getList( params );
System.out.println("response: " + response.toString() );
```

### Devices Module

Once a user is created via API, partner company should register 
user’s device with KIT.  Post which they can activate user’s 
wallet on their device. Partner company can register multiple 
devices per user. 
 

Initialize a Device object to perform device specific actions, 
like registering devices:

```java
com.ost.services.Devices devicesService = services.devices;
```

Create a device for User:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
params.put("address", "0x1Ea365269A3e6c8fa492eca9A531BFaC8bA1649E");
params.put("api_signer_address", "0x5F860598383868e8E8Ee0ffC5ADD92369Db37455");
params.put("device_uuid", "593a967f-87bd-49a6-976c-52edf46c4df4");
params.put("device_name", "Iphone S");
JsonObject response = devicesService.create( params );
System.out.println("response: " + response.toString() );
```

Get User Device(s) List:

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
```

Get User Device:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
params.put("device_address", "0x1Ea365269A3e6c8fa492eca9A531BFaC8bA1649E");
JsonObject response = devicesService.get( params );
System.out.println("response: " + response.toString() );
```

### Device Managers Module

After  user is created and their device is registered via API,  
a wallet can be activated. Activating a wallet involves the deployment of :

* TokenHolder contract - each user in the economy is be represented by this smart contract on blockchain.  It holde the user's balances.
* Device Manager (Multisig contract) - This contract is configured to control the user's TokenHolder contract.
* DelayedRecoveryModule contract - that supports recovery via a 6 digit PIN. 

In order to enable user to access their tokens i.e their TokenHolder contract 
from multiple devices without having to share private keys across devices we 
came up with a multi-signature contract. We refer to this entity as device 
manager in OST APIs.

To get information about user’s device manager use services provided in device manager module.

```java
com.ost.services.DeviceManagers deviceManagersService = services.deviceManagers;
```

Get Device Managers detail of an existing user:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
JsonObject response = deviceManagersService.get( params );
System.out.println("response: " + response.toString() );
```

### Sessions Module

In order to create a seamless user experience so that users don't have to 
sign a new transaction at every move in the application we came up with 
the concept of sessionKeys. These keys are used to sign messages on user's 
behalf for a predetermined amount of time and with a defined maximum spending 
limit per-transaction.

These keys are created on the mobile device from where the end user participates 
in the economy. Each key has a corresponding public key, which in-turn has a 
corresponding public address. User’s TokenHolder contract can have multiple 
sessionKeys for signing messages on user’s behalf.

To get information about user’s session keys use services provided in session module.

```java
com.ost.services.Sessions sessionsService = services.sessions;
```

Get User Session:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
params.put("session_address", "0x1Ea365269A3e6c8fa492eca9A531BFaC8bA1649E");
JsonObject response = sessionsService.get( params );
System.out.println("response: " + response.toString() );
```

Get User Session(s) List:

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

## For executing transactions we need to understand 3 modules listed below
To enable partner companies to design Rules that align with their economy goals 
and define the behavior of the token transfer, they have the flexibility to 
write their custom rules contract. OST has written one rule contract the 
[PricerRule Contract](https://github.com/OpenSTFoundation/openst-contracts/blob/develop/contracts/rules/PricerRule.sol) 
for partner companies to use.

For executing a token transfer, end-user's TokenHolder contract interacts with Rules Contract.

### Rules Module

So for executing a token transfer, partner company need to start with fetching details of 
deployed rules contract and understand the methods written within them and the corresponding 
parameters passed in those methods.

To get information about rules contracts deployed use services provided in rule module.

```java
com.ost.services.Rules rulesService = services.rules;
```

List all Rules:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
JsonObject response = rulesService.getList( params );
System.out.println("response: " + response.toString() );
```

### Transactions Module

Once you’ve fetched the rule method and rule parameters to be passed using services 
in rule module you can start with executing the transfer using the services provided 
in transaction module.

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
//params.put("status", statusArray);
//params.put("meta_property", metaPropertyArrayJsonStr);
//params.put("limit", 10);
JsonObject response = transactionsService.getList( params );
System.out.println("response: " + response.toString() );
```

Execute Transaction DIRECT-TRANSFERS:

```java
//HashMap <String,Object> metaProperty = new HashMap<String,Object>();
//metaProperty.put("name", "transaction_name"); // like, download
//metaProperty.put("type", "user_to_user"); // user_to_user, company_to_user, user_to_company
//metaProperty.put("details", ""); // memo field to add additional info about the transaction

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
//metaProperty.put("details", ""); // memo field to add additional info about the transaction

HashMap <String,Object> params = new HashMap<String,Object>();
HashMap <String,Object> nestedparams = new HashMap<String,Object>();
String userId = "29f57b59-60af-4579-9d6c-2ebcb36a9142";
String toAddress = "0xe37906219ad67cc1301b970539c9860f9ce8d991";
String user2TokenHolderAddress = "0xa31e988eebc89d0bc3e4a9a5463545ea534593e4";
String amount = "1";
params.put("user_id", userId);
params.put("to", toAddress);
nestedparams.put("method", "pay");
ArrayList<ArrayList> nestedarraylist = new ArrayList<ArrayList>();
ArrayList<Object> arrayListForUser2TokenHolderAddress = new ArrayList<Object>();
arrayListForUser2TokenHolderAddress.add(user2TokenHolderAddress);
ArrayList<Object> arrayListAmount = new ArrayList<Object>();
arrayListAmount.add(amount);
Gson gsonObj = new Gson();
String tokenHolderSender = "0xa9632350057c2226c5a10418b1c3bc9acdf7e2ee";
String payCurrencyCode = "USD";
String ostToUsdInWei = "23757000000000000";
nestedarraylist.add(tokenHolderSender);
nestedarraylist.add(arrayListForUser2TokenHolderAddress);
nestedarraylist.add(arrayListAmount);
nestedarraylist.add(payCurrencyCode);
nestedarraylist.add(ostToUsdInWei);
nestedparams.put("parameters", nestedarraylist);
String jsonStr = gsonObj.toJson(nestedparams);
params.put("raw_calldata", jsonStr);
//params.put("meta_property", metaProperty);
JsonObject response = transactionsService.execute( params );
System.out.println("response: " + response.toString() );
```

### Balance Module

Balance services offer the functionality to view user’s balances.

```java
com.ost.services.Balance balanceService = services.balance;
```

Get User's Balance:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
JsonObject response = balanceService.get( params );
System.out.println("response: " + response.toString() );
```

### RecoveryOwners Module
End user’s brand tokens are held by a token holder contract that is controlled ("owned") 
by device manager; the device manager  is controlled ("owned") by device keys created 
and held by the user in their wallets; and if those keys are compromised, the device 
manager (which is a multi-signature contract) is programmed to allow replacement of a 
key by a recovery key.

The DelayedRecoveryModule is deployed at the time of the creation of the wallet. The 
recoveryOwner public-private key pair is created using inputs from the Partner, OST 
and the user. The public addresse of the recoveryOwner is stored on this DelayedRecoveryModule 
contract.

Recovering access to tokens after the owner key becomes compromised

User requests recovery from partner company application by entering their 6-digit 
recovery PIN. Once the request has been raised user waits for defined delay which 
is 12 hours currently.

OST wallet SDK Create the recoveryOwner private key using the inputs from the Partner, 
OST and the user.  This should exactly match the recoveryOwner that was made when the 
wallet was initially created. 

To fetch the recoveryOwner address for a particular user services provided in Recovery 
Owner Module are used.

```java
com.ost.services.RecoveryOwners recoveryOwnersService = services.recoveryOwners;
```

Get recovery owner:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
params.put("recovery_owner_address", "0x1Ea365269A3e6c8fa492eca9A531BFaC8bA1649E");
JsonObject response = recoveryOwnersService.get( params );
System.out.println("response: " + response.toString() );
```

### Tokens Module

To get information about the token created on the OST KIT interface use services provided 
by token module. Partner company can use this service to know the chain id , the auxiliary 
chain on which their economy is running apart from other information.

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

To get information about the auxiliary chain on which the token economy is running use services 
provided by chain module.

```java
com.ost.services.Chains chainsService = services.chains;
```

Get Chains Information:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("chain_id", "200");
JsonObject response = chainsService.get( params );
System.out.println("response: " + response.toString() );
```

### Price Points Module

To know the OST price point in USD and the last timestamp when it was updateds 
use services provided by Price Point module.

```java
com.ost.services.PricePoints pricePointsService = services.pricePoints;
```

Get Price Points:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("chain_id", "200");
JsonObject response = pricePointsService.get( params );
System.out.println("response: " + response.toString() );
```
