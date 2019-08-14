package com.shubham.www.exceptions;

/**
 * @author shsethi
 */
public class InValidAccountNumException extends Exception {
    public InValidAccountNumException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "{ \"error\" : { \"message\":"+ "\""+this.getMessage()+"\"" +"} }";
    }
}
