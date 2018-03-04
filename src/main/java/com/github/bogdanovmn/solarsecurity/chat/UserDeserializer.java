package com.github.bogdanovmn.solarsecurity.chat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

class UserDeserializer extends JsonDeserializer<User> {
	@Override
	public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
		JsonNode node = jsonParser.getCodec().readTree(jsonParser);
		return new User(
			node.get("name").asText(),
			node.get("colorId").asInt()
		);
	}
}
