package com.halcyonmobile.integration;

import com.halcyonmobile.model.dto.QuestionCardDTO;
import com.halcyonmobile.rest.PrivilegesService;
import com.halcyonmobile.rest.QuestionCardService;
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
public class QuestionCardServiceTest {
    public static QuestionCardService questionService = new QuestionCardService();
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
    public void getByInexistentIdPosition() {
        Response response = server.newRequest("/questioncard/200").request().buildGet().invoke();
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        response.close();
    }

    @Test
    public void getByIdPositionTest() {
        Response response = server.newRequest("/questioncard/1").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        List<QuestionCardDTO> result = response.readEntity(new GenericType<List<QuestionCardDTO>>() {
        });

        assertNotNull(result);

        response.close();
    }


}

