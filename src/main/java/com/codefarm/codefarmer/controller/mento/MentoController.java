package com.codefarm.codefarmer.controller.mento;


import com.codefarm.codefarmer.service.chat.ChatRoomService;
import com.codefarm.codefarmer.service.mentor.MentorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/mento/*")
public class MentoController {
    private final ChatRoomService cs;
    private final MentorService ms;

    public MentoController(ChatRoomService cs, MentorService ms) {
        this.cs = cs;
        this.ms = ms;
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
        model.addAttribute("mentorId", ms.showDetailMentorBoard(65L).getMentorId());
    }


    @GetMapping("/write")
    public void write(){

    }

    /*채팅방 이동 시*/
    @GetMapping("/chatting")
    @RequestMapping(value = "/mento/chatting", method = RequestMethod.GET)
    public void chatting(Model model, Long mentorId) {
        Long sessionId = 13L; // 로그인은 현재 13번 일반 회원으로 되어 있다고 가정

        /*로그인 세션 변수로 보내기*/
        model.addAttribute("sessionId", sessionId);


        /*대화가 이미 있는지에 따라 채팅방 생성*/
        cs.createChatRoom(mentorId, sessionId); // 게시글을 작성한 멘토 멤버아이디와 로그인 세션
        /*로그인 멤버 세션이 참여 중인 대화방 목록 저장*/
        model.addAttribute("rooms", cs.chatRoomSelectAll(sessionId));

        System.out.println("멘토이름 : " + cs.chatRoomSelectAll(sessionId).get(0).getMentor().getMemberId());
        System.out.println("멘티이름 : " + cs.chatRoomSelectAll(sessionId).get(0).getMentee().getMemberId());

        /*대화방 클릭 시 해당 방의 대화기록 불러오기*/
        model.addAttribute("chats", cs.chatList(12L));
        /*로그인 세션에 따른 읽지 않은 메세지 개수 가져오기*/
        model.addAttribute("alarmCnt", cs.chatAlarm(sessionId)); // 메세지 개수의 경우 이후 세션으로 등록 필요(모든 페이지에서 쓰기 위함)
    }
}
