package com.github.bogdanovmn.solarsecurity.chat;

class UserPost {
	private final String userName;
	private final String text;

	UserPost(String userName, String text) {
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
