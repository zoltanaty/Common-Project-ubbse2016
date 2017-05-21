package com.halcyonmobile.integration;

import com.halcyonmobile.model.Position;
import com.halcyonmobile.rest.PositionService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.*;

public class PositionServiceTest {
    public static PositionService positionService = new PositionService();
    public static InMemoryRestServer server;

    @BeforeClass
    public static void beforeClass() throws Exception {
        server = InMemoryRestServer.create(positionService);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        server.close();
    }

    @Test
    public void getAllTest() {
        Response response = server.newRequest("/position/").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        List<Position> positionList = response.readEntity(new GenericType<List<Position>>(){});
        for (Position position:positionList) {
            assertNotNull(position.getId());
            assertNotEquals(0, position.getId());
        }

        response.close();
    }

    @Test
    public void findByIdTest() {
        Response response = server.newRequest("/position/1").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        Position position = response.readEntity(Position.class);

        assertNotNull(position);
        assertEquals(positionService.findById(1).toString(), position.toString());
        response.close();
    }

    @Test
    public void findByNameTest() {
        Response response = server.newRequest("/position/name/Junior Android Developer").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        int id = response.readEntity(int.class);
        assertEquals(positionService.findByName("Junior Android Developer"), id);
        response.close();
    }


}