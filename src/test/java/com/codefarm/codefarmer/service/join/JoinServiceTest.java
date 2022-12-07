package com.codefarm.codefarmer.service.join;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class JoinServiceTest {

    @Autowired
    JoinService joinService;

    @Test
    public void checkUserNickTest(){
        int count = joinService.checkUserNick("러너");
        Assertions.assertThat(count).isEqualTo(0);
    }
}
