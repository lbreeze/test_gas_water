# test_gas_water example project

## Overview

Test project for writing and reading data through REST-API using HSQLDB-in-memory database

## Problem

"Gas & Water Usage Monitoring Application"
Create an application to monitor gas, cold and hot water usage. No UI needed, only REST API. Two REST API methods should be implemented: one for submitting the current measurements for a given user, other for getting the history of previously submitted measurements for a given user. User inputs should be validated to reject incomplete or invalid data.

Technical Requirements
1.	Use Java 1.8, Spring Framework and Maven.
2.	Use other Java libraries as needed.
3.	Use HSQLDB for storing data. It is ok NOT to persist data across application launches.
4.	Try following all the good principles of writing qualitative and testable code.
5.	Fill in missing requirements as you feel suitable.
6.	Include a short README file describing how the application works and how to build and run the project.

## Usage

It runs as spring boot application with main class `ru.danch.test.CoreApplication`
By default, it runs at 8080 port.

After that you can add records via REST-service `/measurement/add` by `POST`ing request in json-format like that:

```
{
  "userId": 1,
  "type": "GAS",
  "usage": 100.4
}
```

All field are required. `usage` field is limited by `1000` value as maximum, also.
`type` values are restricted to `GAS`, `WATER_HOT`, `WATER_COLD`.

Next, you can list records via `GET`-request at `/measurement/history/{userId}` where `{userId}` is a value you passed while adding records before.
