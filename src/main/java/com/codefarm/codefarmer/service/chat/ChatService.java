package com.codefarm.codefarmer.service.chat;

import com.codefarm.codefarmer.domain.chat.ChatDTO;
import com.codefarm.codefarmer.domain.chat.ChatRoomDTO;
import com.codefarm.codefarmer.entity.chat.Chat;
import com.codefarm.codefarmer.entity.chat.ChatRoom;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.repository.chat.ChatRepository;
import com.codefarm.codefarmer.repository.chat.ChatRoomRepository;
import com.codefarm.codefarmer.repository.member.FarmerRepository;
import com.codefarm.codefarmer.repository.member.UserRepository;
import com.codefarm.codefarmer.type.ChatStatus;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.codefarm.codefarmer.entity.member.QFarmer.farmer;
import static com.codefarm.codefarmer.entity.member.QUser.user;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final JPAQueryFactory jpaQueryFactory;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final FarmerRepository farmerRepository;


    /*채팅 전송*/
    public void chatAdd(ChatDTO chatDTO, ChatRoomDTO chatRoomDTO) {
        ChatRoom chatRoom = chatRoomDTO.toEntity();

        ArrayList<Member> memberIdList = new ArrayList<Member>(); // 전체 회원의 멤버ID를 담은 배열
        jpaQueryFactory.select(user).from(user).fetch().forEach(v -> memberIdList.add(v));
        jpaQueryFactory.select(farmer).from(farmer).fetch().forEach(v -> memberIdList.add(v));

        for(Member member : memberIdList) {
            if(member.getMemberId().equals(1L)) { // 현재 로그인 세션이 1번일 때
                Chat chat = chatDTO.toEntity();
                chat.changeChatMessage(chatDTO.getChatMessage());
                chat.changeMember(chatDTO.getMemberId());
                chatRepository.save(chat);

                return;
            }
        }
    }


    /*채팅방 알림(읽지 않은 메세지 유무 확인)*/
    public void chatAlarm() {
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
                        chat.changeChatStatus(ChatStatus.READ);
                    }
                }
                return;
            }
        }
    }

    /*채팅 읽음(로그인한 회원의 상대방 메세지를 읽음처리 해야 함)*/
    public void chatStatusChangeRead() {
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
