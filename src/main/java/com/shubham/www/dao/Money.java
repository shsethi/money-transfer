package com.shubham.www.dao;

import lombok.Getter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

import static java.math.BigDecimal.ZERO;

/**
 * @author shsethi
 */

public final class Money implements Comparable<Money>, Serializable {

    @Getter
    private BigDecimal amount;
    @Getter
    private final Currency currency;

    private static Currency DEFAULT_CURRENCY = Currency.getInstance("USD");

    public static final class CurrencyMismatchException extends RuntimeException {
        CurrencyMismatchException(String message) {
            super(message);
        }
    }

    public static void init(Currency defaultCurrency) {
        DEFAULT_CURRENCY = defaultCurrency;
    }

    public static Money of(BigDecimal number) {
        return new Money(number, DEFAULT_CURRENCY);
    }

    public static Money of(double number) {
        return new Money(BigDecimal.valueOf(number), DEFAULT_CURRENCY);
    }

    public static Money of(BigDecimal number, String currencyCode) {
        return new Money(number, Currency.getInstance(currencyCode));
    }

    public static Money of(double number, String currencyCode) {
        return new Money(BigDecimal.valueOf(number), Currency.getInstance(currencyCode));
    }

    public static Money zero() {
        return new Money(BigDecimal.ZERO);
    }

    public boolean isSameCurrencyAs(Money that) {
        boolean result = false;
        if (that != null) {
            result = this.currency.equals(that.currency);
        }
        return result;
    }

    public boolean isPositive() {
        return amount.compareTo(ZERO) > 0;
    }

    public boolean isZero() {
        return amount.compareTo(ZERO) == 0;
    }



    public boolean isEqual(Money that) {
        matchCurrencies(that);
        return compareAmount(that) == 0;
    }

    public boolean isGreaterThan(Money that) {
        matchCurrencies(that);
        return compareAmount(that) > 0;
    }
    public boolean isLessThan(Money that) {
        matchCurrencies(that);
        return compareAmount(that) < 0;
    }

    public boolean isGreaterThanEqual(Money that) {
        matchCurrencies(that);
        return compareAmount(that) >= 0;
    }


    public String toString() {
        return currency.getSymbol() + " " + amount.toPlainString();
    }

    public int compareTo(Money that) {
        final int EQUAL = 0;
        if (this == that) return EQUAL;

        //the object fields are never null
        int comparison = this.amount.compareTo(that.amount);
        if (comparison != EQUAL) return comparison;

        comparison = this.currency.getCurrencyCode().compareTo(
                that.currency.getCurrencyCode()
        );
        if (comparison != EQUAL) return comparison;

        return EQUAL;
    }


    private Money(BigDecimal amount, Currency currency) {
        checkIfValid(amount, currency);
        this.amount = amount;
        this.currency = currency;

    }

    private Money(BigDecimal amount) {
        this(amount, DEFAULT_CURRENCY);
    }

    private void checkIfValid(BigDecimal amt, Currency curr) {
        if (amt == null) {
            throw new IllegalArgumentException("Amount cannot be null");
        }
        if (curr == null) {
            throw new IllegalArgumentException("Currency cannot be null");
        }
        if (amt.scale() > curr.getDefaultFractionDigits()) {
            throw new IllegalArgumentException(
                    "Number of decimals is " + amt.scale() + ", but currency only takes " +
                            curr.getDefaultFractionDigits() + " decimals."
            );
        }
    }

    public boolean equals(Object second){
        if (this == second) return true;
        if (! (second instanceof Money) ) return false;
        Money that = (Money)second;
        return Objects.equals(this.amount, that.amount) && Objects.equals(this.currency, that.currency);
    }

    private void matchCurrencies(Money second) {
        if (!this.currency.equals(second.getCurrency())) {
            throw new CurrencyMismatchException(
                    second.getCurrency() + " doesn't match currency : " + currency
            );
        }
    }

    private int compareAmount(Money second) {
        return this.amount.compareTo(second.amount);
    }

}

