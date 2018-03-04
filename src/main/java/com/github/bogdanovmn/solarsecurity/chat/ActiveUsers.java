package com.github.bogdanovmn.solarsecurity.chat;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
class ActiveUsers {
	private final Map<String, User> users = new ConcurrentHashMap<>();

	void add(User user) {
		this.users.put(user.toString(), user);
	}

	User get(String userName) {
		return this.users.get(userName);
	}

	void remove(User user) {
		this.users.remove(user.toString());
	}
}
