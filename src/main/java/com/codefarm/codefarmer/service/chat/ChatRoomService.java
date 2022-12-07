package com.codefarm.codefarmer.service.chat;

import com.codefarm.codefarmer.domain.chat.ChatDTO;
import com.codefarm.codefarmer.domain.chat.ChatRoomDTO;
import com.codefarm.codefarmer.domain.chat.QChatRoomDTO;
import com.codefarm.codefarmer.entity.chat.Chat;
import com.codefarm.codefarmer.entity.chat.ChatRoom;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.mentor.Mentor;
import com.codefarm.codefarmer.repository.chat.ChatRepository;
import com.codefarm.codefarmer.repository.chat.ChatRoomRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.mentor.MentorRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

import static com.codefarm.codefarmer.entity.chat.QChat.chat;
import static com.codefarm.codefarmer.entity.chat.QChatRoom.chatRoom;
import static com.codefarm.codefarmer.entity.member.QFarmer.farmer;
import static com.codefarm.codefarmer.entity.member.QUser.user;
import static com.codefarm.codefarmer.type.ChatStatus.READ;
import static com.codefarm.codefarmer.type.ChatStatus.UNREAD;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final JPAQueryFactory jpaQueryFactory;
    private final MentorRepository mentorRepository;
    private final MemberRepository memberRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;

    /*-----------------------------------------------*/
                /*회원번호에 따른 Member 객체 반환*/
    /*-----------------------------------------------*/
    public Member findByMemberId(Long memberId) {
        ArrayList<Member> memberIdList = new ArrayList<Member>(); // 전체 회원의 멤버ID를 담은 배열
        // 일반 유저, 멘티 정보를 저장
        jpaQueryFactory.select(user).from(user).fetch().forEach(v -> memberIdList.add((Member) v));
        // 농장주, 멘토 정보를 저장
        jpaQueryFactory.select(farmer).from(farmer).fetch().forEach(v -> memberIdList.add((Member) v));


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
                chatRoom.mentor,
                chatRoom.mentee,
                chatRoom.chatDate
        )).from(chatRoom)
                .where(chatRoom.mentor.memberId.eq(memberId)
                        .or(chatRoom.mentee.memberId.eq(memberId))).fetch();
    }

    /*-----------------------------------------------*/
                /*선택된 채팅방 대화내역 불러오기*/
    /*-----------------------------------------------*/
    public List<ChatDTO> chatList(Long chatRoomId) {
        return jpaQueryFactory.select(Projections.bean(ChatDTO.class, chat.chatId, chat.chatRoom, chat.chatMessage, chat.chatDate, chat.chatStatus, chat.member, chat.member.memberId))
                .from(chat)
                .where(chat.chatRoom.chatRoomId.eq(chatRoomId))
                .orderBy(chat.chatDate.asc())
                .fetch();
    }


    /*-----------------------------------------------*/
        /*채팅방에 접속했을 때 상대방이 보낸 메세지 읽음 처리*/
    /*-----------------------------------------------*/
    public void readChange(Long chatRoomId) {
        Long memberId = 16L; // 이후에 세션 추가되면 변경해야함(현재 로그인 세션 아이디로)
        List<ChatDTO> chatDTOList = chatList(chatRoomId); // 해당 채팅방의 모든 채팅을 가져옴
        List<Chat> chatList = new ArrayList<>();

        /*내가 입력한 메세지가 아닌 상대방이 입력한 메세지만 뽑기*/
        for(ChatDTO chatDTO : chatDTOList) {
            if(chatDTO.getMember().getMemberId() != memberId) {
                chatList.add(chatDTO.toEntity());
            }
        }
        /*상대가 보낸 모든 채팅은 읽음으로 변경*/
        for(int i = 0; i < chatList.size(); i++) {
            Chat chat = chatRepository.findById(chatList.get(i).getChatId()).get();
            chat.changeChatStatus(READ);
            chatRepository.save(chat);
        }
    }


    /*-----------------------------------------------*/
            /*가장 최근에 보낸 채팅 보여주기(작업해야함)*/
    /*-----------------------------------------------*/
    public List<Chat> lastChatSelectAll() {
        // 1번 회원이 참여 중인 채팅방들을 모두 List로 저장
        List<ChatRoomDTO> chatRoomDTOList = chatRoomSelectAll(1L);


        return null;
    }


    /*-----------------------------------------------*/
                /*이미 대화중이었던 채팅방인지 체크*/
    /*-----------------------------------------------*/
    public boolean checkChatRoom(Long mentorId, Long menteeId) {
        Member member = memberRepository.findById(menteeId).get(); // 현재 로그인 한 아이디(대화 신청자)
        List<ChatRoomDTO> chatRoomDTOList = chatRoomSelectAll(menteeId);

        // 채팅방들 중 해당 멘티와 이미 대화 중인 방이 존재할 경우
        for(ChatRoomDTO chatRoomDTO : chatRoomDTOList) {
            if(chatRoomDTO.getMentor().getMemberId().equals(mentorId) && chatRoomDTO.getMentee().getMemberId().equals(menteeId)) {
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
        ChatRoomDTO chatRoomDTO = new ChatRoomDTO();

        for(Mentor mentor : mentorList) {
            if(mentor.getMember().getMemberId() == mentorId) {
                if(!checkChatRoom(mentorId, menteeId)) {
                    chatRoomDTO.setMentor(mentor.getMember());
                    chatRoomDTO.setMentee(findByMemberId(menteeId));
                    ChatRoom chatRoom = chatRoomDTO.toEntity();
                    chatRoomRepository.save(chatRoom);
                    System.out.println("멘토 : " + mentorId + "\n멘티 : " + menteeId + "\n방 만들어짐");
                }
                break;
            }
        }
    }


    /*-----------------------------------------------*/
        /*로그인 세션 기준 읽지 않은 메세지 있는지 확인(작업해야함)*/
    /*-----------------------------------------------*/
    public void sendMessage() {

    }



    /*-----------------------------------------------*/
          /*로그인 세션 기준 읽지 않은 메세지 있는지 확인*/
    /*-----------------------------------------------*/
    public int chatAlarm(Long memberId) {
        List<ChatRoomDTO> chatRooms = chatRoomSelectAll(memberId); // 현재 회원이 참여한 채팅방 모두 저장
        List<ChatDTO> chats = new ArrayList<>();
        int cnt = 0; // 메세지 읽지 않은 개수

        for(ChatRoomDTO chatRoomDTO : chatRooms) {
            chatList(chatRoomDTO.getChatRoomId()).stream().filter(v -> v.getMember().getMemberId() != memberId).forEach(v -> chats.add(v));
        }

        for(ChatDTO chatDTO : chats) {
            if(chatDTO.getChatStatus() == UNREAD) {
                cnt += 1;
            }
        }
        return cnt;
    }
}
















