package com.shubham.www.controller;

import com.shubham.www.dao.DAOManager;
import com.shubham.www.dao.StoreType;
import com.shubham.www.entity.TransactionReq;
import com.shubham.www.entity.TransactionResult;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * @author shsethi
 */

@Path("/v1/transaction")
public class TransactionController {

    private final DAOManager daoManager = DAOManager.getDAOManager(StoreType.IN_MEMORY);


    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response doTransaction(TransactionReq transaction) {
        try {
            TransactionResult transactionResult = daoManager.getBankDAO().transact(transaction);
            return Response.ok().entity(transactionResult).build();

        } catch (Exception  e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}
