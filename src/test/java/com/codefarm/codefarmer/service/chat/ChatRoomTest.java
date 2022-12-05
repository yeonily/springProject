package com.codefarm.codefarmer.service.chat;

import com.codefarm.codefarmer.entity.chat.Chat;
import com.codefarm.codefarmer.entity.member.Farmer;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.mentor.Mentor;
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
public class ChatRoomTest {
    @Autowired
    private ChatRoomService chatRoomService;
    @Autowired
    private JPAQueryFactory jpaQueryFactory;


    /*현재 로그인 세션 찾기*/
    @Test
    public void findByMember() {
        log.info("결과 : " + chatRoomService.findByMemberId(1L).getMemberName());
    }


    /*로그인 세션에 따른 참여 중인 채팅방 조회*/
    @Test
    public void findAllChatRoom() {
        ArrayList<Member> memberIdList = new ArrayList<Member>(); // 전체 회원의 멤버ID를 담은 배열
        jpaQueryFactory.select(user).from(user).fetch().forEach(v -> memberIdList.add(v));
        jpaQueryFactory.select(farmer).from(farmer).fetch().forEach(v -> memberIdList.add(v));

        for (Member member : memberIdList) {
            if(member.getMemberId() == 1L) { // 로그인한 회원의 아이디를 찾았을 경우
                log.info("결과 : " + chatRoomService.chatRoomSelectAll(1L));
                return;
            }
        }
    }

    /*로그인 한 세션이 멘토에게 대화신청 할 때 해당 대화방이 있었는지 유무 확인*/
    @Test
    public void checkChatRoom() {
        log.info("결과 : " + chatRoomService.checkChatRoom(1L, 2L)); // 멘토 아이디는 멤버 아이디x
    }


    /*채팅방 유무에 따라 채팅방 만들기*/
    @Test
    public void createChatRoom() {
        chatRoomService.createChatRoom(1L, 16L);
    }



    /*가장 마지막에 보낸 메세지*/
    @Test
    public void lastChatList() {
        log.info("결과 : " + chatRoomService.lastChatSelectAll());
    }
}












