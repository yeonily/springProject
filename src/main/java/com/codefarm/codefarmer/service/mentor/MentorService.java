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

import static com.codefarm.codefarmer.entity.mentor.QMentor.mentor;
import static com.codefarm.codefarmer.entity.mentor.QMentorBoard.mentorBoard;
import static com.codefarm.codefarmer.entity.mentor.QMentorFile.mentorFile;
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
                mentorBoard.member.memberId
                 )).from(mentorBoard)
                .where(mentorBoard.mentorBoardId.eq(mentorBoardId))
                .fetchOne();
    }

//    수정 시 파일 삭제하고 다시 입력하면 저장하기
    public void update(MentorBoardDTO mentorBoardDTO){
        MentorBoard mentorBoard = mentorBoardDTO.toEntity();

        mentorBoard.changeFiles(mentorBoardDTO.getFiles());
        mentorBoard.changeMember(memberRepository.findById(mentorBoardDTO.getMemberId()).get());
        mentorBoard.changeMentor(mentorRepository.findById(mentorBoardDTO.getMentorId()).get());

        mentorBoard.getMentorFiles().stream().map(t -> mentorFileRepository.save(t));

        List<MentorFile> mentorFiles = mentorFileRepository.findByMentorBoard_MentorBoardId(mentorBoardDTO.getMentorBoardId());
        mentorFiles.forEach(t -> mentorFileRepository.delete(t));

        List<MentorFileDTO> files = mentorBoardDTO.getFiles();
        files.stream().map(t -> t.toEntity());

        mentorBoard.update(mentorBoardDTO);
        mentorBoardRepository.save(mentorBoard);
    }

    //멤버 아이디로 멘토아이디 찾기
    public Long findByMemberId(Long memberId){
        return jpaQueryFactory.select(mentor.mentorId).from(mentor)
                .where(mentor.member.memberId.eq(memberId))
                .fetchOne();
    }

}
