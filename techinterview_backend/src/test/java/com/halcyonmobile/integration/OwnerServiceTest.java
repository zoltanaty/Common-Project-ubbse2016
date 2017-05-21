package com.halcyonmobile.integration;

import com.halcyonmobile.model.Owners;
import com.halcyonmobile.rest.OwnersService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class OwnerServiceTest {
    public static OwnersService ownersService = new OwnersService();
    public static InMemoryRestServer server;

    @BeforeClass
    public static void beforeClass() throws Exception {
        server = InMemoryRestServer.create(ownersService);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        server.close();
    }

    @Test
    public void getAllTest() {
        Response response = server.newRequest("/owner/").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        ArrayList<Owners> ownersArrayList = response.readEntity(new GenericType<ArrayList<Owners>>(){});
        for (Owners owner:ownersArrayList) {
            assertNotNull(owner);
            assertNotEquals("", owner.getUsername());
        }

        response.close();
    }

    @Test
    public void findByIdTest() {
        Response response = server.newRequest("/owner/admin1").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        Owners owner = response.readEntity(Owners.class);

        assertNotNull(owner);
        assertEquals(ownersService.findById("admin1").toString(), owner.toString());
        response.close();
    }


}