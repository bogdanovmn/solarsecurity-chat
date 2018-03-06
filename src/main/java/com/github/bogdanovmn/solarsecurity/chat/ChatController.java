package com.github.bogdanovmn.solarsecurity.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
class ChatController {
	private static final String TOPIC__NEW_MESSAGE = "/topic/new-message";
	private static final String TOPIC__REFRESH_USERS_LIST = "/topic/refresh-users-list";
	private final ActiveUsers activeUsers;

	@Autowired
	ChatController(ActiveUsers activeUsers) {
		this.activeUsers = activeUsers;
	}

	@MessageMapping("/user-login")
	@SendTo(TOPIC__REFRESH_USERS_LIST)
	public RefreshUserListMessage userLogin(User user) {
		this.activeUsers.add(user);
		return new RefreshUserListMessage(
			ChatMessage.fromSystem("К чату присоединился новый участник: " + user),
			this.activeUsers
		);
	}

	@MessageMapping("/user-logout")
	@SendTo(TOPIC__REFRESH_USERS_LIST)
	public RefreshUserListMessage userLogout(User user) {
		this.activeUsers.remove(user);
		return new RefreshUserListMessage(
			ChatMessage.fromSystem("Чат покинул участник: " + user),
			this.activeUsers
		);
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
