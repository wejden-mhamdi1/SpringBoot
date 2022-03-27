package tn.esprit.spring.Entities;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class ChatMessage {
	private String content;
	private String sender;


	 @Enumerated(EnumType.STRING)
	 MessageType Messagetype;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}




}
