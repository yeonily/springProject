package com.codefarm.codefarmer.entity.admin;

import com.codefarm.codefarmer.entity.admin.Crop;
import com.codefarm.codefarmer.repository.admin.CropRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static com.codefarm.codefarmer.entity.admin.QCrop.crop;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class CropTest {
    @Autowired
    private CropRepository cropRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

//    작물 입력
    @Test
    public void cropSaveTest(){
        Crop crop = new Crop();

        crop.setCropTitle("제목임~~");
        crop.setCropContent("테스트임~~~");
        crop.setCropKeyword("category");

        cropRepository.save(crop);
    }

//    작물 수정
    @Test
    public void cropUpdateTest(){
//        Crop crop = cropRepository.findById(7L).get();
////        crop.setCropTitle("제목 수정");
////        crop.setCropContent("내용 수정");
//        crop.setCropImage("이미지도 수정~~");
//
//        cropRepository.save(crop);
    }

//    삭제
    @Test
    public void cropDeleteTest(){
        cropRepository.delete(cropRepository.findById(8L).get());
    }

//    목록
    @Test
    public void cropSelectAllTest(){
        jpaQueryFactory.select(crop).from(crop)
//                .where(crop.cropId.eq(27L))
                .fetch()
                .stream().map(crop -> crop.toString()).forEach(log::info);
    }
}
