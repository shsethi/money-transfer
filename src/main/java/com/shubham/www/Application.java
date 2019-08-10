package com.shubham.www;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

/**
 * @author shsethi
 */
public class Application {
    public static void main(String[] args) throws Exception {
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
        } finally{
            jettyServer.destroy();
        }
    }
}
