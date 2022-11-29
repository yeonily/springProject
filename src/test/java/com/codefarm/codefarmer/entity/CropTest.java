package com.codefarm.codefarmer.entity;

import com.codefarm.codefarmer.repository.CropRepository;
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
public class CropTest {
    @Autowired
    private CropRepository cropRepository;

    @Test
    public void cropSaveTest(){
        Crop crop = new Crop();

        crop.setCropTitle("제목~~");
        crop.setCropContent("테쑤뜨");
        crop.setCropKeyword("category");
        crop.setCropImage("image");

        cropRepository.save(crop);
    }

    @Test
    public void cropUpdateTest(){
//        Crop crop = cropRepository.findById(7L).get();
////        crop.setCropTitle("제목 수정");
////        crop.setCropContent("내용 수정");
//        crop.setCropImage("이미지도 수정~~");
//
//        cropRepository.save(crop);
    }

    @Test
    public void cropDeleteTest(){
        cropRepository.delete(cropRepository.findById(8L).get());
    }
}
