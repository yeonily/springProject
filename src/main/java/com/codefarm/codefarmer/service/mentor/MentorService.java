package com.codefarm.codefarmer.service.mentor;

import com.codefarm.codefarmer.domain.mentor.*;
import com.codefarm.codefarmer.domain.program.ProgramFileDTO;
import com.codefarm.codefarmer.domain.program.QProgramFileDTO;
import com.codefarm.codefarmer.entity.mentor.*;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.repository.mentor.MentorBoardRepository;
import com.codefarm.codefarmer.repository.mentor.MentorFileRepository;
import com.codefarm.codefarmer.repository.mentor.MentorRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.codefarm.codefarmer.entity.mentor.QMentor.mentor;
import static com.codefarm.codefarmer.entity.mentor.QMentorBoard.mentorBoard;
import static com.codefarm.codefarmer.entity.mentor.QMentorFile.mentorFile;
import static com.codefarm.codefarmer.entity.mentor.QReview.review;
import static com.codefarm.codefarmer.entity.program.QProgramFile.programFile;

@Service
@Slf4j
@RequiredArgsConstructor
public class MentorService {
    private final JPAQueryFactory jpaQueryFactory;
    private final MentorBoardRepository mentorBoardRepository;
    private final MemberRepository memberRepository;
    private final MentorRepository mentorRepository;
    private final MentorFileRepository mentorFileRepository;

//    멘토 글 등록하기
    public void mentorBoardAdd(MentorBoardDTO mentorBoardDTO){
        log.info("mentorBoardDTO:"+mentorBoardDTO.toString());
        MentorBoard mentorBoard = mentorBoardDTO.toEntity();
        mentorBoard.changeFiles(mentorBoardDTO.getFiles());
        mentorBoard.changeMember(memberRepository.findById(mentorBoardDTO.getMemberId()).get());
        mentorBoard.changeMentor(mentorRepository.findById(mentorBoardDTO.getMentorId()).get());
        mentorBoardRepository.save(mentorBoard);
        mentorBoard.getMentorFiles().stream().map(t -> mentorFileRepository.save(t));
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

    public List<MentorBoardDTO> getIntroMentor(){
        return jpaQueryFactory.select(new QMentorBoardDTO(
                mentorBoard.mentorBoardId,
                mentorBoard.mentorTitle,
                mentorBoard.member.memberNickname
                )).from(mentorBoard).orderBy(mentorBoard.updatedDate.desc())
                .limit(8)
                .fetch();
    }

    public List<MentorBoardDTO> getInfo(){
        return jpaQueryFactory.select(new QMentorBoardDTO(
                mentorBoard.mentor.mentorCrop,
                mentorBoard.mentor.mentorYear,
                mentorBoard.member.memberLocation,
                mentorBoard.member.memberNickname
                )).from(mentorBoard)
                .fetch();
}

public Long findmentorByBoardId(Long boardId){
        return jpaQueryFactory.select(mentorBoard.member.memberId).from(mentorBoard)
                .where(mentorBoard.mentorBoardId.eq(boardId))
                .fetchOne();
}

public Long findmentoInfo(Long memberID){
        return jpaQueryFactory.select(mentor.mentorId).from(mentor)
                .where(mentor.member.memberId.eq(memberID)).fetchOne();
}

public MentorDTO findBymentoId(Long mentorId){
        return jpaQueryFactory.select(new QMentorDTO(
            mentor.mentorCrop,
            mentor.mentorYear,
            mentor.member.memberLocation,
            mentor.member.memberNickname
        )).from(mentor).where(mentor.mentorId.eq(mentorId)).fetchOne();
}


    @Transactional(readOnly = true)
    public Page<MentorBoardDTO> showAll(Long mentorBoardId, Pageable pageable){
        return mentorBoardRepository.findAllByMentorBoardId(mentorBoardId, pageable);
    }


//    멘토보드 삭제하기
    public void removeMentorBoard(Long mentorBoardId){



        List<MentorFile> mentorFiles = mentorFileRepository.findByMentorBoard_MentorBoardId(mentorBoardId);

        mentorFiles.forEach(t -> mentorFileRepository.delete(t));
        mentorBoardRepository.deleteById(mentorBoardId);
    }


//첨부파일
public List<MentorFileDTO> showFiles(Long mentorBoardId){
    return jpaQueryFactory.select(new QMentorFileDTO(
            mentorFile.fileId,
            mentorFile.fileName,
            mentorFile.fileUploadPath,
            mentorFile.fileUuid,
            mentorFile.fileSize,
            mentorFile.fileImageCheck
    )).from(mentorFile)
            .where(mentorFile.mentorBoard.mentorBoardId.eq(mentorBoardId)).fetch();
}


//    mentorBoardId로 수정 페이지 내용 들고오기
    public MentorBoardDTO showUpdate(Long mentorBoardId){
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
                mentorBoard.member.memberId,
                mentorBoard.mentor.mentorId
                 )).from(mentorBoard)
                .where(mentorBoard.mentorBoardId.eq(mentorBoardId))
                .fetchOne();
    }

