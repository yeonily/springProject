package com.codefarm.codefarmer.service.join;

import com.codefarm.codefarmer.domain.member.UserDTO;
import com.codefarm.codefarmer.entity.member.User;
import com.codefarm.codefarmer.repository.member.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JoinService {

    private final UserRepository userRepository;

    public Integer checkUserNick(String userNickname) {return userRepository.checkUserNick(userNickname);}

}
