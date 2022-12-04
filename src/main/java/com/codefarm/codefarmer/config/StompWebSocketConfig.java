package com.codefarm.codefarmer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker // Stomp를
@Configuration
public class StompWebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /*
    * registerStompEndpoints 메소드는 소켓 연결과 관련된 설정이며, addEndpoint()는 소켓 연결 URI이다. 소켓 연결은 아래와 같은 과정을 거친다.
    * Connect : 연결 요청을 거는 과정
    * Connected : 연결 성공
    * Error : 연결 실패
    * ----------------------------------------------------------------
    * setAllowedOrigins()는 모든 URI에서 chatHandler를 사용한다는 의미
    * */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp/chat")
                .setAllowedOrigins("https://localhost:5555") // 상황에 따라 *을 사용해보기
                .withSockJS(); // 웹소켓 사용이 불가능한 경우 SockJS를 사용
    }


    /*
    * configureMessageBroker 메소드는 Stomp를 사용하기 위한 Message Broker를 설정해주는 메소드이다.
    * setApplicationDestinationPrefixes : 메세지를 보낼 때 관련 경로를 설정해주는 함수, 클라이언트가 메세지를 보낼 때 경로 앞에 "/~"이 붙어있으면 Broker로 보내진다.
    * enableSimpleBroker : 메세지를 받을 때 경로를 설정해주는 함수, 스프링에서 제공해주는 내장 브로커를 사용하는 함수이며, 1:1, 1:N 등을 설정해준다.
    * */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/pub");
        registry.enableSimpleBroker("/sub");
    }
}
