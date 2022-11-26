package com.codefarm.codefarmer.repository;


import com.codefarm.codefarmer.domain.FarmerDTO;
import com.codefarm.codefarmer.entity.Farmer;
import com.codefarm.codefarmer.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.codefarm.codefarmer.type.FarmerType.FARMER;
import static com.codefarm.codefarmer.type.FarmerType.MENTOR;
import static com.codefarm.codefarmer.type.Oauth.KAKAO;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class FarmerRepositoryTest {

    @Autowired
    private FarmerRepository farmerRepository;



    @Test
    public void saveTest(){
//        FarmerDTO farmerDTO = new FarmerDTO();
//        farmerDTO.toEntity();
//        log.info("dfd" + farmerDTO.toEntity());
//
//        farmerDTO.setFarmerType(MENTOR);
//        farmerDTO.setMemberBirth("2019-10-11");
//        farmerDTO.setMemberEmail("aaa@naver.com");
//        farmerDTO.setMemberLocation("서울");
//        farmerDTO.setMemberName("서승우");
//        farmerDTO.setMemberNickname("sdfas");
//        farmerDTO.setMemberPhone("010121");
//        farmerDTO.setMemberOauth(KAKAO);
//
//        farmerRepository.save(farmerDTO);
    }

}
