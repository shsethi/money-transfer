package com.shubham.www;

import com.shubham.www.dao.AccountDAO;
import com.shubham.www.dao.DAOManager;
import com.shubham.www.dao.StoreType;
import com.shubham.www.entity.Account;
import com.shubham.www.entity.AccountNumber;
import com.shubham.www.entity.Money;
import com.shubham.www.entity.TransactionResult;
import com.shubham.www.entity.TransactionStatus;
import com.shubham.www.exceptions.AccountDoesNotExistException;
import com.shubham.www.exceptions.InValidAccountNumException;
import com.shubham.www.persistance.InMemoryStore;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Currency;

/**
 * @author shsethi
 */
public class TransferTest {


    private AccountNumber senderAccount;
    private AccountNumber receiverAccount;
    private AccountNumber inValidAccount = new AccountNumber(34_999_999);

    final DAOManager daoManager = DAOManager.getDAOManager(StoreType.IN_MEMORY);

    InMemoryStore store = (InMemoryStore) daoManager.getMemoryStore();
    AccountDAO accountDAO = daoManager.getAccountDAO();

    @Before
    public void init() throws InValidAccountNumException, AccountDoesNotExistException {
        senderAccount = store.addAccount(new Account("James", Currency.getInstance("USD"), new AccountNumber(99_999_999)));
        receiverAccount = store.addAccount(new Account("Paul", Currency.getInstance("USD"), new AccountNumber(22_999_999)));
        accountDAO.addMoney(senderAccount, 400, Currency.getInstance("USD"));
        accountDAO.addMoney(senderAccount, 100, Currency.getInstance("INR"));

    }

    @Test
    public void transferSuccessTest() throws AccountDoesNotExistException {
        TransactionResult transactionResult = store.transfer(senderAccount, receiverAccount, 200, Currency.getInstance("USD"));
        Assert.assertEquals("Transaction should succeed for valid account number", TransactionStatus.SUCCESS, transactionResult.getStatus());
        Assert.assertEquals("Reviever  should receive sent amount", Money.of(200), store.getBalance(receiverAccount).get(Currency.getInstance("USD")));
    }

    @Test
    public void transferFailLowBalance() {
        TransactionResult transactionResult = store.transfer(senderAccount, receiverAccount, 900, Currency.getInstance("USD"));
        Assert.assertEquals("Transaction should fail for invalid currency", TransactionStatus.FAILURE, transactionResult.getStatus());
        Assert.assertEquals("Display correct message on failure", "In-sufficient balance in accNum = 99999999", transactionResult.getMessage());
    }

    @Test
    public void transferFailInvalidAccountTest() {
        TransactionResult transactionResult = store.transfer(senderAccount, inValidAccount, 200, Currency.getInstance("USD"));
        Assert.assertEquals("Transaction should fail for invalid Account number", TransactionStatus.FAILURE, transactionResult.getStatus());
    }

    @Test
    public void transferFailInvalidCurrency() {
        TransactionResult transactionResult = store.transfer(senderAccount, receiverAccount, 200, Currency.getInstance("EUR"));
        Assert.assertEquals("Transaction should fail for non existent currency", TransactionStatus.FAILURE, transactionResult.getStatus());
    }

    @Test(expected = IllegalArgumentException.class)
    public void transferFailInvalidCurrencyCode() {
        TransactionResult transactionResult = store.transfer(senderAccount, receiverAccount, 200, Currency.getInstance("ER"));
        Assert.assertEquals("Transaction should fail for invalid currency code", TransactionStatus.FAILURE, transactionResult.getStatus());
    }
}
