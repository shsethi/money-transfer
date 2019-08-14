package com.shubham.www;

import com.shubham.www.dao.AccountDAO;
import com.shubham.www.dao.DAOManager;
import com.shubham.www.dao.StoreType;
import com.shubham.www.entity.Account;
import com.shubham.www.entity.AccountNumber;
import com.shubham.www.exceptions.AccountDoesNotExistException;
import com.shubham.www.exceptions.InValidAccountNumException;
import com.shubham.www.persistance.InMemoryStore;
import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.Currency;

/**
 * @author shsethi
 */
public class Application {


    private static Logger log = Logger.getLogger(Application.class);

    public static void main(String[] args) throws Exception {
        bankInit();
        serverInit();
    }

    private static void bankInit() {

        final DAOManager daoManager = DAOManager.getDAOManager(StoreType.IN_MEMORY);

        InMemoryStore store = (InMemoryStore) daoManager.getMemoryStore();
        AccountDAO accountDAO = daoManager.getAccountDAO();


        try {
            AccountNumber acc1 = store.addAccount(new Account("James", Currency.getInstance("USD"), new AccountNumber(99_999_999)));
            AccountNumber acc2 = store.addAccount(new Account("Paul", Currency.getInstance("USD"),new AccountNumber(22_999_999)));


            accountDAO.addMoney(acc1, 500, Currency.getInstance("USD"));
            accountDAO.addMoney(acc1, 400, Currency.getInstance("USD"));
            accountDAO.addMoney(acc1, 100, Currency.getInstance("INR"));

            accountDAO.getAccountBalance(acc1);

            //store.transfer(acc1, acc2, 200, Currency.getInstance("USD"));

        } catch (AccountDoesNotExistException | InValidAccountNumException e) {
            log.info(e);
        }


    }

    private static void serverInit() {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");

        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);

        ServletHolder jerseyServlet = context.addServlet(ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);

//        jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", HelloWorld.class.getCanonicalName());
        jerseyServlet.setInitParameter("jersey.config.server.provider.packages", "com.shubham.www.controller");

        try {
            jettyServer.start();
            jettyServer.join();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jettyServer.destroy();
        }
    }
}
