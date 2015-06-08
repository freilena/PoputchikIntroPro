package com.intropro.academy.kkalashnykova.poputchik;

import static org.junit.Assert.*;

import org.junit.Test;

public class MessageTest {

	@Test
	public void testSmoke() {
		Message message = new Message();
		assertNotNull(message);
	}

}
