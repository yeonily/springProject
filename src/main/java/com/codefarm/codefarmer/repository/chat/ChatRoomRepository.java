package com.codefarm.codefarmer.repository.chat;

import com.codefarm.codefarmer.entity.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
}
