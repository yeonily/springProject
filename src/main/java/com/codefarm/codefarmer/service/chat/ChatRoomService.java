package com.codefarm.codefarmer.service.chat;

import com.codefarm.codefarmer.domain.chat.ChatDTO;
import com.codefarm.codefarmer.domain.chat.ChatRoomDTO;
import com.codefarm.codefarmer.domain.chat.QChatDTO;
import com.codefarm.codefarmer.domain.chat.QChatRoomDTO;
import com.codefarm.codefarmer.entity.chat.Chat;
import com.codefarm.codefarmer.entity.chat.ChatRoom;
import com.codefarm.codefarmer.entity.chat.QChat;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.member.User;
import com.codefarm.codefarmer.entity.mentor.Mentor;
import com.codefarm.codefarmer.entity.mentor.QMentor;
import com.codefarm.codefarmer.repository.chat.ChatRepository;
import com.codefarm.codefarmer.repository.chat.ChatRoomRepository;
import com.codefarm.codefarmer.repository.member.UserRepository;
import com.codefarm.codefarmer.repository.mentor.MentorRepository;
import com.codefarm.codefarmer.type.MessageType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.codefarm.codefarmer.entity.chat.QChat.chat;
import static com.codefarm.codefarmer.entity.chat.QChatRoom.chatRoom;
import static com.codefarm.codefarmer.entity.member.QFarmer.farmer;
import static com.codefarm.codefarmer.entity.member.QMember.member;
import static com.codefarm.codefarmer.entity.member.QUser.user;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final JPAQueryFactory jpaQueryFactory;
    private final MentorRepository mentorRepository;
    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;

    /*-----------------------------------------------*/
                /*회원번호에 따른 Member 객체 반환*/
    /*-----------------------------------------------*/
    public Member findByMemberId(Long memberId) {
        ArrayList<Member> memberIdList = new ArrayList<Member>(); // 전체 회원의 멤버ID를 담은 배열
        // 일반 유저, 멘티 정보를 저장
        jpaQueryFactory.select(user).from(user).fetch().forEach(v -> memberIdList.add(v));
        // 농장주, 멘토 정보를 저장
        jpaQueryFactory.select(farmer).from(farmer).fetch().forEach(v -> memberIdList.add(v));


        for (Member member : memberIdList) {
            if(member.getMemberId() == memberId) { // 로그인한 회원의 아이디를 찾았을 경우
                return member;
            }
        }
        return null; // 해당 회원이 존재하지 않는 경우는 null을 리턴
    }

    /*-----------------------------------------------*/
            /*로그인 한 세션이 대화 중인 채팅방 목록 조회*/
    /*-----------------------------------------------*/
    public List<ChatRoomDTO> chatRoomSelectAll(Long memberId) {
        return jpaQueryFactory.select(new QChatRoomDTO(
                chatRoom.chatRoomId,
                chatRoom.mentee,
                chatRoom.mentor,
                chatRoom.chatDate
        )).from(chatRoom)
                .where(chatRoom.mentor.memberId.eq(memberId)
                        .or(chatRoom.mentee.memberId.eq(memberId))).fetch();
    }

    /*-----------------------------------------------*/
                /*가장 최근에 보낸 채팅 보여주기(작업해야함)*/
    /*-----------------------------------------------*/
    public List<Chat> lastChatSelectAll() {
        // 1번 회원이 참여 중인 채팅방들을 모두 List로 저장
        List<ChatRoomDTO> chatRoomDTOList = chatRoomSelectAll(1L);




        /*로그인 세션에 따라 대화방들을 저장*/



        return null;
    }


    /*-----------------------------------------------*/
                /*이미 대화중이었던 채팅방인지 체크*/
    /*-----------------------------------------------*/
    public boolean checkChatRoom(Long mentorId, Long menteeId) {
        Member user = userRepository.findById(menteeId).get(); // 현재 로그인 한 아이디(대화 신청자)
        List<ChatRoomDTO> chatRoomDTOList = chatRoomSelectAll(1L);

        // 채팅방들 중 해당 멘티와 이미 대화 중인 방이 존재할 경우
        for(ChatRoomDTO chatRoomDTO : chatRoomDTOList) {
            if(chatRoomDTO.getMentor().getMemberId().equals(menteeId) && chatRoomDTO.getMentee().getMemberId().equals(mentorId)) {
                return true; // 대화방이 존재할 경우 true
            }
        }
        return false; // 대화방이 없을 경우 false
    }

    /*-----------------------------------------------*/
                /*대화가 처음인 경우 대화방 만들기*/
    /*-----------------------------------------------*/
    public void createChatRoom(Long mentorId, Long menteeId) {
        List<Mentor> mentorList = mentorRepository.findAll();

        if(!checkChatRoom(mentorId, menteeId)) { // 대화방이 존재하지 않는 경우에만 방 생성
            ChatRoomDTO chatRoomDTO = new ChatRoomDTO();
            chatRoomDTO.setMentee(userRepository.findById(menteeId).get()); // 멘티(로그인 세션)

            for(Mentor mentor : mentorList) {
                if(mentor.getMember().getMemberId().equals(mentorId)) {
                    chatRoomDTO.setMentor(mentor.getMember()); // 멘토
                    break;
                }
            }
            ChatRoom chatRoom = chatRoomDTO.toEntity();
            chatRoomRepository.save(chatRoom);
        }
    }
}
