//    멘토 보드 게시글 수정
//    public void mentorBoardUpdate(MentorBoardDTO mentorBoardDTO){
//        MentorBoard mentorBoard = mentorBoardDTO.toEntity();
//
//        mentorBoard.changeMember(memberRepository.findById(mentorBoardDTO.getMemberId()).get());
//        if (FindmemberIdToMentor(mentorBoard.getMember().getMemberId()) != null){
//            mentorBoard.changeMentor(FindmemberIdToMentor(mentorBoard.getMember().getMemberId()));
//        }
//
//        mentorBoard.changeMentor(mentorRepository.findById(mentorBoardDTO.getMentorId()).get());
//        mentorBoard.update(mentorBoardDTO);
//        mentorBoardRepository.save(mentorBoard);
//    }



//    수정 시 파일 삭제하고 다시 입력하면 저장하기
    public void update(MentorBoardDTO mentorBoardDTO){
        MentorBoard mentorBoard = mentorBoardRepository.findById(mentorBoardDTO.getMentorBoardId()).get();
        // MentorBoard mentorBoard = mentorBoardDTO.toEntity();

//        첨부파일 수정
        mentorBoard.changeFiles(mentorBoardDTO.getFiles());
        mentorBoard.getMentorFiles().stream().map(t -> mentorFileRepository.save(t));
        List<MentorFile> mentorFiles = mentorFileRepository.findByMentorBoard_MentorBoardId(mentorBoardDTO.getMentorBoardId());
        mentorFiles.forEach(t -> mentorFileRepository.delete(t));
        List<MentorFileDTO> files = mentorBoardDTO.getFiles();
        files.stream().map(t -> t.toEntity());
        mentorBoard.changeMember(memberRepository.findById(mentorBoardDTO.getMemberId()).get());
        mentorBoard.update(mentorBoardDTO);

        mentorBoardRepository.save(mentorBoard);
    }


    //    멘토테이블에서 멤버아이디 찾기
    public Mentor FindmemberIdToMentor(Long memberId){
        List<Mentor> mentorList = mentorRepository.findAll();
        Mentor mentorRe;
        for(Mentor mentor : mentorList){
            if(mentor.getMember().getMemberId().equals(memberId)){
                mentorRe = mentor;
                return mentorRe;
            }
        }
        return null;
    }

    //멤버 아이디로 멘토아이디 찾기
    public Long findByMemberId(Long memberId){
        return jpaQueryFactory.select(mentor.mentorId).from(mentor)
                .where(mentor.member.memberId.eq(memberId))
                .fetchOne();
    }

    //    마이페이지 메인 8개 불러오기
    public List<MentorBoardDTO> findMainList(){
        return jpaQueryFactory.select(new QMentorBoardDTO(
                mentorBoard.mentorBoardId,
                mentorBoard.mentorTitle,
                mentorBoard.member.memberNickname
        )).from(mentorBoard)
                .orderBy(mentorBoard.updatedDate.desc())
                .limit(8).fetch();
    }

    //    해당하는 멘토게시물 별점 평균 가져오기
    public List<Double> findReviewAvg(Long mentorBoardId){
        return jpaQueryFactory.select(review.reviewStar.avg()).from(review)
                .where(review.mentorBoard.mentorBoardId.eq(mentorBoardId))
                .fetch();
    }

    //    해당하는 멘토게시물 댓글 총 개수 가져오기
    public List<Long> findReviewCount(Long mentorBoardId){
        return jpaQueryFactory.select(review.reviewStar.count()).from(review)
                .where(review.mentorBoard.mentorBoardId.eq(mentorBoardId))
                .fetch();
    }

    //    리뷰 programDTO에 담으려고 함
    public List<ReviewDTO> showReviews(Long mentorBoardId){
        return jpaQueryFactory.select(new QReviewDTO(
                review.reviewId,
                review.member.memberId,
                review.mentorBoard.mentorBoardId,
                review.member.memberNickname,
                review.reviewContent,
                review.reviewStar,
                review.createdDate,
                review.updatedDate
        )).from(review)
                .where(mentorFile.mentorBoard.mentorBoardId.eq(mentorBoardId)).fetch();
    }

}
