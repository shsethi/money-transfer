package com.shubham.www.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(required = true)
    AccountNumber sender;
    @JsonProperty(required = true)
    AccountNumber receiver;
    @JsonProperty(required = true)
    double amount;
    @JsonProperty(required = true)
    Currency currency;

    long requestTimeStamp = System.currentTimeMillis();
}
