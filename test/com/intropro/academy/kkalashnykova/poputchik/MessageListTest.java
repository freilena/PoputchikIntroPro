package com.intropro.academy.kkalashnykova.poputchik;

import static org.junit.Assert.*;

import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MessageListTest {
	
	private Profile from;
	private Profile to;
	private Message message;
	private Message message2;
	private String body;
	private String body2;
	private MessageList messages;
	
	@Before
	public void setUp() throws Exception {
		from = new Profile();
		from.setFirstName("Test1");
		to = new Profile();
		to.setFirstName("Test2");

		messages = new MessageList();
		body = "Message from " + from.getFirstName() + " to " + to.getFirstName();
		message = messages.sendMessage(from, to, body);
		
		body2 = "Message2 from " + from.getFirstName() + " to " + to.getFirstName();
		message2 = messages.sendMessage(to, from, body2);
	}
	
	@Test
	public void testSendMessage(){
		assertNotNull(message);
		assertEquals(body, message.getBody());
		assertEquals("Test1", message.getFrom().getFirstName());
		assertEquals("Test2", message.getTo().getFirstName());
	}
	
	@Test
	public void testSearchMessagesReceived(){
		try {
			assertEquals(1, messages.searchMessagesReceived(to).size());
		} catch (PoputchikDoesNotExistException e) {
			fail("testSearchMessagesReceived Should not throw exeption");
		}
		
		try {
			assertEquals(1, messages.searchMessagesReceived(from).size());
		} catch (PoputchikDoesNotExistException e) {
			fail("testSearchMessagesReceived Should not throw exeption");
		}
		
		Profile to2 = new Profile();
		try {
			messages.searchMessagesReceived(to2).size();
			fail("Should throw PoputchikDoesNotExistException exeption");
		} catch (PoputchikDoesNotExistException e) {
		}
		
		to.setFirstName("Test3");
		try {
			messages.searchMessagesReceived(to).size();
			fail("Should throw PoputchikDoesNotExistException exeption");
		} catch (PoputchikDoesNotExistException e) {
		}
	
	}
	
	@Test
	public void testSearchMessagesUnread(){
		try {
			assertEquals(1, messages.searchMessagesUnread(to).size());
		} catch (PoputchikDoesNotExistException e) {
			fail("testSearchMessagesUnread Should not throw exeption");
		}
		
		try {
			assertEquals(1, messages.searchMessagesUnread(from).size());
		} catch (PoputchikDoesNotExistException e) {
			fail("testSearchMessagesUnread Should not throw exeption");
		}
		
		message.setStatus(MessageStatus.read);
		try {
			messages.searchMessagesUnread(to).size();
			fail("Should throw PoputchikDoesNotExistException exeption");
		} catch (PoputchikDoesNotExistException e) {
		}
		
		
	}

}
