package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.ChatDTO;
import com.codefarm.codefarmer.domain.ChatRoomDTO;
import com.codefarm.codefarmer.domain.UserDTO;
import com.codefarm.codefarmer.repository.*;
import com.codefarm.codefarmer.type.ChatStatus;
import com.codefarm.codefarmer.type.Oauth;
import com.codefarm.codefarmer.type.UserType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class ChatTest {
    @Autowired
    ChatRepository chatRepository;
    @Autowired
    ChatRoomRepository chatRoomRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    MentorRepository mentorRepository;

    /*일반 유저 만들기*/
    @Test
    public void createUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setMemberBirth("1997-02-16");
        userDTO.setMemberEmail("mild0216@naver.com");
        userDTO.setMemberLocation("서울");
        userDTO.setMemberName("김덕춘");
        userDTO.setMemberNickname("니크네임");
        userDTO.setMemberOauth(Oauth.KAKAO);
        userDTO.setMemberPhone("010-4851-1558");
        userDTO.setUserType(UserType.USER);

        User user = userDTO.toEntity();

        userRepository.save(user);
    }


    /*대화 신청 시 대화방 만들어짐*/
    @Test
    public void chatRoomSaveTest() {
        ChatRoomDTO chatRoomDTO = new ChatRoomDTO();
        User user = userRepository.findById(6L).get(); // 일반 회원(유저ID)
        Member mentor = mentorRepository.findById(16L).get().getMember(); // 멘토 회원(멘토ID)

        chatRoomDTO.setMentor(mentor);
        chatRoomDTO.setMentee(user);

        ChatRoom chatRoom = chatRoomDTO.toEntity();
        chatRoomRepository.save(chatRoom);
    }


    /*채팅방 조회(3번 회원 기준으로 찾음)*/
    @Test
    public void chatList() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAll(); // 전체 대화방을 불러옴

        for(ChatRoom chatRoom : chatRooms) {
            // 전체 대화방 중 MEMBER_ID가 3인 회원이 참여 중인 방들을 찾아 출력
            if(chatRoom.getMentor().getMemberId() == 3L || chatRoom.getMentee().getMemberId() == 3L) {
                log.info("결과 : " + chatRoom.getChatRoomId());
            }
        }
    }


    /*대화방에 참여하여 채팅 전송*/
    @Test
    public void sendChatTest() {
        ChatDTO chatDTO = new ChatDTO();
        ChatRoom chatRoom = chatRoomRepository.findById(17L).get(); // 17번 방을 저장
        Member member = userRepository.findById(6L).get(); // 현재 로그인 한 회원을 객체로 저장(임시 3L)

        log.info("결과 : " + member.getMemberId());

//        chatDTO.setChatMessage("멘토가 입력함");
//        chatDTO.setChatStatus(ChatStatus.UNREAD); // 초기 전송 시에는 읽지 않았기 때문에 UNREAD로 전송
//        chatDTO.setChatRoom(chatRoom);
//        chatDTO.setMemberId(member);
//
//        Chat chat = chatDTO.toEntity();
//        chat.changeChatRoom(chatDTO.getChatRoom()); // 12번방에 위에서 작성한 메세지 저장
//        chat.changeMember(chatDTO.getMemberId());

//        chatRepository.save(chat);
    }




    /*채팅방 알림(읽지않은 메세지 조회)[진행중]*/
    @Test
    public void readChat() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        List<Chat> chats = chatRepository.findAll();

        // 전체 채팅방을 불러오고
        for(ChatRoom chatRoom : chatRooms) {
            // 그 중 현재 로그인 한 세션(6번 회원)이 참여한 방 중에서
            if(chatRoom.getMentor().getMemberId() == 6L || chatRoom.getMentee().getMemberId() == 6L) {
                // 대화 기록들을 불러오고
                for(Chat chat : chats) {
                    chat.changeChatRoom(chatRoom);
                    // 대화 기록 중에 읽지 않은 상태의 메세지가 있는 경우
                    if(chat.getChatStatus().equals(ChatStatus.UNREAD)) {
                        // 우선은 출력
                        log.info("입력값 : " + chat.getChatMessage());
                    }
                }
            }
        }
    }
}













