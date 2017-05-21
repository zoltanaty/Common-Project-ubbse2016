package com.halcyonmobile.integration;

import com.halcyonmobile.model.QuestionType;
import com.halcyonmobile.rest.QuestionTypeService;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.Assert.*;

public class QuestionTypeServiceTest {
	public static QuestionTypeService questionTypeService = new QuestionTypeService();
	public static InMemoryRestServer server;

	@BeforeClass
	public static void beforeClass() throws Exception {
		server = InMemoryRestServer.create(questionTypeService);
	}

	@AfterClass
	public static void afterClass() throws Exception {
		server.close();
	}

	@Test
	public void getAllTest() {
		Response response = server.newRequest("/questiontype/").request().buildGet().invoke();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

		List<QuestionType> questionTypeList = response.readEntity(new GenericType<List<QuestionType>>(){});
		for (QuestionType questionType:questionTypeList) {
			assertNotNull(questionType.getId());
			assertNotEquals(0, questionType.getId());
		}

		response.close();
	}

	@Test
	public void findByIdTest() {
		Response response = server.newRequest("/questiontype/1").request().buildGet().invoke();
		assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
		QuestionType questionType = response.readEntity(QuestionType.class);

		assertNotNull(questionType);
		assertEquals(questionTypeService.findById(1).toString(), questionType.toString());
		response.close();
	}


}