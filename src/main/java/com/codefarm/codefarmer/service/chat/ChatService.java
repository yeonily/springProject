package com.codefarm.codefarmer.service.chat;

import com.codefarm.codefarmer.domain.chat.ChatDTO;
import com.codefarm.codefarmer.domain.chat.ChatRoomDTO;
import com.codefarm.codefarmer.entity.chat.Chat;
import com.codefarm.codefarmer.entity.chat.ChatRoom;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.repository.chat.ChatRepository;
import com.codefarm.codefarmer.repository.chat.ChatRoomRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;
    private final MemberRepository memberRepository;


    /*채팅 전송*/
    public void chatAdd(ChatDTO chatDTO, ChatRoomDTO chatRoomDTO) {
        ArrayList<Member> memberIdList = (ArrayList<Member>) memberRepository.findAll(); // 전체 회원의 멤버ID를 담은 배열

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
