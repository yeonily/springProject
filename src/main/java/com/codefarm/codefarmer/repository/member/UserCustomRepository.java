package com.codefarm.codefarmer.repository.member;

import com.codefarm.codefarmer.entity.member.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCustomRepository  {
    //닉네임 중복검사
    public Integer checkUserNick(String nickname);
}
