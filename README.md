

#Money Transfer API

- App exposed endpoints to create account, add/withdraw money and transfer money from one account to another
- Transfer of money is takes lock on both the accounts to ensure thread safety
- Representing amount held by each account as objects of Money class which are *immutable*


###Assumptions and Simplification

- In-memory datastore
- All the apis are synchronous / blocking
- Each account can hold  multiple currencies
- Ignored currency conversion


##Pre-requisites

- Install `maven` to build the project


##Build

To build the project execute following
```
$ bin/setup
```

This packages the java project into jar and runs tests.

##Run

```
$ bin/money_transfer
```


###Dependencies
- used `lombok` to avoid writing broiler plate code