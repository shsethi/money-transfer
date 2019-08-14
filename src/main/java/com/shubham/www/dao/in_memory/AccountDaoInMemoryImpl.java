package com.shubham.www.dao.in_memory;

import com.shubham.www.dao.AccountDAO;
import com.shubham.www.entity.AccountNumber;
import com.shubham.www.exceptions.InSufficientBalanceException;
import com.shubham.www.persistance.InMemoryStore;
import com.shubham.www.entity.Money;
import com.shubham.www.exceptions.AccountDoesNotExistException;

import java.util.Currency;
import java.util.Map;

/**
 * @author shsethi
 */
public class AccountDaoInMemoryImpl implements AccountDAO {

    private InMemoryStore memoryStore;

    public AccountDaoInMemoryImpl(InMemoryStore memoryStore) {
        this.memoryStore = memoryStore;
    }

    @Override
    public Map<Currency, Money> getAccountBalance(AccountNumber accountNum) throws AccountDoesNotExistException {
        return memoryStore.getBalance(accountNum);
    }


    public void addMoney(AccountNumber accNum, double m, Currency currency) throws AccountDoesNotExistException {
        memoryStore.addMoney(accNum,Money.of(m, currency), currency);

    }

    @Override
    public void withdrawMoney(AccountNumber accNum, double m, Currency currency) throws AccountDoesNotExistException, InSufficientBalanceException {
        memoryStore.withDrawMoney(accNum,Money.of(m, currency), currency);
    }
}
