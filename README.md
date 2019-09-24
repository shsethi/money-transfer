

# Money Transfer API

- App exposes endpoints to create account, add/withdraw money and transfer money from one account to another
- Transfer of money takes lock on both the accounts to ensure thread safety and in ordered of increasing account number to prevent deadlock
- Representing amount held by each account as objects of Money class which are *immutable*

## Sample request

- get all accounts
    ```
    curl -X GET  'http://localhost:8080/v1/account/balance?accountNum=99999999'
    ```

- get balance for one account accounts
    ```
    curl -X GET  http://localhost:8080/v1/account/all
    ```

- transfer money from one account to another

    ```
    curl -X POST \
      http://localhost:8080/v1/transfer \
      -H 'Content-Type: application/json' \
      -H 'content-length: 79' \
      -d '{
        "sender":22999999,
        "receiver":99999999,
        "amount": 100,
        "currency":"USD"
    }'
    ```

### Assumptions and Simplification

- In-memory datastore, extensible to other datastore
- All the apis are synchronous / blocking
- Each account can hold  multiple currencies
- Ignored currency conversion


## Pre-requisites

- Install `maven` to build the project


## Build

To build the project execute following
```
$ bin/setup
```

This packages the java project into jar and runs tests.

## Run

```
$ bin/money_transfer
```

### Dependencies
- used `lombok` to avoid writing broiler plate code