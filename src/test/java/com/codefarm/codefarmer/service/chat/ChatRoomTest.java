package com.codefarm.codefarmer.service.chat;

import com.codefarm.codefarmer.domain.chat.ChatDTO;
import com.codefarm.codefarmer.repository.chat.ChatRoomRepository;
import com.codefarm.codefarmer.type.ChatStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;



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
        log.info("결과 : " + chatRoomService.checkChatRoom(86L, 83L));
    }


    /*채팅방 유무에 따라 채팅방 만들기*/
    @Test
    public void createChatRoom() {
        chatRoomService.createChatRoom(86L, 33L);
    }

    /*채팅방 대화내역 불러오기*/
    @Test
    public void findChatList() {
        log.info("결과 : " + chatRoomService.chatList(1982L).toString());
    }


    /*안 읽은 메세지 읽음으로 변경*/
    @Test
    public void changeChatStatus() {
        // 현재 로그인 한 세션 735L 회원 메세지를 제외하고는 모두 읽음 처리
        chatRoomService.readChange(1982L, 735L);
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

        chatDTO.setRoomId(2495L);
        chatDTO.setChatRoom(chatRoomRepository.findById(2495L).get()); // 87번 채팅방
        chatDTO.setChatMessage("테스트 문자555 마지막");
        chatDTO.setChatStatus(ChatStatus.UNREAD);
        chatDTO.setMember(chatRoomService.findByMemberId(33L).get());
        chatDTO.setMemberId(33L);
        chatDTO.setNickName(chatRoomService.findByMemberId(33L).get().getMemberNickname());

        System.out.println("결과 : " + chatDTO.toString());
        chatRoomService.sendMessage(chatDTO);
    }

    /*채팅 알림*/
    @Test
    public void chatAlarm() {
        log.info("결과 : " + chatRoomService.chatAlarm(735L));
    }

    /*채팅방에서 안 읽은 메세지 중 가장 마지막*/
    @Test
    public void returnCheck() {
        log.info("결과요 : " + chatRoomService.returnChat(2478L, 86L));
    }
}












