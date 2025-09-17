package com.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.chat.AppConstants;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer
{
@Override
public void configureMessageBroker(MessageBrokerRegistry registry)
{
	// TODO Auto-generated method stub
	registry.enableSimpleBroker("/topic");
	registry.setApplicationDestinationPrefixes("/app");
}

@Override
	public void registerStompEndpoints(StompEndpointRegistry registry)
{
		// TODO Auto-generated method stub
        registry.addEndpoint("/chat").setAllowedOrigins(AppConstants.FRONT_END).withSockJS();
}
}
