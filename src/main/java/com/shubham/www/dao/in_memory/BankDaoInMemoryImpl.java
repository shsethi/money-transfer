package com.shubham.www.dao.in_memory;

import com.shubham.www.dao.BankDAO;
import com.shubham.www.entity.Account;
import com.shubham.www.entity.AccountNumber;
import com.shubham.www.persistance.InMemoryStore;
import com.shubham.www.exceptions.AccountDoesNotExistException;

import java.util.List;

/**
 * @author shsethi
 */
public class BankDaoInMemoryImpl implements BankDAO {
    private InMemoryStore memoryStore;

    public BankDaoInMemoryImpl(InMemoryStore memoryStore) {
        this.memoryStore = memoryStore;
    }

    @Override
    public Account getAccountByNumber(AccountNumber accountNum) throws AccountDoesNotExistException {
        return memoryStore.getAccount(accountNum);
    }

    @Override
    public List<Account> getAllAccounts() {
        return memoryStore.getAllAccount();
    }
}
