package com.intropro.academy.kkalashnykova.poputchik;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class RideListTest {
	
	//чтобы сделать прекондишены
	// создаем поле у класса RideTest
	//и туда или в несколько таких полей заполняем тестовые данные с помощью	
	//@before - перед каждым методом or setUp метод 
	//
	@Before
	public void createTestData(){
		
	}

	@Test
	public void testCreateRide() {
		
		Date date = new Date();
		Profile owner = new Profile();
		RideList rideList = new RideList();
		
		//check add one
		Ride ride = rideList.createRide("Cristall", "Grygorenka", date, owner);
		assertNotNull(ride);
		assertTrue(!rideList.getRides().isEmpty());
		assertEquals(ride, rideList.getRides().get(0));
		
		//check add two 
		Ride ride2 = rideList.createRide("Grygorenka", "Cristall", date, owner);
		assertEquals(rideList.getRides().size(), 2);
		assertEquals(ride2, rideList.getRides().get(1));
		
		//check add same as already existed
		try{
			rideList.createRide("Grygorenka", "Cristall", date, owner);
		}
		catch(PoputchikDomainRideAlreadyExistsException e){
			assertNotNull(e);
		}
		assertEquals(2, rideList.getRides().size());
		
	}
	
	@Test
	public void testDeleteRide() {
		Date date = new Date();
		Profile owner = new Profile();
		RideList rideList = new RideList();
		
		//check 
		Ride ride = rideList.createRide("Cristall", "Grygorenka", date, owner);
		assertTrue(!rideList.getRides().isEmpty());
		assertTrue(rideList.deleteRide(ride));
		assertTrue(rideList.getRides().isEmpty());
		
		assertTrue(!rideList.deleteRide(ride));// check somehow that you can't delete same object two times and no uncaughted exceptions
	}
	
	@Test
	public void testSearchRide() {
		
		Date date = new Date();
		Date date2 = new Date();
		Date date3 = new Date();
		//String z = date.toString();
		
		Profile owner = new Profile();
		owner.setFirstName("Test1");
		Profile owner2 = new Profile();
		owner2.setFirstName("Test2");
		Profile owner3 = new Profile();
		owner3.setFirstName("Test3");
		
		RideList rides = new RideList();
		rides.createRide("Cristall", "Grygorenka", date, owner);
		rides.createRide("Grishka", "Cristall", date, owner);

		//HOW TO GET NOT SAME DATE		
		//String z2 = date.toString();
		rides.createRide("Cristall", "Grygorenka", date2, owner2);//now fails because two with same start and finish and date could not be created
		rides.createRide("Grygorenka", "Cristall", date, owner);
		assertTrue(!rides.getRides().isEmpty());
		
		assertEquals(1, rides.search("Grishka", "Cristall", date, owner).size());
		assertEquals(1, rides.search("Cristall", "Grygorenka", null, owner2).size());
		//assertEquals(1, rides.search("Cristall", "Grygorenka", date2, null).size()); //fails because dates are same
		assertEquals(2, rides.search("Cristall", "Grygorenka", null, null).size());
		assertEquals(2, rides.search("Cristall", null, null, null).size());
		assertEquals(2, rides.search(null, "Grygorenka", null, null).size());
		assertEquals(0, rides.search("A", "Cristall", date, owner).size());
		assertEquals(0, rides.search("Grishka", "B", date, owner).size());
		//assertEquals(0, rides.search("Grishka", "Cristall", date3, owner).size()); //fails because dates are same
		assertEquals(0, rides.search("Grishka", "Cristall", date, owner3).size());
		assertEquals(rides.getRides().size(), rides.search(null, null, null, null).size());
		
		//добавить
		//поиск по нескольким параметрам (searchParameters) - для этих серч параметров использовать Enum. может и для старт и финиш
		//enum
		//в отдельном файле создаем
		//public enum Weekday{, где вик дей это тип
		//MON
		//TUE
		//} 
		
	}	
}