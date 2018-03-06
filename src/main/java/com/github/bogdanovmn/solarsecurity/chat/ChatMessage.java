package com.github.bogdanovmn.solarsecurity.chat;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

class ChatMessage {
	private static final int SYSTEM_COLOR = 0;

	private final String msg;
	@JsonProperty
	private final int colorId;
	private final Instant timestamp;

	ChatMessage(String msg, int colorId) {
		this.msg = msg;
		this.colorId = colorId;
		this.timestamp = Instant.now();
	}

	static ChatMessage fromSystem(String text) {
		return new ChatMessage(text, SYSTEM_COLOR);
	}

	@JsonProperty
	String getText() {
		String time = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
			.withZone(ZoneId.systemDefault())
			.format(this.timestamp);

		return String.format(
			"[%s] %s",
				time, this.msg
		);
	}
}
