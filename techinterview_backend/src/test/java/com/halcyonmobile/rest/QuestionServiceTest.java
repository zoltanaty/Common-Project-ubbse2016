package com.halcyonmobile.rest;

import com.halcyonmobile.model.Question;
import com.halcyonmobile.persistence.Entitymanager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

public class QuestionServiceTest {
    private static int ID = 9999;
    private static String QUESTION = "This is a question";
    private int id_questionType, id_position;
    private Question product;

    @Before
    public void setUp() throws Exception {
        PositionService ps = new PositionService();
        id_position = ps.findAll().get(0).getId();
        QuestionTypeService qts = new QuestionTypeService();
        id_questionType = qts.findAll().get(0).getId();

        product = new Question();
        product.setId(ID);
        product.setQuestion(QUESTION);
        product.setId_questiontype(id_questionType);
        product.setId_position(id_position);

        EntityManager em = Entitymanager.getEntityManagerInstance();
        em.getTransaction().begin();
        em.persist(product);
        em.flush();
        em.getTransaction().commit();
    }

    @After
    public void tearDown() throws Exception {
        QuestionService qs = new QuestionService();
        qs.deleteQuestion(product);
    }

    @Test
    public void findAll() throws Exception {
        QuestionService qs = new QuestionService();

        assertNotEquals(0, qs.findAll().size());
    }

    @Test
    public void findByPosition() throws Exception {
        QuestionService qs = new QuestionService();
        assertNotEquals(0, qs.findByPosition(id_position).size());
    }

    @Test
    public void insertQuestion() throws Exception {
        QuestionService qs = new QuestionService();
        int idQuestion = qs.insertQuestion(QUESTION + " TEMP", id_questionType, id_position);
        assertNotEquals(0, idQuestion);
        if (idQuestion != 0) {
            Question newQuestion = qs.findById(idQuestion);
            qs.deleteQuestion(newQuestion);
        }
    }

    @Test
    public void findIdByQuestion() throws Exception {
        QuestionService qs = new QuestionService();
        assertNotEquals(0, qs.findIdByQuestion(QUESTION));
    }

    @Test
    public void findById() throws Exception {
        QuestionService qs = new QuestionService();
        assertNotNull(qs.findById(ID));
    }

    @Test
    public void deleteQuestion() throws Exception {
        Question product = new Question();
        product.setId(ID + 1);
        product.setQuestion(QUESTION);
        product.setId_questiontype(id_questionType);
        product.setId_position(id_position);

        EntityManager em = Entitymanager.getEntityManagerInstance();
        em.getTransaction().begin();
        em.persist(product);
        em.flush();
        em.getTransaction().commit();

        QuestionService qs = new QuestionService();
        qs.deleteQuestion(product);
        assertNull(qs.findById(ID + 1));
    }

}