package com.shubham.www.dao;

import com.shubham.www.entity.Account;
import com.shubham.www.entity.AccountNumber;
import com.shubham.www.entity.TransactionReq;
import com.shubham.www.entity.TransactionResult;
import com.shubham.www.exceptions.AccountDoesNotExistException;
import com.shubham.www.exceptions.InSufficientBalanceException;

import java.util.List;

/**
 * @author shsethi
 */
public interface BankDAO {

    Account getAccountByNumber(AccountNumber accountNum) throws AccountDoesNotExistException;

    List<Account> getAllAccounts();

    TransactionResult transact(TransactionReq transactionReq) throws AccountDoesNotExistException, InSufficientBalanceException;
}
