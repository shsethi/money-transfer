package com.shubham.www.persistance;

import com.shubham.www.entity.Account;
import com.shubham.www.entity.AccountNumber;
import com.shubham.www.entity.Money;
import com.shubham.www.entity.TransactionResult;
import com.shubham.www.entity.TransactionStatus;
import com.shubham.www.exceptions.AccountDoesNotExistException;
import com.shubham.www.exceptions.InSufficientBalanceException;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shsethi
 */
final public class InMemoryStore extends Store {

    private static Logger log = Logger.getLogger(InMemoryStore.class);

    private Map<AccountNumber, Account> accountStore;
    private static InMemoryStore inMemoryStore;

    public static InMemoryStore getInMemoryStore() {
        if (inMemoryStore == null) {
            inMemoryStore = new InMemoryStore();
        }
        return inMemoryStore;
    }

    private InMemoryStore() {
        this.accountStore = new ConcurrentHashMap<>();
    }

    public Account getAccount(AccountNumber accountNumber) throws AccountDoesNotExistException {
        Account account = this.accountStore.get(accountNumber);
        if (account == null) {
            throw new AccountDoesNotExistException("Account doesn't exist for account number " + accountNumber);
        }
        return account;
    }

    public Map<Currency, Money> getBalance(AccountNumber accountNumber) throws AccountDoesNotExistException {
        Account account = this.accountStore.get(accountNumber);
        if (account == null) {
            throw new AccountDoesNotExistException("Account doesn't exist for account number " + accountNumber);
        }
        Map<Currency, Money> wallet = account.getWallet();
        return wallet;
    }

    private String formatResult(Map<Currency, Money> wallet) {
        return wallet.values().toString();
    }

    public AccountNumber addAccount(Account account) {
        accountStore.put(account.getAccountNumber(), account);
        return account.getAccountNumber();
    }


    public void addMoney(AccountNumber accNum, Money money, Currency currency) throws AccountDoesNotExistException {
        Account account = this.accountStore.get(accNum);
        if (account == null) {
            throw new AccountDoesNotExistException("Account doesn't exist for account number " + accNum);
        }
        Map<Currency, Money> wallet = account.getWallet();
        wallet.compute(currency, (curr, value) -> {
            if (value == null) {
                return money;
            } else {
                return value.plus(money);
            }
        });

        log.info("Add money complete " + accNum + " for " + money);

    }


    public void withDrawMoney(AccountNumber accNum, Money money, Currency currency) throws AccountDoesNotExistException, InSufficientBalanceException {
        Account account = this.accountStore.get(accNum);

        if (account == null) {
            throw new AccountDoesNotExistException("Account doesn't exist for account number = " + accNum);
        }
        Map<Currency, Money> wallet = account.getWallet();

        Money value = wallet.get(currency);
        if (value == null || value.isLessThan(money)) {
            throw new InSufficientBalanceException("In-sufficient balance in accNum = " + accNum);
        } else {
            wallet.put(currency, value.minus(money));
        }
        log.info("Withdraw complete " + accNum + " for " + money);
    }


    public TransactionResult transfer(AccountNumber sender, AccountNumber receiver, double amount, Currency currency) {

        log.info("Attempting transfer of " + amount + " from " + sender.getAccountId() + "  to " + receiver.getAccountId());
        try {

            Account senderAccount = this.accountStore.get(sender);
            Account receiverAccount = this.accountStore.get(receiver);

            if (senderAccount == null) {
                throw new AccountDoesNotExistException("Account doesn't exist for account number " + senderAccount);
            }
            if (receiverAccount == null) {
                throw new AccountDoesNotExistException("Account doesn't exist for account number " + receiverAccount);
            }
            synchronized (senderAccount) {
                synchronized (receiverAccount) {
                    withDrawMoney(sender, Money.of(amount, currency), currency);
                    addMoney(receiver, Money.of(amount, currency), currency);
                }
            }

        } catch (AccountDoesNotExistException | InSufficientBalanceException e) {
            return new TransactionResult(TransactionStatus.FAILURE, e.getMessage());
        }
        return new TransactionResult(TransactionStatus.SUCCESS, "Money transfer from account " + sender + " to " + receiver + " complete");
    }

    public List<Account> getAllAccount() {
        return new ArrayList<>(accountStore.values());
    }
}

