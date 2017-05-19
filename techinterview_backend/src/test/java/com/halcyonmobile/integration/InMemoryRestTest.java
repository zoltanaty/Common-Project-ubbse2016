package com.halcyonmobile.integration;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.core.Response;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.halcyonmobile.rest.UserService;


public class InMemoryRestTest {



    public static UserService userService = new UserService();
    public static InMemoryRestServer server;

    @BeforeClass
    public static void beforeClass() throws Exception {
        server = InMemoryRestServer.create(userService);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        server.close();
    }

    @Test
    public void postSimpleBody() throws Exception {

        Response response = server.newRequest("/user/1").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
