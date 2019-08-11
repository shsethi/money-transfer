package com.shubham.www.dao;

import com.shubham.www.entity.AccountNumber;
import com.shubham.www.entity.Money;
import com.shubham.www.exceptions.AccountDoesNotExistException;

import java.util.Currency;
import java.util.Map;

/**
 * @author shsethi
 */
public interface AccountDAO {

    Map<Currency, Money> getAccountBalance(AccountNumber accountNum) throws AccountDoesNotExistException;

    void addMoney(AccountNumber accNum, double m, Currency currency) throws AccountDoesNotExistException;

    void withdrawMoney(AccountNumber accNum, double m, Currency currency) throws AccountDoesNotExistException ;
}
