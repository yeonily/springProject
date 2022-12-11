package com.codefarm.codefarmer.controller.mento;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.chat.ChatDTO;
import com.codefarm.codefarmer.domain.chat.ChatRoomDTO;
import com.codefarm.codefarmer.entity.chat.Chat;
import com.codefarm.codefarmer.service.alba.AlbaListService;
import com.codefarm.codefarmer.service.chat.ChatRoomService;
import com.codefarm.codefarmer.service.chat.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/getChat/*")
public class ChatController {
    @Autowired
    private final ChatService chatService;
    @Autowired
    private final ChatRoomService chatRoomService;
    @Autowired
    private AlbaListService albaListService;

    /*다른 대화 선택 시 해당 방번호의 대화내역 불러오기*/
    @PostMapping("/test")
    @RequestMapping(value = "/getChat/test", method = RequestMethod.POST)
    public List<ChatDTO> test(@RequestParam("roomId") String roomId) throws Exception {
        Long chatRoomId = Long.parseLong(roomId);
        log.info("방 번호 : " + chatRoomId);
        return chatRoomService.chatList(chatRoomId);
    }
}
