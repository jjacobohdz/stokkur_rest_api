# Accounts Management

This project provides a REST API to manage accounts.

The project is based on the Spring Boot framework. Specifically it uses the following modules:

- Spring Data JPA

- Spring REST

- Spring Security

- Spring Maven Plugin

- Spring embedded Tomcat Server Instance


The project also uses the following technologies and libraries:
- HSQL for handling the persistence of the information (http://hsqldb.org/)

- JWT to provide security to the exposed REST API (https://jwt.io/)

- JUnit (https://junit.org/) to provide a set of test unit cases.

- Lombok. Pretty cool library that helps developers to avoid writing boiler plate code such as adding getters/setters, constructors, etc. (https://projectlombok.org/)


## Getting Started

The following instructions will guide you through getting a copy of the project and executing it in your environment.


### Prerequisites

To successfully set up the project, you will need Maven and git installed in your environment.

For Maven installation visit https://maven.apache.org/

For git installation visit https://es.atlassian.com/git/tutorials/install-git


To facilitate the installation process, this project uses an In-Memory HyperSQL Database (HSQLDB), so you don't have to install and configure a local DBMS instance to use it.

Also, for deploying the REST API, the Spring embedded Tomcat instance is used, so you don't need to install and configure your own.


### Downloading

To get a copy of the project to your local environment:

1. Open a Command Line

2. Navigate to the directory where you want to download the project

3. Execute the following command

```
git clone https://github.com/jjacobohdz/stokkur_rest_api.git
```

4. Navigate to downloaded directory AccountManagement

```
cd stokkur_rest_api\AccountManagement
```

5. To run the application, execute the following command

```
mvn spring-boot:run
```

After this, you will be able to start interacting with the provided REST API, as described below.


## REST API Consumption

1. To get a list of all the existing accounts, execute the following command

```
curl -X GET localhost:8080/api/accounts
```

2. To get an account by id, use the following command and provide the desired id (1 in this example)
```
curl -v localhost:8080/api/accounts/1
```

3. In order to execute POST, PUT, DELETE operations, a token must be provided. Use the following command to get a Token
Standard user token:
```
curl -X POST localhost:8080/auth/login -H "Content-Type:application/json" -d "{\"username\":\"duke\", \"password\":\"java\"}"
```

Admin user token (required for DELETE operations):
```
curl -X POST localhost:8080/auth/login -H "Content-Type:application/json" -d "{\"username\":\"admin\", \"password\":\"admin\"}"
```

The provided Token expires after 60 minutes.


4. To create a new Account use the following command. Replace the token in the example for the one you obtained in the last step. you can also change the account values.
```
curl -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfQURNSU4iXSwiaWF0IjoxNjA0ODIxNTI4LCJleHAiOjE2MDQ4MjUxMjh9.weBkxPRA2n6lcQn7z0EKbdvveFYaPmXETpreStW6u04" -X POST localhost:8080/api/accounts -H "Content-type:application/json" -d "{\"firstName\": \"Nicciy\", \"lastName\": \"Hernandez\", \"email\": \"nicciy@gmail.com\", \"age\": 15, \"balance\": 1500}"
```

You can confirm the new account was created successfully by executing:
```
curl -X GET localhost:8080/api/accounts
```

5. To delete an account use the following command. An Admin token is required.
```
curl -H "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsInJvbGVzIjpbIlJPTEVfVVNFUiIsIlJPTEVfQURNSU4iXSwiaWF0IjoxNjA0ODIwMjg5LCJleHAiOjE2MDQ4MjM4ODl9.44HRG6B1vhs5k9ctusIx5Tv_MQmwTQLfkoFtBvZ2n8E" -X DELETE localhost:8080/api/accounts/1
```

You can confirm the new account was deleted successfully by executing:
```
curl -X GET localhost:8080/api/accounts
```

## Unit Tests

To specifically run the test cases included with the project, you can execute
```
mvn test
```