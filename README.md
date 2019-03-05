# OST Java SDK
[![Build Status](https://travis-ci.org/ostdotcom/ost-sdk-java.svg?branch=master)](https://travis-ci.org/ostdotcom/ost-sdk-java)

The official [OST Java SDK](https://dev.ost.com/).

## Requirements

To use this node module, developers will need to:
1. Sign-up on [https://kit.ost.com](https://kit.ost.com).
2. Launch a branded token economy with the OST KIT Economy Planner.
3. Obtain an API Key and API Secret from [https://kit.ost.com/developer-api-console](https://kit.ost.com/developer-api-console).

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

### Chains Module

```java
com.ost.services.Chains chainsService = services.chains;
```

Get Chains Information:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("chain_id", "2000");
JsonObject response = chainsService.get( params );
System.out.println("response: " + response.toString() );
```

### Price Points Module

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

### Tokens Module

```java
com.ost.services.Tokens tokensService = services.tokens;
```

Get Token Detail:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
JsonObject response = tokensService.get( params );
System.out.println("response: " + response.toString() );
```

### Rules Module

```java
com.ost.services.Rules rulesService = services.rules;
```

List all Rules:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
JsonObject response = rulesService.getList( params );
System.out.println("response: " + response.toString() );
```

### Users Module 

```java
com.ost.services.Users userService = services.users;
```

Create a new user:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
JsonObject response = userService.create( params );
System.out.println("response: " + response.toString() );
```

Get an existing user:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
JsonObject response = userService.get( params );
System.out.println("response: " + response.toString() );
```

Get a list of users and other data:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
//params.put("limit", "10");
JsonObject response = userService.getList( params );
System.out.println("response: " + response.toString() );
```

### Devices Module

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
//params.put("limit", "10");
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

### DeviceManagers Module

```java
com.ost.services.DeviceManagers deviceManagersService = services.deviceManagers;
```

Get Device Managers detail:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
JsonObject response = deviceManagersService.get( params );
System.out.println("response: " + response.toString() );
```

### Sessions Module

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
//params.put("limit", "10");
JsonObject response = sessionsService.getList( params );
System.out.println("response: " + response.toString() );
```

### RecoveryOwners Module

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

### Balance Module

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

### Transactions Module

```java
com.ost.services.Transactions transactionsService = services.transactions;
```

Get a transaction info:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("user_id", "29f57b59-60af-4579-9d6c-2ebcb36a9142");
params.put("transaction_id", "e96450b8-f46a-40ee-adf1-9d65a4b53241");
JsonObject response = transactionsService.get( params );
System.out.println("response: " + response.toString() );
```

Get all transactions for a user:

```java
//ArrayList<HashMap<String, Object>> metaPropertyArray = new ArrayList<HashMap<String, Object>>();
//HashMap <String,Object> metaPropertyArrayParams = new HashMap<String,Object>();
//metaPropertyArrayParams.put("name", "transaction_name"); //like, download IMP : Max length 25 characters (numbers alphabets spaces _ - allowed)
//metaPropertyArrayParams.put("type", "user_to_user"); // user_to_user, company_to_user, user_to_company
//metaPropertyArrayParams.put("details", ""); // memo field to add additional info about the transaction .  IMP : Max length 120 characters (numbers alphabets spaces _ - allowed)
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
params.put("transaction_id", "e96450b8-f46a-40ee-adf1-9d65a4b53241");
//params.put("status", statusArray);
//params.put("meta_property", metaPropertyArrayJsonStr);
//params.put("limit", "10");
JsonObject response = transactionsService.getList( params );
System.out.println("response: " + response.toString() );
```

execute transaction from company:

```java
//HashMap <String,Object> metaProperty = new HashMap<String,Object>();
//metaProperty.put("name", "transaction_name"); // like, download
//metaProperty.put("type", "user_to_user"); // user_to_user, company_to_user, user_to_company
//metaProperty.put("details", ""); // memo field to add additional info about the transaction

HashMap <String,Object> params = new HashMap<String,Object>();
HashMap <String,Object> nestedparams = new HashMap<String,Object>();
String userId = "29f57b59-60af-4579-9d6c-2ebcb36a9142";
String toAddress = "0xe37906219ad67cc1301b970539c9860f9ce8d991";
String parameter1 = "0xa31e988eebc89d0bc3e4a9a5463545ea534593e4";
String parameter2 = "1";
params.put("user_id", userId);
params.put("to", toAddress);
nestedparams.put("method", "directTransfers");
ArrayList<ArrayList> nestedarraylist = new ArrayList<ArrayList>();
ArrayList<Object> arrayList1 = new ArrayList<Object>();
arrayList1.add(parameter1);
ArrayList<Object> arrayList2 = new ArrayList<Object>();
Gson gsonObj = new Gson();
arrayList2.add(parameter2);
nestedarraylist.add(arrayList1);
nestedarraylist.add(arrayList2);
nestedparams.put("parameters", nestedarraylist);
String jsonStr = gsonObj.toJson(nestedparams);
params.put("raw_calldata", jsonStr);
//params.put("meta_property", metaProperty);
JsonObject response = transactionsService.execute( params );
System.out.println("response: " + response.toString() );
```