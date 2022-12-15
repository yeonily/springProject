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

import java.util.*;

import static com.codefarm.codefarmer.entity.chat.QChat.chat;
import static com.codefarm.codefarmer.entity.chat.QChatRoom.chatRoom;
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
    public Optional<Member> findByMemberId(Long memberId) {
        return memberRepository.findById(memberId);
    }


    /*-----------------------------------------------*/
                /*채팅방 번호로 DTO로 반환*/
    /*-----------------------------------------------*/
    public ChatRoomDTO findByChatRoomId(Long chatRoomId) {
        Optional<ChatRoom> chatRoom = chatRoomRepository.findById(chatRoomId);
        ChatRoomDTO chatRoomDTO = new ChatRoomDTO();

        chatRoomDTO.setChatRoomId(chatRoomId);
        chatRoomDTO.setMentee(chatRoom.get().getMentee());
        chatRoomDTO.setMentor(chatRoom.get().getMentor());

        return chatRoomDTO;
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
    public void readChange(Long chatRoomId, Long memberId) {
        List<ChatDTO> chatDTOList = chatList(chatRoomId); // 해당 채팅방의 모든 채팅을 가져옴
        List<Chat> chatList = new ArrayList<>();

        for(ChatDTO chatDTO : chatDTOList) {
            /*내가 입력한 메세지가 아닌 상대방이 입력한 메세지만 뽑기*/
            if(!chatDTO.getMember().getMemberId().equals(memberId)) {
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
                /*이미 대화중이었던 채팅방인지 체크*/
    /*-----------------------------------------------*/
    public boolean checkChatRoom(Long mentorId, Long menteeId) {
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

        /*만약 멘토, 멘티 아이디가 같을 경우 즉, 로그인 세션이 자기가 작성한 글에 멘토신청할 경우*/
        if(mentorId.equals(menteeId)) {
            return;
        }

        for(Mentor mentor : mentorList) {
            if(mentor.getMember().getMemberId().equals(mentorId)) {
                if(!checkChatRoom(mentorId, menteeId)) {
                    chatRoomDTO.setMentor(mentor.getMember());
                    chatRoomDTO.setMentee(findByMemberId(menteeId).get());
                    ChatRoom chatRoom = chatRoomDTO.toEntity();
                    chatRoomRepository.save(chatRoom);
                    System.out.println("멘토 : " + mentorId + "\n멘티 : " + menteeId + "\n방 만들어짐");
                }
                break;
            }
        }
    }


    /*-----------------------------------------------*/
                        /*메세지 전송*/
    /*-----------------------------------------------*/
    public void sendMessage(ChatDTO chatDTO) {
        Chat chat = chatDTO.toEntity();
        chat.changeChatStatus(chatDTO.getChatStatus());
        chat.changeChatRoom(chatRoomRepository.findById(chatDTO.getRoomId()).get());
        chat.changeMember(memberRepository.findById(chatDTO.getMemberId()).get());
        chatRepository.save(chat);
    }


    /*안 읽은 메세지 중 가장 마지막 메세지를 저장(해당 방 번호, 로그인 세션)*/
    public ChatDTO returnChat(Long roomId, Long memberId) {
        List<ChatDTO> chatList = chatList(roomId);
        ChatDTO chat = new ChatDTO();

        for (ChatDTO chatDTO : chatList) {
            // 상대가 보낸 메세지 중에서 읽지 않은 메세지를 저장
            if(!chatDTO.getMemberId().equals(memberId) && chatDTO.getChatStatus() == UNREAD) {
                // 닉네임을 저장
                chatDTO.setNickName(memberRepository.findById(chatDTO.getMemberId()).get().getMemberNickname());
                chatDTO.setRoomId(roomId);
                chat = chatDTO;
            }
        }
        return chat;
    }


    /*-----------------------------------------------*/
          /*로그인 세션 기준 읽지 않은 메세지 있는지 확인*/
    /*-----------------------------------------------*/
    public List<ChatDTO> chatAlarm(Long memberId) {
        List<ChatRoomDTO> chatRooms = chatRoomSelectAll(memberId); // 현재 회원이 참여한 채팅방 모두 저장
        List<ChatDTO> result = new ArrayList<>();

        for(ChatRoomDTO chatRoomDTO : chatRooms) {
            if(returnChat(chatRoomDTO.getChatRoomId(), memberId).getMemberId() != null) {
                result.add(returnChat(chatRoomDTO.getChatRoomId(), memberId));
            }
        }
        return result;
    }
}
















