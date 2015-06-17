package com.intropro.academy.kkalashnykova.poputchik;

import static org.junit.Assert.*;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

public class MessageListTest {
	
	@Test
	public void testSendMessage(){
		Profile from = new Profile();
		from.setFirstName("Test1");
		Profile to = new Profile();
		to.setFirstName("Test2");
		String body = "Message from " + from.getFirstName() + " to " + to.getFirstName();
		MessageList messages = new MessageList();
		Message message = messages.sendMessage(from, to, body);
		assertNotNull(message);
		assertEquals(body, message.getBody());
		assertEquals("Test1", message.getFrom().getFirstName());
		assertEquals("Test2", message.getTo().getFirstName());
		Date dateMessage = new Date();
		assertEquals(dateMessage, message.getDateTime());
		
	}

}
