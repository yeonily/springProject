package com.codefarm.codefarmer.service.admin;

import com.codefarm.codefarmer.entity.admin.Crop;
import com.codefarm.codefarmer.entity.admin.Policy;
import com.codefarm.codefarmer.repository.admin.CropRepository;
import com.codefarm.codefarmer.repository.admin.PolicyRepository;
import com.codefarm.codefarmer.repository.board.ReplyRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
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
    @Autowired
    private ReplyRepository replyRepository;
    @Autowired
    private AdminService adminService;

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
        Policy policy = policyRepository.findById(184L).get();
        log.info("정책 --> " + policy);
        policy.setPolicyKeyword("키워드 임다");
        log.info("정책 수정 --> " + policy);
//        policy.setPolicyTitle("정책 수정");
//        policy.setPolicyContent("서비스 수정22");

        informationService.policyUpdate(policy);
    }

//    정책 삭제
    @Test
    public void policyDeleteTest(){
        log.info("" + informationService.policyDelete(3L));
    }

//    정책 목록 불러오기
    @Test
    public void policySelectAllTest(Pageable pageable){
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
        Crop crop = cropRepository.findById(69L).get();

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
    public void cropSelectAllTest(Pageable pageable){
    }

//    농업정보 디테일
    @Test
    public void cropSelectOneTest(){
        log.info("" + informationService.cropShowOne(69L));
    }


//    댓글 수 리스트
//    @Test
//    public void replyCountByBoardIdTest() { log.info("댓글 수 : " + replyRepository.countReplyByBoardId(1L)); }

//    @Test
//    public void boardShowAllTest(){
//        Pageable pageable = Pageable.unpaged();
//        String keyword = "";
//        String searchText = "";
//        adminService.boardShowAll(pageable, keyword, searchText).stream().forEach(b -> log.info("하.... -> " + b));
//    }
}
