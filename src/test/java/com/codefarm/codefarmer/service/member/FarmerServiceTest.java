package com.codefarm.codefarmer.service.member;

import com.codefarm.codefarmer.domain.member.FarmerDTO;
import com.codefarm.codefarmer.domain.member.UserDTO;
import com.codefarm.codefarmer.type.FarmerType;
import com.codefarm.codefarmer.type.UserType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.codefarm.codefarmer.type.Oauth.KAKAO;

@SpringBootTest
@Slf4j
public class FarmerServiceTest {

    @Autowired FarmerService farmerService;

    @Test
    public void farmerAddTest(){
        FarmerDTO farmerDTO = new FarmerDTO();

        farmerDTO.setFarmerType(FarmerType.FARMER);
        farmerDTO.setMemberBirth("2022-10-22");
        farmerDTO.setMemberEmail("aaa@naver.com");
        farmerDTO.setMemberLocation("서울");
        farmerDTO.setMemberName("김지연11");
        farmerDTO.setMemberNickname("oiii");
        farmerDTO.setMemberPhone("010-2222-1111");
        farmerDTO.setMemberOauth(KAKAO);

        farmerService.farmerAdd(farmerDTO);
    }
}
