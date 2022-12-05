package com.codefarm.codefarmer.service.mentor;

import com.codefarm.codefarmer.domain.mentor.MentorBoardDTO;
import com.codefarm.codefarmer.entity.mentor.Mentor;
import com.codefarm.codefarmer.entity.mentor.MentorBoard;
import com.codefarm.codefarmer.repository.mentor.MentorBoardRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MentorService {
    private final JPAQueryFactory jpaQueryFactory;
    private final MentorBoardRepository mentorBoardRepository;

//    멘토 글 등록하기
    public void mentorBoardAdd(MentorBoardDTO mentorBoardDTO){
        MentorBoard mentorBoard = mentorBoardDTO.toEntity();
        mentorBoard.changeMember(mentorBoardDTO.getMemberId());
        mentorBoardRepository.save(mentorBoard);
    }
}
