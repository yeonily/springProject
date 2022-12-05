package com.codefarm.codefarmer.controller.mento;


import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.service.chat.ChatRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/mento/*")
public class MentoController {
    private final ChatRoomService chatRoomService;

    public MentoController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
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
    public void chatting(Model model){
        Member member = chatRoomService.findByMemberId(1L);
        System.out.println("결과 : " + chatRoomService.chatRoomSelectAll(1L).get(0).getMentor().getMemberNickname());
        model.addAttribute("rooms", chatRoomService.chatRoomSelectAll(1L));
    }
}
