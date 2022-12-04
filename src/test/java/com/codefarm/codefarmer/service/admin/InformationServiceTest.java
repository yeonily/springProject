package com.codefarm.codefarmer.service.admin;

import com.codefarm.codefarmer.entity.admin.Crop;
import com.codefarm.codefarmer.entity.admin.Policy;
import com.codefarm.codefarmer.repository.admin.CropRepository;
import com.codefarm.codefarmer.repository.admin.PolicyRepository;
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
public class InformationServiceTest {
    @Autowired
    private InformationService informationService;

    @Autowired
    private PolicyRepository policyRepository;
    @Autowired
    private CropRepository cropRepository;

//    정책 등록
    @Test
    public void policyAddTest(){
        Policy policy = new Policy();
//        Policy policy = new Policy();
        policy.setPolicyKeyword("키워드");
        policy.setPolicyTitle("정책 서비스 제목 테스트");
        policy.setPolicyContent("정첵 서비스 테스트");

        informationService.policyAdd(policy);
    }

//    정책 수정
    @Test
    public void policyUpdateTest(){
        Policy policy = policyRepository.findById(36L).get();

        policy.setPolicyTitle("정책 서비스 제목 수정");
        policy.setPolicyContent("서비스 수정");

        informationService.policyUpdate(policy);
    }

//    정책 삭제
    @Test
    public void policyDeleteTest(){
        log.info("" + informationService.policyDelete(3L));
    }

//    정책 목록 불러오기
    @Test
    public void policySelectAllTest(){
        informationService.policySelectAll().stream().forEach(p -> log.info("" + p));
    }

//    농업정보 추가
    @Test
    public void cropAddTest(){
        Crop crop = new Crop();

        crop.setCropKeyword("Category");
        crop.setCropTitle("서비스 제목22");
        crop.setCropContent("서비스 내용22");
        crop.setCropImage("이미지");

        informationService.cropAdd(crop);
    }

//    농업정보 수정
    @Test
    public void cropUpdateTest(){
        Crop crop = cropRepository.findById(27L).get();

        crop.setCropContent("Service 내용 수정");
        crop.setCropImage("Image 수정");

        informationService.cropUpdate(crop);
    }

//    농업정보 삭제
    @Test
    public void cropDeleteTest(){
        log.info("" + informationService.cropDelete(27L));
    }

//    농업정보 목록
    @Test
    public void cropSelectAllTest(){
        informationService.cropSelectAll().stream().forEach(c -> log.info("" + c));
    }
}
