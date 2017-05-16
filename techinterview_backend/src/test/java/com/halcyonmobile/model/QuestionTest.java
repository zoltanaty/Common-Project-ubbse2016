package com.halcyonmobile.model;

import com.halcyonmobile.persistence.Entitymanager;
import com.halcyonmobile.rest.PositionService;
import com.halcyonmobile.rest.QuestionService;
import com.halcyonmobile.rest.QuestionTypeService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

public class QuestionTest {
    private static int ID = 9999;
    private static String QUESTION = "This is a question";
    private int id_questionType;
    private int id_position;
    private Question product;

    @Before
    public void setUp() throws Exception {
        QuestionTypeService qts = new QuestionTypeService();
        id_questionType = qts.findAll().get(0).getId();
        PositionService ps = new PositionService();
        id_position = ps.findAll().get(0).getId();

        product = new Question();
        product.setId(ID);
        product.setQuestion(QUESTION);
        product.setId_questiontype(id_questionType);
        product.setId_position(id_position);
    }

    @Test
    public void questionTest() {
        assertNotNull(product);
        assertEquals(product.getId(), ID);
        assertEquals(product.getQuestion(), QUESTION);
        assertEquals(product.getId_questiontype(), id_questionType);
        assertEquals(product.getId_position(), id_position);

        EntityManager em = Entitymanager.getEntityManagerInstance();
        em.getTransaction().begin();
        em.persist(product);
        em.flush();
        em.getTransaction().commit();

        assertNotNull(product.getId());
    }

    @After
    public void tearDown() throws Exception {
        QuestionService qs = new QuestionService();
        qs.deleteQuestion(product);
    }

}