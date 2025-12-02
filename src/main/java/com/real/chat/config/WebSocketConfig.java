package com.real.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // For broadcasting messages to all subscribed users
        config.enableSimpleBroker("/topic");

        // Prefix for messages from client to server (like @MessageMapping endpoints)
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // Endpoint for WebSocket handshake
        registry.addEndpoint("/chat")
                .setAllowedOriginPatterns("*")  // allow all origins (for local + future deployment)
                .withSockJS(); // fallback support for browsers not supporting WebSocket
    }
}
