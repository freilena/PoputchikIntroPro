package com.intropro.academy.kkalashnykova.poputchik;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MessageList {

	private List<Message> messagesSent = new ArrayList<Message>();
	private static Logger log = LogManager.getLogger(RideList.class);

	public Message sendMessage(Profile from, Profile to, String body) {
		log.info("sendMessage() started.");
		Message message = Message.createMessage(from, to, body);
		messagesSent.add(message);
		log.info("Message successfully sent.");
		return message;
	}

	public List<Message> searchMessagesReceived(Profile to) throws PoputchikDoesNotExistException {
		log.info("searchMessagesReceived() started.");
		List<Message> result = new ArrayList<Message>();
		for (Message current : messagesSent) {
			if (current.getTo().equals(to)) {
				result.add(current);
			}
		}
		if (result.isEmpty()) {
			log.error("Error in searchMessagesReceived(): Failed to searchMessagesReceived: There are no messages for profile " + to.getFirstName() + " " + to.getLastName() + ".");
			throw new PoputchikDoesNotExistException("Failed to searchMessagesReceived: There are no messages for profile " + to.getFirstName() + " " + to.getLastName() + ".");
		}
		log.info("searchMessagesReceived() finished. Messages received found.");
		return result;
	}

	public List<Message> searchMessagesUnread(Profile to) throws PoputchikDoesNotExistException {
		log.info("searchMessagesUnread() started.");
		List<Message> result = new ArrayList<Message>();
		for (Message current : messagesSent) {
			if (current.getTo().equals(to) && current.getStatus().equals(MessageStatus.unread)) {
				result.add(current);
			}
		}
		if (result.isEmpty()) {
			log.error("Error in searchMessagesUnread(): Failed to searchMessagesUnread: There are no messages for profile " + to.getFirstName() + " " + to.getLastName() + ".");
			throw new PoputchikDoesNotExistException("Failed to searchMessagesUnread: There are no unread messages for profile " + to.getFirstName() + " " + to.getLastName() + ".");
		}
		log.info("searchMessagesUnread() finished. Messages unread found.");
		return result;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((messagesSent == null) ? 0 : messagesSent.hashCode());
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
