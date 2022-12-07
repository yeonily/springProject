package com.codefarm.codefarmer.service.member;

import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.domain.member.UserDTO;
import com.codefarm.codefarmer.entity.admin.Policy;
import com.codefarm.codefarmer.entity.member.Farmer;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.member.User;
import com.codefarm.codefarmer.type.UserType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static com.codefarm.codefarmer.type.Oauth.KAKAO;

@SpringBootTest
@Slf4j
public class UserServiceTest {

    @Autowired UserService userService;

    @Test
    public void userAddTest(){
        UserDTO userDTO = new UserDTO();

        userDTO.setUserType(UserType.USER);
        userDTO.setMemberBirth("2022-10-21");
        userDTO.setMemberEmail("aaa@naver.com");
        userDTO.setMemberLocation("서울");
        userDTO.setMemberName("김지연");
        userDTO.setMemberNickname("rr221322");
        userDTO.setMemberPhone("010-2222-1111");
        userDTO.setMemberOauth(KAKAO);

        userService.userAdd(userDTO);
    }


}
