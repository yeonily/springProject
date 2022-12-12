package com.codefarm.codefarmer.repository.program;

import com.codefarm.codefarmer.entity.program.ProgramFile;
import com.codefarm.codefarmer.entity.program.QProgramFile;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.codefarm.codefarmer.entity.program.QProgramFile.programFile;

@Repository
@RequiredArgsConstructor
public class ProgramFileCustomRepositoryImpl implements ProgramFileCustomRepository{
    private final JPAQueryFactory jpaQueryFactory;
    private final ProgramFileRepository programFileRepository;

    public List<ProgramFile> findByProgramId(Long programId){
        return jpaQueryFactory.selectFrom(programFile)
        .where(programFile.program.programId.eq(programId))
        .fetch();
    };

//    public void deleteByProgramId(Long programId){
//        return programFileRepository.deleteAllById(programId);
//
//    }
}
