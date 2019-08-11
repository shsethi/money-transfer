package com.shubham.www.controller;

import com.shubham.www.dao.DAOManager;
import com.shubham.www.dao.StoreType;
import com.shubham.www.entity.Account;
import com.shubham.www.entity.AccountNumber;
import com.shubham.www.entity.Money;
import com.shubham.www.exceptions.AccountDoesNotExistException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Currency;
import java.util.List;
import java.util.Map;

/**
 * @author shsethi
 */
@Path("/account")
public class AccountController {

    private final DAOManager daoManager = DAOManager.getDAOManager(StoreType.IN_MEMORY);

    @GET
    @Path("/all")
    @Produces("application/json")
    public Response getAllAccount() {
        List<Account> list = daoManager.getBankDAO().getAllAccounts();
        return Response.status(Response.Status.OK).entity(list).build();
    }

    @GET
    @Produces("application/json")
    public Response getAccount(@QueryParam("accountNum") long accountNum) throws AccountDoesNotExistException {
        try {
            AccountNumber accountNumber = new AccountNumber(accountNum);
            Account account = daoManager.getBankDAO().getAccountByNumber(new AccountNumber(accountNum));
            return Response.ok().entity(account).build();
        } catch (IllegalArgumentException | AccountDoesNotExistException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/balance")
    @Produces("application/json")
    public Response getBalance(@QueryParam("accountNum") long accountNum) {


        try {
            Map<Currency, Money> balance = daoManager.getAccountDAO().getAccountBalance(new AccountNumber(accountNum));
            return Response.ok().entity(balance).build();
        } catch (AccountDoesNotExistException e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
