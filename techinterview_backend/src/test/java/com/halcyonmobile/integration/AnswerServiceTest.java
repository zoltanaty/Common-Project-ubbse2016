package com.halcyonmobile.integration;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import com.halcyonmobile.rest.AnswerService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.halcyonmobile.model.Answer;
import com.halcyonmobile.rest.UserService;


public class AnswerServiceTest {

    public static AnswerService answerService = new AnswerService();
    public static InMemoryRestServer server;

    @BeforeClass
    public static void beforeClass() throws Exception {
        server = InMemoryRestServer.create(answerService);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        server.close();
    }

    @Test
    public void answer_getAnswerWithId_returnsValidAnswer() throws Exception {
        Response response = server.newRequest("/answer/").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        List<Answer> answerList = response.readEntity(new GenericType<List<Answer>>() {
        });
        System.out.println(answerList.toString());
        for (Answer answer : answerList) {
            System.out.println(answer.toString());
        }
        response.close();
    }

    @Test
    public void user_getAnswerWithInexistentId() throws Exception {
        Response response = server.newRequest("/answer/0").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        List<Answer> answerList = response.readEntity(new GenericType<List<Answer>>() {
        });
        assertEquals(answerList.size(), 0);
        response.close();
    }


}
