package com.github.bogdanovmn.solarsecurity.chat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

class User {
	@JsonProperty
	private final String name;
	@JsonProperty
	private final int colorId;

	@JsonCreator
	User(
		@JsonProperty("name") String name,
		@JsonProperty("colorId") int colorId)
	{
		this.name = name;
		this.colorId = colorId;
	}

	ChatMessage sayToChat(String text) {
		return new ChatMessage(
			String.format(
				"%s: %s", this.name, text
			),
			this.colorId
		);
	}

	@Override
	public String toString() {
		return this.name;
	}
}
