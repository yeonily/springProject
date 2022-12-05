package com.codefarm.codefarmer.handler;

import com.codefarm.codefarmer.entity.chat.Chat;
import com.codefarm.codefarmer.entity.chat.ChatRoom;
import com.codefarm.codefarmer.repository.chat.ChatRoomRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class WebSocketHandler extends TextWebSocketHandler {
    private final ChatRoomRepository chatRoomRepository; // 레퍼지토리 생성
    private final ObjectMapper objectMapper; // 웹소켓을 통해 받은 JSON형식의 데이터를 ChatMessage 클래스에 맞게 파싱하여 ChatMessage 객체로 변환

    // 채팅방에서 어떠한 변화가 있을 때마다 전송되는 메세지를 출력..?
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("메세지 전송 = {} : {}", session, message.getPayload()); // 앞 중괄호에는 웹소켓 세션, 뒤 중괄호에는 채팅에 대한 정보가 담김
        String msg = message.getPayload();
        Chat chat = objectMapper.readValue(msg, Chat.class);
        Optional<ChatRoom> chatRoom = chatRoomRepository.findById(chat.getChatRoom().getChatRoomId());
    }
}