package com.shubham.www.controller;

import com.shubham.www.dao.BuildInfo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
/**
 * @author shsethi
 */
@Path("/hello")
public class HelloWorld {
    @GET
    @Path("test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Test";
    }
    @GET
    @Path("build")
    @Produces(MediaType.APPLICATION_JSON)
    public BuildInfo build() {
        return new BuildInfo("running", "v1");
    }
}
