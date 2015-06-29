package com.intropro.academy.kkalashnykova.poputchik;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestLog4j {
	
	private Logger log = LogManager.getLogger(TestLog4j.class);

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		log.error("Test fgdfgfd");
	}

}
