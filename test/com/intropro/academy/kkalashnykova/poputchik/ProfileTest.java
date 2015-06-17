package com.intropro.academy.kkalashnykova.poputchik;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

public class ProfileTest {

	@Test
	public void testSmoke() {
		Profile profile = new Profile();
		assertNotNull(profile);
	}

}
