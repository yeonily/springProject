package com.codefarm.codefarmer.service.program;

import com.codefarm.codefarmer.service.alba.AlbaService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
@Rollback(false)
public class ProgramServiceTest {
    @Autowired ProgramListService programListService;
    @Test
    public void findTop8ByOrderByProgramApplyEndDateDescTest() {
        programListService.findTop8ByOrderByProgramApplyEndDateDesc().forEach(program -> {
            log.info(program.getProgramTitle() + "");
        });
    }
}
