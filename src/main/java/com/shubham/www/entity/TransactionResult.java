package com.shubham.www.entity;

public class TransactionResult {

    private TransactionStatus status;
    private String message;


    public TransactionResult(TransactionStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String toString() {
        return "TransactionResult{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
