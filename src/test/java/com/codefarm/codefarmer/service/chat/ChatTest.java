package com.codefarm.codefarmer.service.chat;

import com.codefarm.codefarmer.domain.chat.ChatDTO;
import com.codefarm.codefarmer.entity.chat.Chat;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.type.ChatStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

import java.util.ArrayList;

import static com.codefarm.codefarmer.entity.member.QFarmer.farmer;
import static com.codefarm.codefarmer.entity.member.QUser.user;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ChatTest {
    @Autowired
    private ChatService chatService;
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;


    @Test
    public void sendChat() {
        ChatDTO chatDTO = new ChatDTO();


        chatDTO.setChatMessage("서비스 확인용...");


//        ChatRoom chatRoom = chatRoomRepository.findById(17L).get();


    }

    @Test
    public void findAllChatRoom() {
        ArrayList<Member> memberIdList = new ArrayList<Member>(); // 전체 회원의 멤버ID를 담은 배열
        jpaQueryFactory.select(user).from(user).fetch().forEach(v -> memberIdList.add(v));
        jpaQueryFactory.select(farmer).from(farmer).fetch().forEach(v -> memberIdList.add(v));


        for (Member member : memberIdList) {
            if(member.getMemberId() == 1L) { // 로그인한 회원의 아이디를 찾았을 경우
                log.info("결과 : " + chatRoomService.chatRoomSelectAll(member));
                return;
            }
        }

    }
}
