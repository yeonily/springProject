package com.codefarm.codefarmer.controller.mento;


import com.codefarm.codefarmer.domain.chat.ChatDTO;
import com.codefarm.codefarmer.entity.chat.Chat;
import com.codefarm.codefarmer.repository.chat.ChatRepository;
import com.codefarm.codefarmer.service.chat.ChatRoomService;
import com.codefarm.codefarmer.service.mentor.MentorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/mento/*")
public class MentoController {
    private final ChatRoomService cs;
    private final MentorService ms;
    private final ChatRepository chatRepository;
    private final SimpMessagingTemplate template;

    public MentoController(ChatRoomService cs, MentorService ms, ChatRepository chatRepository, SimpMessagingTemplate template) {
        this.cs = cs;
        this.ms = ms;
        this.chatRepository = chatRepository;
        this.template = template;
    }



    @GetMapping("/intro")
    public void mentoIntro(){

    }

    @GetMapping("/list")
    public void list(){

    }


    @GetMapping("/detail")
    public void detail(Model model){
        /*선택한 게시글의 번호로 글의 정보를 program JSON 형식으로 전송*/
        model.addAttribute("mentorId", ms.showDetailMentorBoard(53L).getMentorId());
    }


    @GetMapping("/write")
    public void write(){

    }

    /*채팅방 이동 시*/
    @GetMapping("/chatting")
    @RequestMapping(value = "/mento/chatting", method = RequestMethod.GET)
    public void chatting(Model model, Long mentorId, HttpSession session) {
        Long sessionId = (Long) session.getAttribute("memberId"); // 로그인은 현재 13번 일반 회원으로 되어 있다고 가정
//        Long sessionId = 1L;

        System.out.println("결과 : " + cs.chatRoomSelectAll(sessionId));


        /*로그인 세션 변수로 보내기*/
        model.addAttribute("sessionId", sessionId);

        /*대화가 이미 있는지에 따라 채팅방 생성*/
        cs.createChatRoom(mentorId, sessionId); // 게시글을 작성한 멘토 멤버아이디와 로그인 세션
        /*로그인 멤버 세션이 참여 중인 대화방 목록 저장*/
        model.addAttribute("rooms", cs.chatRoomSelectAll(sessionId));

        /*대화방 클릭 시 해당 방의 대화기록 불러오기*/
        model.addAttribute("chats", cs.chatList(12L));
        /*로그인 세션에 따른 읽지 않은 메세지 개수 가져오기*/
        model.addAttribute("alarmCnt", cs.chatAlarm(sessionId)); // 메세지 개수의 경우 이후 세션으로 등록 필요(모든 페이지에서 쓰기 위함)
    }

    /*채팅방 선택 시 대화 이력 불러오기*/
    @MessageMapping(value = "/chatting/enter")
    public void enter(ChatDTO message) {
        List<ChatDTO> chatList = cs.chatList(message.getRoomId()); // 매개변수로 받은 객체에 저장된 대화목록

        System.out.println("--------채팅 불러오기 로딩 완료--------");


        /*대화방 유무에 따른 메세지 처리*/
        if(!chatList.isEmpty()) {
            for (ChatDTO chatDTO : chatList) {
                template.convertAndSend("/sub/mento/chatting" + chatDTO.getChatRoom().getChatRoomId(), chatDTO);
            }
        }
    }

    /*메세지 보냄*/
    @MessageMapping(value = "/chatting/message")
    public void message(ChatDTO message) {
        System.out.println("결과 : " + message.toString());
        template.convertAndSend("/sub/mento/chatting" + message.getRoomId(), message);
        cs.sendMessage(message); // 입력한 메세지를 DB로 보냄
    }
}
















