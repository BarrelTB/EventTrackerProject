package com.skilldistillery.eventtracker.entities;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class StrainTest {

	private static EntityManagerFactory emf;
	private EntityManager em;
	private Strain strain;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		emf = Persistence.createEntityManagerFactory("SeshPU");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		emf.close();
	}

	@BeforeEach
	void setUp() throws Exception {
		em = emf.createEntityManager();
		strain = em.find(Strain.class, 1);
	}

	@AfterEach
	void tearDown() throws Exception {
		em.close();
		strain = null;
	}

	@Disabled
	@Test
	void test() {
		fail("Not yet implemented");
	}
	@Test
	void test1() {
		assertEquals("OG Kush", strain.getName());
	}

}
