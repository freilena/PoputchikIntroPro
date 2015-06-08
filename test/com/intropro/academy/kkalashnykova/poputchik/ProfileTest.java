package com.intropro.academy.kkalashnykova.poputchik;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProfileTest {

	@Test
	public void testSmoke() {
		Profile profile = new Profile();
		assertNotNull(profile);
	}

}
