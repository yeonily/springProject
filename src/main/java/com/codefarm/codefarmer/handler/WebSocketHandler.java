package com.codefarm.codefarmer.handler;

import com.codefarm.codefarmer.domain.chat.ChatDTO;
import com.codefarm.codefarmer.domain.chat.ChatRoomDTO;
import com.codefarm.codefarmer.entity.chat.Chat;
import com.codefarm.codefarmer.entity.chat.ChatRoom;
import com.codefarm.codefarmer.repository.chat.ChatRoomRepository;
import com.codefarm.codefarmer.service.chat.ChatRoomService;
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
    private final ChatRoomService chatRoomService;

    // 채팅방에서 어떠한 변화가 있을 때마다 전송되는 메세지를 출력..?
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = message.getPayload();
        ChatDTO chat = objectMapper.readValue(msg, ChatDTO.class);

        System.out.println("DTO메세지 : " + chat.toString());

        ChatRoomDTO chatRoom = chatRoomService.findByChatRoomId(48L); // 방 번호는 임시로 48번으로 설정
        chatRoom.handleMessage(session, chat, objectMapper);
    }
}