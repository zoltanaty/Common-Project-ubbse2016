package com.halcyonmobile.model;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import com.halcyonmobile.persistence.Entitymanager;

public class PrivilegesTest {

	private Privileges priv;
	
	@Before
	public void setUp() {
		priv = new Privileges();
	}
	
	@Test
	public void getNameprivileges_NameIsSetted_TheGivenName() {
		priv.setNameprivileges("test");
		assertEquals("test", priv.getNameprivileges());
	}
	
	@Test
	public void setNameprivileges_NameIsPreviouslySetted_TheNewName() {
		priv.setNameprivileges("test1");
		priv.setNameprivileges("test2");
		
		assertEquals("test2", priv.getNameprivileges());
	}
}
