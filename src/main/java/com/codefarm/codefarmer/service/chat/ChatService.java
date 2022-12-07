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
    private final ChatRoomService chatRoomService;
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
                chat.changeMember(chatDTO.getMember());
                chatRepository.save(chat);

                return;
            }
        }
    }

}
