package com.github.bogdanovmn.solarsecurity.chat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class UserPost {
	private final String userName;
	private final String text;

	@JsonCreator
	UserPost(
		@JsonProperty("userName") String userName,
		@JsonProperty("text") String text
	) {
		this.userName = userName;
		this.text = text;
	}

	String getUserName() {
		return this.userName;
	}

	String getText() {
		return this.text;
	}
}
