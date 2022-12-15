package com.codefarm.codefarmer.service.Mentor;

import com.codefarm.codefarmer.repository.mentor.MentorCustomRepository;
import com.codefarm.codefarmer.repository.mentor.MentorMenteeRepository;
import com.codefarm.codefarmer.service.mentor.MentorMenteeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class MentorMenteeServiceTest {

    @Autowired
    private MentorMenteeService mentorMenteeService;

    @Test
    public void removeByIdTest(){
        Long mentorMenteeId = 1l;
        mentorMenteeService.removeById(mentorMenteeId);
    }



}
