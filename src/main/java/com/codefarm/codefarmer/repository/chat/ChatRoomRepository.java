package com.codefarm.codefarmer.repository.chat;

import com.codefarm.codefarmer.domain.chat.ChatDTO;
import com.codefarm.codefarmer.entity.chat.Chat;
import com.codefarm.codefarmer.entity.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
