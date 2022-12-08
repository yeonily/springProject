package com.codefarm.codefarmer.repository.alba;
import com.codefarm.codefarmer.entity.alba.Alba;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlbaRepository extends JpaRepository<Alba, Long>, AlbaCustomRepository {
}
