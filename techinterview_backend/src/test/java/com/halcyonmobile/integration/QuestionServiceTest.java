package com.halcyonmobile.integration;

import com.halcyonmobile.model.Question;
import com.halcyonmobile.model.QuestionType;
import com.halcyonmobile.rest.QuestionService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.*;

public class QuestionServiceTest {
    public static QuestionService questionService = new QuestionService();
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
        Response response = server.newRequest("/question/").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        List<Question> questionList = response.readEntity(new GenericType<List<Question>>(){});
        for (Question question:questionList) {
            assertNotNull(question.getId());
            assertNotEquals(0, question.getId());
        }

        response.close();
    }

    @Test
    public void findByPositionTest() {
        Response response = server.newRequest("/question/1").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        List<Question> questionList = response.readEntity(new GenericType<List<Question>>(){});
        for (Question question:questionList) {
            assertNotNull(question.getId());
            assertNotEquals(0, question.getId());
        }

        response.close();
    }

    @Test
    public void findByIdTest() {
        Response response = server.newRequest("/question/id/1").request().buildGet().invoke();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        Question question = response.readEntity(Question.class);

        assertNotNull(question);
        assertEquals(questionService.findById(1).getQuestion(), question.getQuestion());
        response.close();
    }


}