package com.codefarm.codefarmer.service.mentor;

import com.codefarm.codefarmer.domain.mentor.MentorBoardDTO;
import com.codefarm.codefarmer.domain.mentor.QMentorBoardDTO;
import com.codefarm.codefarmer.entity.mentor.Mentor;
import com.codefarm.codefarmer.entity.mentor.MentorBoard;
import com.codefarm.codefarmer.entity.mentor.QMentorBoard;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.mentor.MentorBoardRepository;
import com.codefarm.codefarmer.repository.mentor.MentorRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.codefarm.codefarmer.entity.mentor.QMentorBoard.mentorBoard;

@Service
@RequiredArgsConstructor
public class MentorService {
    private final JPAQueryFactory jpaQueryFactory;
    private final MentorBoardRepository mentorBoardRepository;
    private final MemberRepository memberRepository;
    private final MentorRepository mentorRepository;

//    멘토 글 등록하기
    public void mentorBoardAdd(MentorBoardDTO mentorBoardDTO){
        MentorBoard mentorBoard = mentorBoardDTO.toEntity();
        mentorBoard.changeMember(memberRepository.findById(mentorBoardDTO.getMemberId()).get());
        mentorBoard.changeMentor(mentorRepository.findById(mentorBoardDTO.getMentorId()).get());
        mentorBoardRepository.save(mentorBoard);
    }

//    멘토 글 상세페이지에서 확인하기
    public MentorBoardDTO showDetailMentorBoard(Long mentorBoardNumber){
        MentorBoard mentorBoard = mentorBoardRepository.findById(mentorBoardNumber).get();

        MentorBoardDTO mentorBoardDTO = new MentorBoardDTO();

        mentorBoardDTO.setMentorCareer(mentorBoard.getMentorCareer());
        mentorBoardDTO.setMentorExCareer(mentorBoard.getMentorExCareer());
        mentorBoardDTO.setMentorStrongTitle1(mentorBoard.getMentorStrongTitle1());
        mentorBoardDTO.setMentorStrongContent1(mentorBoard.getMentorStrongContent1());
        mentorBoardDTO.setMentorStrongTitle2(mentorBoard.getMentorStrongTitle2());
        mentorBoardDTO.setMentorStrongContent2(mentorBoard.getMentorStrongContent2());
        mentorBoardDTO.setMentorStrongTitle3(mentorBoard.getMentorStrongTitle3());
        mentorBoardDTO.setMentorStrongContent3(mentorBoard.getMentorStrongContent3());
        mentorBoardDTO.setMentorTitle(mentorBoard.getMentorTitle());
        mentorBoardDTO.setMentorTitleSub(mentorBoard.getMentorTitleSub());
        mentorBoardDTO.setMentorTextTitle(mentorBoard.getMentorTextTitle());
        mentorBoardDTO.setMentorTextContent(mentorBoard.getMentorTextContent());
        mentorBoardDTO.setMentorBoardId(mentorBoard.getMentorBoardId());
        mentorBoardDTO.setMentorId(mentorBoard.getMember().getMemberId());

        return mentorBoardDTO;
    }

//    목록 들고오기
    public List<MentorBoardDTO> getMentorList(){
        return jpaQueryFactory.select(new QMentorBoardDTO(
                mentorBoard.mentorBoardId,
                mentorBoard.mentorCareer,
                mentorBoard.mentorExCareer,
                mentorBoard.mentorStrongTitle1,
                mentorBoard.mentorStrongContent1,
                mentorBoard.mentorStrongTitle2,
                mentorBoard.mentorStrongContent2,
                mentorBoard.mentorStrongTitle3,
                mentorBoard.mentorStrongContent3,
                mentorBoard.mentorTitle,
                mentorBoard.mentorTitleSub,
                mentorBoard.mentorTextTitle,
                mentorBoard.mentorTextContent,
                mentorBoard.mentor.mentorCrop
                )).from(mentorBoard)
                .orderBy(mentorBoard.updatedDate.desc())
                .fetch();
    }

    @Transactional(readOnly = true)
    public Page<MentorBoardDTO> showAll(Long mentorBoardId, Pageable pageable){
        return mentorBoardRepository.findAllByMentorBoardId(mentorBoardId, pageable);
    }

//    멘토 목록에 닉네임 갖고오기
//    멘토 목록에 제목 갖고오기  (닉넴이랑 제목 둘다 목록 한번에 묶을 필요있음)

//    멘토보드 삭제하기
    public void removeMentorBoard(Long mentorBoardId){
        mentorBoardRepository.deleteById(mentorBoardId);
    }


}
