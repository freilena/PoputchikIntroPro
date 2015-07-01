package com.intropro.academy.kkalashnykova.poputchik;

import static org.junit.Assert.*;

import org.junit.Test;

public class DaoRideTest {

	@Test
	public void testSmoke() {
		DaoRide result = new DaoRide();
		result.getRideById(1);
	}
}
