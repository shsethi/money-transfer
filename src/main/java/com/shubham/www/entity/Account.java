package com.shubham.www.entity;

import lombok.Getter;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shsethi
 */

public class Account {

    @Getter
    private AccountNumber accountNumber;

    @Getter
    private String name;

    @Getter
    private Currency defaultCurrency;

    @Getter
    private Map<Currency, Money> wallet = new HashMap<>();

    public Account(String name, Currency defaultCurrency) {

        this.accountNumber = new AccountNumber();
        this.name = name;
        this.defaultCurrency = defaultCurrency;
    }
    public Account(String name, Currency defaultCurrency, AccountNumber accountNumber) {

        this.accountNumber = accountNumber;
        this.name = name;
        this.defaultCurrency = defaultCurrency;
    }

}
