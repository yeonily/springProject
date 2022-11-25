package com.codefarm.codefarmer.domain;

import com.codefarm.codefarmer.entity.ChatRoom;
import com.codefarm.codefarmer.entity.Member;
import com.codefarm.codefarmer.type.ChatStatus;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Component
@NoArgsConstructor
@Data
public class ChatRoomDTO {
    private Long chatRoomId;
    private Member mentor;
    private Member mentee;
}
