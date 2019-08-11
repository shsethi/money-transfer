package com.shubham.www.entity;

import lombok.Getter;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author shsethi
 */
public class AccountNumber {

    private static int START;
    private static int END;
    @Getter
    private long accountId;

    public AccountNumber(long accountId) {
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

    private void isValid(long num) {
        if (!(num > START && num < END)) {
            throw new IllegalArgumentException("Invalid account number");
        }
    }

    /**
     * Generate 8 digit long account id .
     *
     * @return the long
     */
    public static long generateAccountId() {
        START = 10_000_000;
        END = 100_000_000;
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
