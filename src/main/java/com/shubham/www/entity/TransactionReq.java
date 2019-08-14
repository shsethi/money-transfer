package com.shubham.www.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Currency;

/**
 * @author shsethi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionReq {

    AccountNumber sender;
    AccountNumber receiver;
    double amount;
    Currency currency;
}
