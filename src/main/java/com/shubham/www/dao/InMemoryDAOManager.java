package com.shubham.www.dao;

import com.shubham.www.dao.in_memory.AccountDaoInMemoryImpl;
import com.shubham.www.dao.in_memory.BankDaoInMemoryImpl;
import com.shubham.www.persistance.InMemoryStore;

/**
 * @author shsethi
 */
public class InMemoryDAOManager extends DAOManager {


    private InMemoryStore memoryStore;
    protected AccountDAO accountDAO = null;
    protected BankDAO bankDAO = null;


    public InMemoryStore getMemoryStore() {
        return memoryStore;
    }

    public InMemoryDAOManager() {
        this.memoryStore = InMemoryStore.getInMemoryStore();
    }

    @Override
    public AccountDAO getAccountDAO() {
        if (this.accountDAO == null) {
            this.accountDAO = new AccountDaoInMemoryImpl(memoryStore);
        }
        return this.accountDAO;
    }

    @Override
    public BankDAO getBankDAO() {
        if (this.bankDAO == null) {
            this.bankDAO = new BankDaoInMemoryImpl(memoryStore);
        }
        return this.bankDAO;
    }


}
