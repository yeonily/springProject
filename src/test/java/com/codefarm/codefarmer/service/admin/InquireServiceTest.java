package com.codefarm.codefarmer.service.admin;

import com.codefarm.codefarmer.domain.inquire.InquireDTO;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.member.Farmer;
import com.codefarm.codefarmer.entity.member.QMember;
import com.codefarm.codefarmer.entity.member.User;
import com.codefarm.codefarmer.repository.member.FarmerRepository;
import com.codefarm.codefarmer.repository.member.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class InquireServiceTest {
    @Autowired
    private InquireService inquireService;
    @Autowired
    private FarmerRepository farmerRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void inquireAddTest(){
        InquireDTO inquireDTO = new InquireDTO();
        Optional<User> findUser = userRepository.findById(11L);

        inquireDTO.setInquireQTitle("나 문희");
        inquireDTO.setInquireQContent("문희는 포도가 먹고찌푼뎅...");
        inquireDTO.setMember(findUser.get());
        inquireService.inquireAdd(inquireDTO);
    }

}
