# test_gas_water example project

## Overview

Test project for writing and reading data through REST-API using HSQLDB-in-memory database

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
