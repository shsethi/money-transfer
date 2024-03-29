package com.shubham.www.exceptions;

public final class AccountDoesNotExistException extends Exception {
    public AccountDoesNotExistException(String message) {
        super("Account doesn't exist");
    }

    @Override
    public String toString() {
        return "{ \"error\" : { \"message\":"+ "\""+this.getMessage()+"\"" +"} }";
    }
}
