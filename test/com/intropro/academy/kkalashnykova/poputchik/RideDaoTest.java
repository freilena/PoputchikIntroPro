package com.intropro.academy.kkalashnykova.poputchik;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class RideDaoTest {
	
	private static Logger log = LogManager.getLogger(RideList.class);
	
	@Test
	public void testSmoke() {
		
		RideDao result = new RideDao();
		assertEquals("Grigorenka", result.getRideById(2));
		try{
			assertEquals("Grigorenka", result.getRideById(3));
		}catch(PoputchikDaoFailedToRead e){
			log.error("Failed to get Ride by ID.", e);
		}
	}
}
