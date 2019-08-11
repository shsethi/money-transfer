package com.shubham.www.dao;

import com.shubham.www.entity.Account;
import com.shubham.www.entity.AccountNumber;
import com.shubham.www.exceptions.AccountDoesNotExistException;

import java.util.List;

/**
 * @author shsethi
 */
public interface BankDAO {

    Account getAccountByNumber(AccountNumber accountNum) throws AccountDoesNotExistException;

    List<Account> getAllAccounts();
}
