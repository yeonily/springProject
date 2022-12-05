package com.codefarm.codefarmer.service.member;

import com.codefarm.codefarmer.domain.member.FarmerDTO;
import com.codefarm.codefarmer.domain.member.UserDTO;
import com.codefarm.codefarmer.entity.member.Farmer;
import com.codefarm.codefarmer.entity.member.User;
import com.codefarm.codefarmer.repository.alba.AlbaRepository;
import com.codefarm.codefarmer.repository.member.FarmerRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class FarmerService {

        private final JPAQueryFactory jpaQueryFactory;
        private final FarmerRepository farmerRepository;


        //    유저 회원가입
        public void farmerAdd(FarmerDTO farmerDTO){
                Farmer farmer = farmerDTO.toEntity();
                farmerRepository.save(farmer);
        }
}
