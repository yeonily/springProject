package com.codefarm.codefarmer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// Stomp를 사용하기위해 선언하는 어노테이션
@EnableWebSocketMessageBroker
@Configuration
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    // endpoint를 /stomp로 하고, allowedOrigins를 "*"로 하면 페이지에서 Get /info 404 Error가 발생
    // 아래와 같이 2개의 계층으로 분리하니 잘 동작.
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // StompEndpointRegistry : 핸드셰이크를 하기 위해 endpoint 지정, 해당 경로가 true인 경우 웹소켓 연결
        registry.addEndpoint("/stomp/chat")
                .setAllowedOrigins("http://localhost:5555")
                .withSockJS();
    }

    /*어플리케이션 내부에서 사용할 path를 지정할 수 있음*/
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // Client 에서 SEND 요청을 처리
        // Spring docs 에서는 /topic, /queue로 나오나 편의상 /pub, /sub로 변경
        registry.setApplicationDestinationPrefixes("/pub");


        // 해당 경로로 SimpleBroker를 등록.
        // SimpleBroker는 해당하는 경로를 SUBSCRIBE하는 Client에게 메세지를 전달하는 간단한 작업을 수행
        // enableStompBrokerRelay : SimpleBroker의 기능과 외부 Message Broker( RabbitMQ, ActiveMQ 등 )에 메세지를 전달하는 기능을 가짐
        registry.enableSimpleBroker("/sub");
    }
}
