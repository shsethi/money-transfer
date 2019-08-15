package com.shubham.www.exceptions;

/**
 * @author shsethi
 */
public class InValidAccountNumException extends RuntimeException {
    public InValidAccountNumException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "{ \"error\" : { \"message\":"+ "\""+this.getMessage()+"\"" +"} }";
    }
}
