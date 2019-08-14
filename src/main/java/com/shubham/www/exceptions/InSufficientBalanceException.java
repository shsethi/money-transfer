package com.shubham.www.exceptions;

/**
 * @author shsethi
 */
public class InSufficientBalanceException extends Exception {
    public InSufficientBalanceException(String s) {
        super(s);
    }

    @Override
    public String toString() {
        return "{ \"error\" : { \"message\":"+ "\""+this.getMessage()+"\"" +"} }";
    }
}
