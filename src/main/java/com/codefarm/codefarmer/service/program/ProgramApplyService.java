package com.codefarm.codefarmer.service.program;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProgramApplyService {
    private final JPAQueryFactory jpaQueryFactory;

    public String findMemberNameByprogramId(Long programId){

        return null;
    }
}
