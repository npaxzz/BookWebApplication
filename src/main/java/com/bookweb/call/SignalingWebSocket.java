package com.bookweb.call;

import java.util.concurrent.CopyOnWriteArraySet;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class SignalingWebSocket extends TextWebSocketHandler {

	private final CopyOnWriteArraySet<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		sessions.add(session);
	}

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// ส่งข้อความไปให้ทุก session ยกเว้นตัวเอง
		for (WebSocketSession s : sessions) {
			if (!s.getId().equals(session.getId())) {
				s.sendMessage(message);
			}
		}
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, org.springframework.web.socket.CloseStatus status)
			throws Exception {
		sessions.remove(session);
	}
}
