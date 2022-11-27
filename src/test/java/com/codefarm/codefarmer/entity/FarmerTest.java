package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.domain.FarmerDTO;
import com.codefarm.codefarmer.repository.FarmerRepository;
import com.codefarm.codefarmer.type.FarmerType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.codefarm.codefarmer.type.Oauth.KAKAO;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class FarmerTest {
        @Autowired
        private FarmerRepository farmerRepository;

        @Test
        public void saveTest(){

            FarmerDTO farmerDTO = new FarmerDTO();
            farmerDTO.setFarmerType(FarmerType.FARMER);
            farmerDTO.setMemberBirth("1994-10-21");
            farmerDTO.setMemberEmail("a@naver.com");
            farmerDTO.setMemberLocation("서울");
            farmerDTO.setMemberName("김지연");
            farmerDTO.setMemberNickname("rr222");
            farmerDTO.setMemberPhone("010-1112-1111");
            farmerDTO.setMemberOauth(KAKAO);

            Farmer farmer = farmerDTO.toEntity();

            farmerRepository.save(farmer);

        }


    @Test
    public void updateTest(){
        Farmer farmer = farmerRepository.findById(1l).get();
        FarmerDTO farmerDTO = new FarmerDTO();
        farmerDTO.setMemberNickname("김지김지");

        farmer.update(farmerDTO);
    }

    @Test
    public void deleteTest(){
        farmerRepository.deleteById(1l);
    }

}
