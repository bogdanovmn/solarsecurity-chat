package com.github.bogdanovmn.solarsecurity.chat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = UserDeserializer.class)
class User {
	private final String name;
	private final int colorId;

	User(String name, int colorId) {
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
