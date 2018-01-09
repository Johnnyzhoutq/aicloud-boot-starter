package com.aicloud.entity;

public class AicloudMessage {

	private String text;
	
	public AicloudMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AicloudMessage(String text){
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
