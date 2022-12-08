package com.codefarm.codefarmer.service.chat;

import com.codefarm.codefarmer.domain.chat.ChatDTO;
import com.codefarm.codefarmer.entity.chat.Chat;
import com.codefarm.codefarmer.entity.chat.ChatRoom;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.repository.chat.ChatRepository;
import com.codefarm.codefarmer.repository.chat.ChatRoomRepository;
import com.codefarm.codefarmer.type.ChatStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.ArrayList;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ChatRoomTest {
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private ChatRoomRepository chatRoomRepository;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;


    /*현재 로그인 세션 찾기(o)*/
    @Test
    public void findByMember() {
        log.info("결과 : " + chatRoomService.findByMemberId(1L).get().getMemberName());
    }


    /*로그인 세션에 따른 참여 중인 채팅방 조회*/
    @Test
    public void findAllChatRoom() {
        log.info("결과 : " + chatRoomService.chatRoomSelectAll(1L));
    }

    /*로그인 한 세션이 멘토에게 대화신청 할 때 해당 대화방이 있었는지 유무 확인*/
    @Test
    public void checkChatRoom() {
        // 14번 멘티(일반회원이) 13번 멘토에게 채팅 요청
        log.info("결과 : " + chatRoomService.checkChatRoom(1L, 42L));
    }


    /*채팅방 유무에 따라 채팅방 만들기*/
    @Test
    public void createChatRoom() {
        chatRoomService.createChatRoom(1L, 42L);
    }

    /*채팅방 대화내역 불러오기*/
    @Test
    public void findChatList() {
        log.info("결과 : " + chatRoomService.chatList(87L).toString());
    }


    /*안 읽은 메세지 읽음으로 변경*/
    @Test
    public void changeChatStatus() {
        chatRoomService.readChange(12L);
    }


    /*채팅방 확인*/
    @Test
    public void findChatRoom() {
        log.info("결과 : " + chatRoomService.findByChatRoomId(48L));
    }


    /*채팅 전송*/
    @Test
    public void sendChatTest() {
        ChatDTO chatDTO = new ChatDTO();

        chatDTO.setChatRoom(chatRoomRepository.findById(87L).get()); // 87번 채팅방
        chatDTO.setChatMessage("테스트 문자");
        chatDTO.setChatStatus(ChatStatus.UNREAD);
        chatDTO.setMember(chatRoomService.findByMemberId(1L).get());
        chatDTO.setMemberId(1L);

        System.out.println("결과 : " + chatDTO.toString());
        chatRoomService.sendMessage(chatDTO);
    }

    /*채팅 알림*/
    @Test
    public void chatAlarm() {
        log.info("결과 : " + chatRoomService.chatAlarm(1L));
    }

    /*가장 마지막에 보낸 메세지*/
//    @Test
//    public void lastChatList() {
//        log.info("결과 : " + chatRoomService.lastChatSelectAll());
//    }
}












