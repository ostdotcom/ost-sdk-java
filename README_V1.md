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
  <version>1.1.0</version>
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
// the latest valid API endpoint is "https://sandboxapi.ost.com/v1/", this may change in the future
HashMap <String,Object> sdkConfig = new HashMap<String,Object>();
sdkConfig.put("apiEndpoint","[V1_API_ENDPOINT]");
sdkConfig.put("apiKey","[YOUR_API_KEY]");
sdkConfig.put("apiSecret","[YOUR_API_SECRET]");
OSTSDK ostObj = new OSTSDK(sdkConfig);
com.ost.services.v1.Manifest services = (com.ost.services.v1.Manifest) ostObj.services;
```

### Users Module 

```java
com.ost.services.v1.Users userService = services.users;
```

Create a new user:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("name", "Alice");
JsonObject response = userService.create( params );
System.out.println("response: " + response.toString() );
```

Edit an existing user:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("id", "1234-1928-1081dsds-djhksjd");
params.put("name", "Bob");
JsonObject response = userService.edit( params );
System.out.println("response: " + response.toString() );
```

Get an existing user:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("id", "1234-1928-1081dsds-djhksjd");
JsonObject response = userService.get( params );
System.out.println("response: " + response.toString() );
```

Get a list of users and other data:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
JsonObject response = userService.list( params );
System.out.println("response: " + response.toString() );
```

### Airdrops Module 

```java
com.ost.services.v1.Airdrops airdropService = services.airdrops;
```

Execute Airdrop:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("amount", 1);
params.put("user_ids", "f87346e4-61f6-4d55-8cb8-234c65437b01");
JsonObject response = airdropService.execute( params );
System.out.println("response: " + response.toString() );
```

Get Airdrop Status:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("id", 'ecd9b0b2-a0f4-422c-95a4-f25f8fc88334');
JsonObject response = airdropService.get( params );
System.out.println("response: " + response.toString() );
```

List Airdrop

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("page_no", 1);
params.put("limit", 50);
params.put("current_status", "processing,complete");
JsonObject response = airdropService.list( params );
System.out.println("response: " + response.toString() );
```


### Token Module 

```java
com.ost.services.v1.Token tokenService = services.token;
```

Get details:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
JsonObject response = tokenService.get( params );
System.out.println("response: " + response.toString() );
```

### Actions Module 


```java
com.ost.services.v1.Actions actionService = services.actions;
```

Create a new action:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("name", "Voteup");
params.put("kind", "user_to_user");
params.put("currency", "USD");
params.put("arbitrary_amount", false);
params.put("amount", 1.01);
params.put("arbitrary_commission", false);
params.put("commission_percent", 1);
JsonObject response = actionService.create( params );
System.out.println("response: " + response.toString() );
```

Edit an action:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("id", 22599);
params.put("name", "Like");
JsonObject response = actionService.edit( params );
```

Get an action:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("id", 22599);
JsonObject response = actionService.get( params );
System.out.println("response: " + response.toString() );
```

List actions:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
JsonObject response = actionService.list( params );
System.out.println("response: " + response.toString() );
```

### Transaction Module 

```java
com.ost.services.v1.Transactions transactionService = services.transactions;
```

Execute Transaction:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("from_user_id", "0a201640-77a7-49c8-b289-b6b5d7325323");
params.put("to_user_id", "24580db2-bf29-4d73-bf5a-e1d0cf8c8928");
params.put("action_id", "22599");
JsonObject response = transactionService.execute( params );
System.out.println("response: " + response.toString() );
```

Get Transaction Status:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("id", "84d97848-074f-4a9a-a214-19076cfe9dd1");
JsonObject response = transactionService.get( params );
```

List Transactions:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("page_no", 1);
params.put("limit", 10);
JsonObject response = transactionService.list( params );
System.out.println("response: " + response.toString() );
```

### Transfer Module 

```java
com.ost.services.v1.Transfer transferService = ostObj.services.transfers;
```

Execute ST Prime Transfer:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("to_address", "0xd2b789293674faEE51bEb2d0338d15401dEbfdE3");
params.put("amount", 1);
JsonObject response = transferService.execute( params );
System.out.println("response: " + response.toString() );
```

Get Transfer Status:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
params.put("id", "38895b82-737e-4b23-b111-fec96e52f3b2");
JsonObject response = transferService.get( params );
System.out.println("response: " + response.toString() );
```

List Transfers:

```java
HashMap <String,Object> params = new HashMap<String,Object>();
JsonObject response = transferService.list( params );
```

