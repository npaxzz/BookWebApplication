package com.bookweb.call;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CallWebSocketHandler extends TextWebSocketHandler {

	// roomId -> list of sessions
	private final Map<String, Set<WebSocketSession>> rooms = new ConcurrentHashMap<>();

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Map<String, Object> msg = mapper.readValue(message.getPayload(), Map.class);
		String roomId = (String) msg.get("roomId");

		rooms.putIfAbsent(roomId, ConcurrentHashMap.newKeySet());
		Set<WebSocketSession> peers = rooms.get(roomId);
		peers.add(session);

		// ส่งข้อความ peer ไปทุกคนใน room ยกเว้นตัวเอง
		for (WebSocketSession s : peers) {
			if (!s.equals(session)) {
				s.sendMessage(message);
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		rooms.values().forEach(set -> set.remove(session));
	}
}
