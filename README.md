# Mobile Number API - Vodafone

A REST Web-Service responsible of maintaining a database of mobile numbers. Uses JWT Authentication and runs with Docker.

## Endpoints

### API
`http://localhost:8080/api/subscribers`

`http://localhost:8080/api/users/sign-up`

`http://localhost:8080/api/login`

### Database
`http://localhost:8080/h2-console`

# Used Technologies

- **VS Code** - 1.24.11
- **OPEN JDK** - 10.0.2
- **Spring-Boot** - 2.0.3
- **Maven** - 3.5.4
- **H2 in-memory database** 

# Properties 

`application-<environment>.properties`

This file contains properties for configuring database and logging. There are two files under the src/main/resources directory:

- application-dev.properties
- application-prod.properties

You should set at least propertie 'logging.path' for defining file path:

# Execution

To run the Web-Service, follow the steps below according to your operation system:

** This assumes that Maven and Java is already configured properly in your environment **

## Windows
1. Open the  **Command Prompt**
2. Navigate to the project root folder: `cd C:\<your_project_path_folder>`
3. Run command `mvn clean package`
4. Navigate to target folder: `cd target`
5. Move the .jar file `mobile-number-api-<version>.jar` to your desired environment folder
6. In your environment folder run command:
- for production environment:
`start java -Dspring.profiles.active=prod -jar mobile-number-api-<version>.jar`
- for development environment:
`start java -Dspring.profiles.active=dev -jar mobile-number-api-<version>.jar`


## Linux
1. Open the  **Console**
2. Navigate to the project root folder: `cd /<your_project_path_folder>`
3. Run command `mvn clean package`
4. Navigate to target folder: `cd target`
5. Move the .jar file`mobile-number-api-<version>.jar` to your desired environment folder
6. In your environment folder run command:
- for production environment:
`java -Dspring.profiles.active=prod -jar mobile-number-api-<version>.jar &`
- for development environment:
`java -Dspring.profiles.active=dev -jar mobile-number-api-<version>.jar &`

# Requests examples

Using `curl` command tool in Windows

##  Authentication JWT

### Create user
curl -i -k -i -H "Content-Type: application/json" -X POST -d "{ \"login\": \"filipe\",\"password\": \"123\"}" http://localhost:8080/api/users/sign-up

### Login User
curl -i -k -H "Content-Type: application/json" -X POST -d "{ \"login\": \"filipe\",\"password\": \"123\"}" http://localhost:8080/api/login

### Header Bearer
curl -i -k -H "Authorization: Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJmaWxpcGUiLCJleHAiOjE1NTM2NTY0OTF9.CgVFKVdgv1kCTkGUVIPMz3X22mhSJiJBV3OfP-CPUM1p9gpq7bwUmCcNbSK5GIWO3zVNRq8UGayvlDg04BQlIg" -X GET http://localhost:8080/api/users


##  Basic Requests

## List
curl -i -k -X GET http://localhost:8080/api/subscribers

## Get
curl -i -k -X GET http://localhost:8080/subscribers/1

## Create
curl -i -k -X POST -H "Content-Type: application/json" -d "{ \"msisdn\": \"1155972532645\", \"customerIdOwner\": 0, \"customerIdUser\": 0,  \"serviceType\": \"MOBILE_PREPAID\", \"serviceStartDate\": 1528208058559 }" http://localhost:8080/subscribers

curl -i -k -X POST -H "Content-Type: application/json" -d "{ \"msisdn\": \"1155974786091\", \"customerIdOwner\": 1, \"customerIdUser\": 1,  \"serviceType\": \"MOBILE_POSTPAID\", \"serviceStartDate\": 1528208058550 }" http://localhost:8080/subscribers

## Update
curl -i -k -X PUT -H "Content-Type: application/json" -d "{ \"msisdn\": \"1155972532644\", \"customerIdOwner\": 1, \"customerIdUser\": 1,  \"serviceType\": \"MOBILE_POSTPAID\", \"serviceStartDate\": 1528208058559 }" http://localhost:8080/subscribers/1

## Delete
curl -i -k -X DELETE -H "Content-Type: application/json" http://localhost:8080/subscribers/1




