package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.member.UserDTO;
import com.codefarm.codefarmer.entity.member.User;
import com.codefarm.codefarmer.repository.member.UserRepository;
import com.codefarm.codefarmer.type.UserType;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.codefarm.codefarmer.type.Oauth.KAKAO;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class UserTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveTest(){

        UserDTO UserDTO = new UserDTO();
        UserDTO.setUserType(UserType.USER);
        UserDTO.setMemberBirth("1994-10-21");
        UserDTO.setMemberEmail("aaa@naver.com");
        UserDTO.setMemberLocation("서울");
        UserDTO.setMemberName("김지연");
        UserDTO.setMemberNickname("rr222");
        UserDTO.setMemberPhone("010-1114-1111");
        UserDTO.setMemberOauth(KAKAO);

        User user = UserDTO.toEntity();

        userRepository.save(user);

    }

    @Test
    public void findTest(){
        Optional<User> findUser = userRepository.findById(1l);
        if(findUser.isPresent()){
            Assertions.assertThat(findUser.get().getMemberName()).isEqualTo("김지연");

            log.info("user name : " + findUser.get().getMemberName());
        }

    }


    @Test
    public void updateTest(){
        User user = userRepository.findById(1l).get();
        UserDTO UserDTO = new UserDTO();
        UserDTO.setMemberNickname("김지김지");

        user.update(UserDTO);
    }

    @Test
    public void deleteTest(){
        userRepository.deleteById(1l);
    }

}
