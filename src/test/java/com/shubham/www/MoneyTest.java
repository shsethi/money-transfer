package com.shubham.www;

import com.shubham.www.dao.Money;
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
    public void checkComparisons() {
        Money negative = Money.of(-54);
        Money postive = Money.of(78);
        Money zero = Money.zero();


        Assert.assertTrue("Negative money should be less than zero", negative.isLessThan(zero));
        Assert.assertTrue("Postive money should be more than zero", postive.isGreaterThan(zero));
    }
}
