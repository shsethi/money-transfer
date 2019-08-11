package com.shubham.www.persistance;

import com.shubham.www.entity.Account;
import com.shubham.www.entity.AccountNumber;
import com.shubham.www.entity.Money;
import com.shubham.www.entity.TransactionResult;
import com.shubham.www.entity.TransactionStatus;
import com.shubham.www.exceptions.AccountDoesNotExistException;

import java.util.ArrayList;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author shsethi
 */
final public class InMemoryStore extends Store {

    private Map<AccountNumber, Account> accountStore = new HashMap<>();
    private Map<AccountNumber, Map<Currency, Money>> walletStore = new HashMap<>();

    private static InMemoryStore inMemoryStore;

    public static InMemoryStore getInMemoryStore() {
        if (inMemoryStore == null) {
            inMemoryStore = new InMemoryStore();
        }
        return inMemoryStore;
    }

    private InMemoryStore() {
        this.accountStore = new HashMap<>();
        this.walletStore = new HashMap<>();
    }

    public Account getAccount(AccountNumber accountNumber) throws AccountDoesNotExistException {
        Account account = this.accountStore.get(accountNumber);
        if (account == null) {
            throw new AccountDoesNotExistException("Account doesn't exist for account number " + accountNumber);
        }
        return account;
    }

    public Map<Currency, Money> getBalance(AccountNumber accountNumber) throws AccountDoesNotExistException {
        Map<Currency, Money> wallet = this.walletStore.get(accountNumber);
        if (wallet == null) {
            throw new AccountDoesNotExistException("Account doesn't exist for account number " + accountNumber);
        }
        return wallet;
    }

    private String formatResult(Map<Currency, Money> wallet) {
        return wallet.values().toString();
    }

    public AccountNumber addAccount(Account account) {
        accountStore.put(account.getAccountNumber(), account);
        walletStore.put(account.getAccountNumber(), new HashMap<>());
        return account.getAccountNumber();
    }


    public void addMoney(AccountNumber accNum, Money money, Currency currency) throws AccountDoesNotExistException {
        Map<Currency, Money> wallet = walletStore.get(accNum);
        wallet.compute(currency, (curr, value) -> {
            if (value == null) {
                return money;
            } else {
                return value.plus(money);
            }
        });
    }


    public void withDrawMoney(AccountNumber accNum, Money money, Currency currency) throws AccountDoesNotExistException {
        Map<Currency, Money> wallet = walletStore.get(accNum);

        wallet.compute(currency, (curr, value) -> {
            if (value == null) {
                return money;
            } else {
                return value.minus(money);
            }
        });
    }


    public TransactionResult transfer(AccountNumber sender, AccountNumber receiver, double amount, Currency currency) throws AccountDoesNotExistException {

        withDrawMoney(sender, Money.of(amount, currency), currency);
        addMoney(receiver, Money.of(amount, currency), currency);
        return new TransactionResult(TransactionStatus.SUCCESS, "Money transfer from account " + sender + " to " + receiver + " complete");
    }

    public List<Account> getAllAccount() {
        return new ArrayList<>(accountStore.values());
    }
}

