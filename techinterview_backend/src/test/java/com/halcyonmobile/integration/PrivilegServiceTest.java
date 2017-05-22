package com.halcyonmobile.integration;

import com.halcyonmobile.model.Position;
import com.halcyonmobile.model.Privileges;
import com.halcyonmobile.rest.PrivilegesService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by helmut on 22.05.2017.
 */
public class PrivilegServiceTest {
    public static PrivilegesService questionService = new PrivilegesService();
    public static InMemoryRestServer server;

    @BeforeClass
    public static void beforeClass() throws Exception {
        server = InMemoryRestServer.create(questionService);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        server.close();
    }

    @Test
    public void getAllTest() {
        Response response = server.newRequest("/privileg").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        List<Privileges> privileges = response.readEntity(new GenericType<List<Privileges>>() {
        });
        for (Privileges privileg : privileges) {
            assertNotNull(privileg.getIdprivileges());
            assertNotEquals(0, privileg.getIdprivileges());
        }

        response.close();
    }

    @Test
    public void getByPrivilegByNameTest() {
        Response response = server.newRequest("/privileg/manager").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        List<Privileges> privileges = response.readEntity(new GenericType<List<Privileges>>() {
        });
        assertEquals(privileges.size(), 1);
        response.close();
    }

    @Test
    public void getByPrivilegByInsexistentNameTest() {
        Response response = server.newRequest("/privileg/BusDriver").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        List<Privileges> privileges = response.readEntity(new GenericType<List<Privileges>>() {
        });
        assertEquals(privileges.size(), 0);
        response.close();
    }
}
