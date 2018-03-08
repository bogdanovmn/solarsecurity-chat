package com.github.bogdanovmn.solarsecurity.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

class ChatErrorMessage {
	@JsonProperty
	private String text;

	ChatErrorMessage(String text) {
		this.text = text;
	}
}
