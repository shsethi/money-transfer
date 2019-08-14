package com.shubham.www.entity;

import com.shubham.www.exceptions.InValidAccountNumException;
import lombok.Getter;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author shsethi
 */
public class AccountNumber {

    private final static int START = 10_000_000;
    private final static int END = 100_000_000;
    @Getter
    private long accountId;

    public AccountNumber(long accountId) throws InValidAccountNumException {
        isValid(accountId);
        this.accountId = accountId;
    }

    public AccountNumber() {
        this.accountId = generateAccountId();
    }

    @Override
    public String toString() {
        return Long.toString(this.accountId);
    }

    private void isValid(long num) throws InValidAccountNumException {
        if(num==0){
            throw new InValidAccountNumException("Account number missing");
        }
        if (!(num > START && num < END)) {
            throw new InValidAccountNumException("Invalid account number");
        }
    }

    /**
     * Generate 8 digit long account id .
     *
     * @return the long
     */
    public static long generateAccountId() {
        return ThreadLocalRandom.current().nextLong(START, END);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountNumber that = (AccountNumber) o;
        return getAccountId() == that.getAccountId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAccountId());
    }
}
