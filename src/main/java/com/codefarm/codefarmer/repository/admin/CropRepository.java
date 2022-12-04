package com.codefarm.codefarmer.repository.admin;

import com.codefarm.codefarmer.entity.admin.Crop;
import com.codefarm.codefarmer.entity.admin.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CropRepository extends JpaRepository<Crop, Long> {
//    작물정보 번호 순으로 정렬
    @Query("select c from Crop c order by c.cropId desc")
    public List<Crop> OrderByCropId();

//    작물정보 글 개수
    @Query("select count(c) from Crop c")
    public int countByCrop();
}
