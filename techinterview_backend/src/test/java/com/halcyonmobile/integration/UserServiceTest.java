package com.halcyonmobile.integration;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.halcyonmobile.model.User;
import com.halcyonmobile.rest.UserService;


public class UserServiceTest {

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
    public void user_getUserWithId_returnsValidUser() throws Exception {
        Response response = server.newRequest("/user/1").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        
        User user = response.readEntity(User.class);
        String assertString = "User [id=1, name=Teszt Elek, email=tesztelek@email.com, id_position=1]";
        assertEquals(user.toString(), assertString);
    }
    
    @Test
    public void user_getUserWithInexistentId_returnsWrongUser() throws Exception {
        Response response = server.newRequest("/user/0").request().buildGet().invoke();
        assertEquals(Response.Status.NO_CONTENT.getStatusCode(), response.getStatus());
    }
    
    @Test
    public void user_postUserWithCorrectData_returnsUserId() throws Exception {
    	User user = new User();
    	user.setName("testuser");
    	user.setEmail("test@test.test");
    	user.setPositionId(1);
    	Response response = server.newRequest("/user").request().buildPost(Entity.json(user)).invoke();
    	assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    	User responseUser = response.readEntity(User.class);
    	user.setId(responseUser.getId());
    	assertEquals(user.toString(), responseUser.toString());
    	Response deleteResponse = server.newRequest("/user/"+user.getId()).request().buildDelete().invoke();
    	assertEquals(Response.Status.NO_CONTENT.getStatusCode(), deleteResponse.getStatus());
    }
    
}
