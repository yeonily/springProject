package com.codefarm.codefarmer.controller.mento;


import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.service.chat.ChatRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
@RequestMapping("/mento/*")
public class MentoController {
    private final ChatRoomService cs;

    public MentoController(ChatRoomService chatRoomService) {
        this.cs = chatRoomService;
    }

    @GetMapping("/intro")
    public void mentoIntro(){

    }

    @GetMapping("/list")
    public void list(){

    }
    @GetMapping("/detail")
    public void detail(){

    }
    @GetMapping("/write")
    public void write(){

    }

    /*@GetMapping("/chatting")
    public void chatting(){

    }*/

    /*채팅방 이동 시*/
    @GetMapping("/chatting")
    public void chatting(Model model) {
        Member member = cs.findByMemberId(1L); // 로그인 멤버 세션 대체용

        /*로그인 멤버 세션이 참여 중인 대화방 목록 저장*/
        model.addAttribute("rooms", cs.chatRoomSelectAll(1L));
        /*대화방 클릭 시 해당 방의 대화기록 불러오기*/
        model.addAttribute("chats", cs.chatList(12L));
        /*로그인 세션에 따른 읽지 않은 메세지 개수 가져오기*/
        model.addAttribute("alarmCnt", cs.chatAlarm(1L)); // 메세지 개수의 경우 이후 세션으로 등록 필요(모든 페이지에서 쓰기 위함)
    }
}
