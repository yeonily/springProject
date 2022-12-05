package com.codefarm.codefarmer.service.member;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.domain.member.UserDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.member.User;
import com.codefarm.codefarmer.repository.member.UserRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

        private final JPAQueryFactory jpaQueryFactory;
        private final UserRepository userRepository;

        //    유저 회원가입
        public void userAdd(UserDTO userDTO){
                User user = userDTO.toEntity();
                userRepository.save(user);
        }
}
