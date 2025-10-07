package com.bookweb.call;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

	private final SignalingWebSocket signalingWebSocket;

	public WebSocketConfig(SignalingWebSocket signalingWebSocket) {
		this.signalingWebSocket = signalingWebSocket;
	}

	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(signalingWebSocket, "/ws").setAllowedOrigins("*");
	}
}
