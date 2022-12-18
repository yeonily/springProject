package com.codefarm.codefarmer.service.login;

import com.codefarm.codefarmer.service.join.JoinService;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class LoginServiceTest {

    @Autowired
    KakaoService kakaoService;
    @Autowired
    GoogleService googleService;

    @Test
    public void checkOauthTest(){
        int count = kakaoService.checkOauth("2560839262k");
        Assertions.assertThat(count).isEqualTo(1);
    }

    @Test
    public void check() {
        System.out.println("확인용 : " + googleService.findByMember("sudal0216@gmail.com"));
    }
}
