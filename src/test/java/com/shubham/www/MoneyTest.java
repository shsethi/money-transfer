package com.shubham.www;

import com.shubham.www.entity.Money;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author shsethi
 */
public class MoneyTest {


    @Test
    public void checkConstructors() {
        String message = "Amount from Money object didn't match it's initialisation value";

        Assert.assertEquals(message, Money.of(BigDecimal.valueOf(54.99)).getAmount(), BigDecimal.valueOf(54.99));
        Assert.assertNotEquals(message, Money.of(BigDecimal.valueOf(54.99)).getAmount(), BigDecimal.valueOf(55));
        Assert.assertEquals(message, Money.of(54.99).getAmount(), BigDecimal.valueOf(54.99));

    }

    @Test
    public void checkArithmetic() {
        Money first = Money.of(54.57);
        Money second = Money.of(45.54);
        Money zero = Money.zero();

        Assert.assertEquals("Addition invalid", Money.of(100.11), first.plus(second));
        Assert.assertEquals("Addition with zero", first, first.plus(zero));

        Assert.assertEquals("Subtraction invalid", Money.of(9.03), first.minus(second));
        Assert.assertEquals("Subtraction with zero", first, first.minus(zero));
        Assert.assertEquals("Subtraction resulting in negative result invalid", Money.of(-9.03), second.minus(first));

        Assert.assertTrue("Money should be equal to itself", first.isEqual(first));
        Assert.assertTrue("Money with same value should be equal", first.isEqual(Money.of(54.57)));
    }

    @Test
    public void checkComparisons() {
        Money negative = Money.of(-54);
        Money postive = Money.of(78);
        Money zero = Money.zero();


        Assert.assertTrue("Negative money should be less than zero", negative.isLessThan(zero));
        Assert.assertTrue("Positive money should be more than zero", postive.isGreaterThan(zero));
    }
}
