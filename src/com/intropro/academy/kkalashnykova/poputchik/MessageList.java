package com.intropro.academy.kkalashnykova.poputchik;

import java.util.ArrayList;
import java.util.List;

public class MessageList {
	
	private List <Message> messagesSent = new ArrayList <Message>();
	//private List <Message> messagesReceived = new ArrayList <Message>();
	
	public Message sendMessage(Profile from, Profile to, String body){
		Message message = Message.createMessage(from, to, body);
		messagesSent.add(message);
		return message;
	}
	
	public List <Message> searchMessagesReceived(Profile to)
	{
		List <Message> result = new ArrayList<Message>();
		for(Message current : messagesSent)
		{
			if (current.getTo().equals(to)){
				result.add(current);
			}
		}
		if(result.isEmpty()){
			throw new PoputchikDomainObjectDoesnotExistException();
		}
		return result;
	}
	
	public List <Message> searchMessagesUnread(Profile to){
		List <Message> result = new ArrayList<Message>();
		for(Message current : messagesSent)
		{
			if (current.getTo().equals(to) && current.getStatus().equals("unread")){
				result.add(current);
			}
		}
		if(result.isEmpty()){
			throw new PoputchikDomainObjectDoesnotExistException();
		}
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((messagesSent == null) ? 0 : messagesSent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MessageList other = (MessageList) obj;
		if (messagesSent == null) {
			if (other.messagesSent != null)
				return false;
		} else if (!messagesSent.equals(other.messagesSent))
			return false;
		return true;
	}
	
	

}
