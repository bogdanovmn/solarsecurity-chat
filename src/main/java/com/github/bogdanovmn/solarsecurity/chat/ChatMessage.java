package com.github.bogdanovmn.solarsecurity.chat;

import java.time.Instant;

class ChatMessage {
	private static final int SYSTEM_COLOR = 0;

	private final String text;
	private final int colorId;
	private final Instant timestamp;

	ChatMessage(String text, int colorId) {
		this.text = text;
		this.colorId = colorId;
		this.timestamp = Instant.now();
	}

	static ChatMessage fromSystem(String text) {
		return new ChatMessage(text, SYSTEM_COLOR);
	}
}
