package com.github.bogdanovmn.solarsecurity.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

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

	@JsonProperty("activeUsers")
	List<User> getAllUsers() {
		return users.keySet().stream()
			.sorted()
			.map(this.users::get)
			.collect(Collectors.toList());
	}

	boolean contains(User user) {
		return this.users.containsKey(user.toString());
	}
}
