# OST Java SDK
The official [OST Java SDK](https://dev.ost.com/).


[![Travis](https://travis-ci.org/OpenSTFoundation/ost-sdk-java.svg?branch=master)](https://travis-ci.org/OpenSTFoundation/ost-sdk-java)
[![Gitter: JOIN CHAT](https://img.shields.io/badge/gitter-JOIN%20CHAT-brightgreen.svg)](https://gitter.im/OpenSTFoundation/SimpleToken)

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
git clone https://github.com/OpenSTFoundation/ost-sdk-java.git
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
// the latest valid API endpoint is "https://s5-api.sandboxost.com/testnet/v2/", this may change in the future
HashMap <String,Object> sdkConfig = new HashMap<String,Object>();
sdkConfig.put("apiEndpoint","[V1_API_ENDPOINT]");
sdkConfig.put("apiKey","[YOUR_API_KEY]");
sdkConfig.put("apiSecret","[YOUR_API_SECRET]");
OSTSDK ostObj = new OSTSDK(sdkConfig);
com.ost.services.Manifest services = (com.ost.services.Manifest) ostObj.services;
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
JsonObject response = userService.getList( params );
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
JsonObject response = sessionsService.getList( params );
System.out.println("response: " + response.toString() );
```

### Price Points Module


```java
com.ost.services.PricePoints pricePointsService = services.pricePoints;
```

Get Price Points:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
JsonObject response = pricePointsService.get( params );
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
