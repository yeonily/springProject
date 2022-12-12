package com.codefarm.codefarmer.controller.mento;

import com.codefarm.codefarmer.domain.chat.ChatDTO;
import com.codefarm.codefarmer.service.chat.ChatRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class StompController {
    private final ChatRoomService cs;
    private final SimpMessagingTemplate template;

    /*채팅방 선택 시 대화 이력 불러오기*/
    @MessageMapping(value = "/chatting/enter")
    public void enter(ChatDTO message) {
        List<ChatDTO> chatList = cs.chatList(message.getRoomId()); // 매개변수로 받은 객체에 저장된 대화목록을 DB에서 가져옴

        /*대화방 유무에 따른 메세지 처리*/
        if (!chatList.isEmpty()) {
            for (ChatDTO chatDTO : chatList) {
                // /chatting으로 채팅방의 대화내역을 보냄
                System.out.println("채팅내용 : " + chatDTO.toString());
                template.convertAndSend("/sub/mento/chatting/" + chatDTO.getChatRoom().getChatRoomId(), chatDTO);
            }
//            message.setChatMessage("환영합니다!");
//            System.out.println("결과 : " + message.toString());
//
//            template.convertAndSend("/sub/mento/chatting" + message.getRoomId(), message);
        }
    }


    /*메세지 보냄*/
    @MessageMapping(value = "/chatting/message")
    public void message(ChatDTO message) {
        LocalDateTime now = LocalDateTime.now();
        message.setChatDate(now);
        System.out.println("출력 : " + message.getRoomId());
        template.convertAndSend("/sub/mento/chatting/" + message.getRoomId(), message);
        cs.sendMessage(message); // 입력한 메세지를 DB로 보냄
    }
}









