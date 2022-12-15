package com.codefarm.codefarmer.repository.alba;
import com.codefarm.codefarmer.entity.alba.Alba;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface AlbaRepository extends JpaRepository<Alba, Long>, AlbaCustomRepository {
//    검색(아르바이트명) + 페이징
    public Page<Alba> findByAlbaTitleContaining(String albaTitle, Pageable pageable);

//    아르바이트 글 개수
    @Query("select count(a) from Alba a")
    public int countByAlba();
}
