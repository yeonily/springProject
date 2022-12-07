package com.codefarm.codefarmer.repository.member;

import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.member.Farmer;
import com.codefarm.codefarmer.entity.program.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FarmerRepository extends JpaRepository<Farmer, Long>, FarmerCustomRepository {

}
