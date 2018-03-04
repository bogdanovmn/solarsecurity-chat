package com.github.bogdanovmn.solarsecurity.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
class ChatController {
	private static final String TOPIC__NEW_MESSAGE = "/topic/new-message";
	private final ActiveUsers activeUsers;

	@Autowired
	ChatController(ActiveUsers activeUsers) {
		this.activeUsers = activeUsers;
	}

	@MessageMapping("/user-login")
	@SendTo(TOPIC__NEW_MESSAGE)
	public ChatMessage userLogin(User user) {
		this.activeUsers.add(user);
		return ChatMessage.fromSystem("К чату присоединился новый участник: " + user);
	}

	@MessageMapping("/user-logout")
	@SendTo(TOPIC__NEW_MESSAGE)
	public ChatMessage userLogout(User user) {
		this.activeUsers.remove(user);
		return ChatMessage.fromSystem("Чат покинул участник: " + user);
	}

	@MessageMapping("/user-post")
	@SendTo(TOPIC__NEW_MESSAGE)
	public ChatMessage postMessage(UserPost post) {
		User user = this.activeUsers.get(post.getUserName());

		if (user == null) {
			throw new IllegalStateException("Unknown user");
		}

		return user.sayToChat(post.getText());
	}
}
