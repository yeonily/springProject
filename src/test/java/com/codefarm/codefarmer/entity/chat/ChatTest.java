package com.codefarm.codefarmer.entity.chat;

import com.codefarm.codefarmer.domain.chat.ChatDTO;
import com.codefarm.codefarmer.domain.chat.ChatRoomDTO;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.repository.chat.ChatRepository;
import com.codefarm.codefarmer.repository.chat.ChatRoomRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.mentor.MentorRepository;
import com.codefarm.codefarmer.type.ChatStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.codefarm.codefarmer.entity.chat.QChat.chat;
import static com.codefarm.codefarmer.entity.chat.QChatRoom.chatRoom;
import static com.codefarm.codefarmer.entity.member.QMember.member;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ChatTest {
    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    ChatRepository chatRepository;
    @Autowired
    ChatRoomRepository chatRoomRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MentorRepository mentorRepository;

    
    /*대화 신청 시 대화방 만들어짐*/
    @Test
    public void chatRoomSaveTest() {
        ChatRoomDTO chatRoomDTO = new ChatRoomDTO();
        Member member = memberRepository.findById(43L).get(); // 일반 회원(유저ID)
        Member mentor = mentorRepository.findById(11L).get().getMember(); // 멘토 회원(멘토ID)

        chatRoomDTO.setMentor(mentor);
        chatRoomDTO.setMentee(member);

        ChatRoom chatRoom = chatRoomDTO.toEntity();
        chatRoomRepository.save(chatRoom);
    }


    /*채팅방 목록 조회(회원번호 기준으로 찾음) ㅇ*/
    @Test
    public void chatList() {
        jpaQueryFactory.select(chatRoom.chatRoomId, chatRoom.chatDate, chatRoom.mentee, chatRoom.mentor)
                .from(chatRoom)
                .where(chatRoom.mentor.memberId.eq(1L).or(chatRoom.mentee.memberId.eq(1L)))
                .fetch()
                .stream().map(ChatRoom -> ChatRoom.toString()).forEach(log::info);
    }


    /*채팅방 대화 기록 불러오기(채팅방 번호 기준으로 찾음)*/
    @Test
    public void chatRecord() {
        ChatRoom chatRoom = chatRoomRepository.findById(17L).get(); // 채팅방 번호에 따라 채팅방을 저장
        jpaQueryFactory.select(chat.chatRoom, chat.chatMessage, chat.chatDate, chat.chatStatus)
                .from(chat)
                .where(chat.chatRoom.eq(chatRoom))
                .orderBy(chat.chatDate.asc())
                .fetch()
                .stream().map(chat -> chat.toString()).forEach(log::info);
    }


    /*대화방에 참여하여 채팅 전송*/
    @Test
    public void sendChatTest() {
        ChatDTO chatDTO = new ChatDTO();
        ChatRoom chatRoom = chatRoomRepository.findById(87L).get(); // 접속한 방번호에 따라 해당 채팅방을 저장
        ArrayList<Member> memberIdList = (ArrayList<Member>) memberRepository.findAll(); // 전체 회원의 멤버ID를 담은 배열


        for (Member member : memberIdList) {
            if(member.getMemberId() == 86L) { // 로그인한 회원의 아이디를 찾았을 경우
                chatDTO.setChatMessage("채팅 테스트");
                chatDTO.setChatStatus(ChatStatus.UNREAD); // 초기 전송 시에는 읽지 않았기 때문에 UNREAD로 전송
//                chatDTO.setChatRoom(chatRoom);
                chatDTO.setMember(member);

                Chat chat = chatDTO.toEntity();
                chat.changeChatRoom(chatDTO.getChatRoom()); // 해당 방에 채팅을 저장
                chat.changeMember(chatDTO.getMember());
                chatRepository.save(chat);

                return;
            }
        }
    }

    /*채팅방 알림(읽지않은 메세지 조회)*/
    @Test
    public void readChat() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        List<Chat> chats = chatRepository.findAll();

        // 전체 채팅방을 불러오고
        for(ChatRoom chatRoom : chatRooms) {
            // 그 중 현재 로그인 한 세션(6번 회원)이 참여한 방 중에서
            if(chatRoom.getMentor().getMemberId() == 1L || chatRoom.getMentee().getMemberId() == 1L) {
                // 대화 기록들을 불러오고
                for(Chat chat : chats) {
                    // 대화 기록 중에 읽지 않은 상태의 메세지가 있는 경우
                    if(chat.getChatStatus().equals(ChatStatus.UNREAD)) {
                        // 출력
                        log.info("Test : " + chat.getChatMessage());
                        // chat.changeChatStatus(ChatStatus.READ);
                    }
                }
                return;
            }
        }
    }


    /*채팅 읽음(로그인한 회원의 상대방 메세지를 읽음처리 해야 함)*/
    @Test
    public void readStatus() {
        List<Chat> chats = chatRepository.findAll();

        for (Chat chat : chats) {
            if(chat.getChatRoom().getChatRoomId() == 17L) { // 17번방으로 테스트
                if(chat.getMember().getMemberId() != 1L) { // 로그인한 1번 회원이 입력한 값 외에는 모두 읽음처리
                    chat.changeChatStatus(ChatStatus.READ);
                }
            }
        }
    }
}













