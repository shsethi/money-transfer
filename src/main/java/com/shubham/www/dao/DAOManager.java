package com.shubham.www.dao;

import com.shubham.www.persistance.Store;

public abstract class DAOManager {


    public abstract Store getMemoryStore();

    public abstract AccountDAO getAccountDAO();
    public abstract BankDAO getBankDAO();

    public static DAOManager getDAOManager(StoreType pcode) {

        switch (pcode) {
            case IN_MEMORY:
                return new InMemoryDAOManager();
            default:
                return new InMemoryDAOManager();
        }
    }
}
