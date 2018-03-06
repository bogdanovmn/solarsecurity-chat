package com.github.bogdanovmn.solarsecurity.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

class RefreshUserListMessage {
	@JsonProperty
	private final ChatMessage chatMessage;
	@JsonProperty
	@JsonUnwrapped
	private final ActiveUsers activeUsers;

	RefreshUserListMessage(ChatMessage chatMessage, ActiveUsers activeUsers) {
		this.chatMessage = chatMessage;
		this.activeUsers = activeUsers;
	}
}
