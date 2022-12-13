package com.codefarm.codefarmer.repository.admin;

import com.codefarm.codefarmer.entity.admin.Crop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CropRepository extends JpaRepository<Crop, Long> {
//    검색(제목+내용) + 페이징
    public Page<Crop> findByCropTitleContainingOrCropContentContaining(String cropTitle, String cropContent, Pageable pageable);
//    검색(제목) + 페이징
    public Page<Crop> findByCropTitleContaining(String cropTitle, Pageable pageable);
//    검색(내용) + 페이징
    public Page<Crop> findByCropContentContaining(String cropContent, Pageable pageable);

//    사용자
//    검색(키워드, 제목, 내용, 키워드) + 페이징
    public Page<Crop> findByCropKeywordContainingOrCropTitleContainingOrCropContentContaining(String cropKeyword, String cropTitle, String cropContent, Pageable pageable);

//    작물정보 번호 순으로 정렬
    @Query("select c from Crop c order by c.cropId desc")
    public List<Crop> OrderByCropId();

//    작물정보 글 개수
    @Query("select count(c) from Crop c")
    public int countByCrop();

}
