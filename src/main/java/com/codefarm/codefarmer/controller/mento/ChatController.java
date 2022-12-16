package com.codefarm.codefarmer.controller.mento;

import com.codefarm.codefarmer.domain.chat.ChatDTO;
import com.codefarm.codefarmer.service.chat.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/getChat/*")
public class ChatController {
    @Autowired
    private final ChatRoomService chatRoomService;

    /*다른 대화 선택 시 해당 방번호의 대화내역 불러오기*/
    @PostMapping("/history")
    @RequestMapping(value = "/getChat/history", method = RequestMethod.POST)
    public List<ChatDTO> test(@RequestParam("roomId") String roomId, HttpSession session) throws Exception {
        Long chatRoomId = Long.parseLong(roomId);
        Long sessionId = (Long) session.getAttribute("memberId");
        log.info("방 번호 : " + chatRoomId);
        chatRoomService.readChange(chatRoomId, sessionId); // 해당방에 접속 시 상대방이 보낸 메세지 읽음 표시
        return chatRoomService.chatList(chatRoomId);
    }

    /*로그인 세션에 따라 대화방 내역을 조회하여 읽지 않은 메세지 조회*/
    @PostMapping("/alarm")
    public List<ChatDTO> alarm(HttpSession session) throws Exception {
        Long sessionId = (Long) session.getAttribute("memberId");
        System.out.println("현재세션 : " + sessionId);
        System.out.println("채팅결과 : " + chatRoomService.chatAlarm(sessionId));

        return chatRoomService.chatAlarm(sessionId);
    }
}
