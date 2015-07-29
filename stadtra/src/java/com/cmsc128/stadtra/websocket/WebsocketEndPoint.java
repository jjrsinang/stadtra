package com.cmsc128.stadtra.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@MessageMapping("/requests/notify")
public class WebsocketEndPoint extends TextWebSocketHandler {

	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		TextMessage returnMessage = new TextMessage(message.getPayload());
		session.sendMessage(returnMessage);
	}
}