package com.shubham.www.dao.in_memory;

import com.shubham.www.dao.BankDAO;
import com.shubham.www.entity.Account;
import com.shubham.www.entity.AccountNumber;
import com.shubham.www.entity.TransactionReq;
import com.shubham.www.entity.TransactionResult;
import com.shubham.www.exceptions.AccountDoesNotExistException;
import com.shubham.www.exceptions.InSufficientBalanceException;
import com.shubham.www.persistance.InMemoryStore;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * @author shsethi
 */
public class BankDaoInMemoryImpl implements BankDAO {
    private static Logger log = Logger.getLogger(BankDaoInMemoryImpl.class);
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


    @Override
    public TransactionResult transact(TransactionReq transactionReq) throws AccountDoesNotExistException, InSufficientBalanceException {
        log.info(transactionReq);
        return memoryStore.transfer(transactionReq.getSender(), transactionReq.getReceiver(), transactionReq.getAmount(), transactionReq.getCurrency());
    }
}
