package com.codefarm.codefarmer.repository.member;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.alba.MemberAlbaDTO;
import com.codefarm.codefarmer.domain.alba.QAlbaDTO;
import com.codefarm.codefarmer.domain.alba.QMemberAlbaDTO;
import com.codefarm.codefarmer.domain.board.BoardDTO;
import com.codefarm.codefarmer.domain.board.QBoardDTO;
import com.codefarm.codefarmer.domain.inquire.InquireAnswerDTO;
import com.codefarm.codefarmer.domain.inquire.InquireDTO;
import com.codefarm.codefarmer.domain.inquire.QInquireAnswerDTO;
import com.codefarm.codefarmer.domain.inquire.QInquireDTO;
import com.codefarm.codefarmer.domain.mentor.MentorDTO;
import com.codefarm.codefarmer.domain.program.*;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.alba.MemberAlba;
import com.codefarm.codefarmer.entity.alba.QAlba;
import com.codefarm.codefarmer.entity.alba.QMemberAlba;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.board.QBoard;
import com.codefarm.codefarmer.entity.board.QReply;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.inquire.QInquire;
import com.codefarm.codefarmer.entity.inquire.QInquireAnswer;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.member.QMember;
import com.codefarm.codefarmer.entity.program.*;
import com.codefarm.codefarmer.type.Status;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static com.codefarm.codefarmer.entity.alba.QAlba.*;
import static com.codefarm.codefarmer.entity.alba.QAlba.alba;
import static com.codefarm.codefarmer.entity.alba.QMemberAlba.*;
import static com.codefarm.codefarmer.entity.board.QBoard.board;
import static com.codefarm.codefarmer.entity.board.QReply.*;
import static com.codefarm.codefarmer.entity.inquire.QInquire.*;
import static com.codefarm.codefarmer.entity.member.QMember.*;
import static com.codefarm.codefarmer.entity.program.QMemberProgram.memberProgram;
import static com.codefarm.codefarmer.entity.program.QProgram.*;
import static com.codefarm.codefarmer.entity.program.QProgram.program;
import static com.codefarm.codefarmer.entity.program.QProgramFile.*;

@Repository
@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Integer duplicateNick(String nickname) {
        return Math.toIntExact(jpaQueryFactory.select(member.memberNickname.count())
                .from(member)
                .where(member.memberNickname.eq(nickname))
                .fetchOne());
    }

    @Override
    public List<MemberProgramDTO> selectMyProgramApplyers(Long memberId, Long programId) {
        return jpaQueryFactory.select(memberProgram)
                .from(memberProgram)
                .join(program).on((program.member.memberId.eq(memberId)).and(memberProgram.program.programId.eq(programId)))
                .fetch().stream()
                .map(memberProgram -> new MemberProgramDTO(
                        memberProgram.getProgramApplyId(),
                        memberProgram.getProgramStatus(),
                        memberProgram.getProgramApplyCount(),
                        memberProgram.getProgramPayment(),
                        memberProgram.getProgramApplyName(),
                        memberProgram.getProgramApplyPhoneNum(),
                        memberProgram.getProgramApplyEmail(),
                        memberProgram.getProgramApplyLocation(),
                        memberProgram.getProgramApplyBirth()))
                .collect(Collectors.toList());
    }

    @Override
    public List<MemberAlbaDTO> selectMyAlbaApplyers(Long memberId,Long albaId) {
        return jpaQueryFactory.select(memberAlba)
                .from(memberAlba)
                .join(alba).on((alba.member.memberId.eq(memberId)).and(memberAlba.alba.albaId.eq(albaId)))
                .fetch().stream()
                .map(memberAlba -> new MemberAlbaDTO(
                        memberAlba.getAlbaApplyId(),
                        memberAlba.getMember().getMemberId(),
                        memberAlba.getMemberStatus(),
                        memberAlba.getMember().getMemberName(),
                        memberAlba.getMember().getMemberEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public Integer duplicateOauth(String oauthId) {
        return Math.toIntExact(jpaQueryFactory.select(member.memberOauthId.count())
                .from(member)
                .where(member.memberOauthId.eq(oauthId))
                .fetchOne());
    }

    @Override
    public List<AlbaDTO> selectMyAlba(Long memberId) {
        return jpaQueryFactory.select(new QAlbaDTO(
                alba.albaId,
                alba.albaTitle,
                alba.albaImage,
                alba.albaTitleOne,
                alba.albaApplyStartDate,
                alba.albaApplyEndDate,
                alba.albaWorkDate,
                alba.albaApplyCount,
                alba.albaApplyTotalCount,
                alba.albaAddress,
                alba.albaPrice,
                alba.albaMainTitle,
                alba.albaMainContent,
                alba.albaStrongTitle1,
                alba.albaStrongContent1,
                alba.albaStrongTitle2,
                alba.albaStrongContent2,
                alba.albaStrongTitle3,
                alba.albaStrongContent3,
                alba.albaBannerTitle,
                alba.albaBannerOne,
                alba.albaTextTitle,
                alba.albaText,
                alba.albaProfileTitle1,
                alba.albaProfileContent1,
                alba.albaProfileTitle2,
                alba.albaProfileContent2,
                alba.member.memberId
                )).from(alba).where(alba.member.memberId.eq(memberId))
                .fetch();

    }

    @Override
    public List<ProgramDTO> selectMyProgram(Long memberId) {
        return  jpaQueryFactory.select(new QProgramDTO(
                program.programId,
                program.programCrop,
                program.programType,
                program.programTarget1,
                program.programTarget2,
                program.programTarget3,
                program.programTarget4,
                program.programTitle,
                program.programTitleSub,
                program.programLevel,
                program.programResult1,
                program.programResult2,
                program.programResult3,
                program.programResult4,
                program.programFarmerInfo,
                program.programInfoTitle1,
                program.programInfoTitle2,
                program.programInfoTitle3,
                program.programInfoTitle4,
                program.programInfoContent1,
                program.programInfoContent2,
                program.programInfoContent3,
                program.programInfoContent4,
                program.programWorkDate,
                program.programWorkStartTime,
                program.programWorkEndTime,
                program.programApplyStartDate,
                program.programApplyEndDate,
                program.programApplyCount,
                program.programApplyTotalCount,
                program.programPrice,
                program.programLocation,
                program.programInquire,
                program.member.memberId
        )).from(program)
                .where(program.member.memberId.eq(memberId))
                .fetch();
    }

    @Override
    public List<BoardDTO> selectMyBoard(Long memberId) {
        return jpaQueryFactory.selectFrom(board).join(board.replies, reply).fetchJoin()
                .where(board.member.memberId.eq(memberId)).fetch()
                .stream()
                .map(board -> new BoardDTO(
                        board.getBoardId(),
                        board.getBoardTitle(),
                        board.getBoardContent(),
                        board.getBoardViewCount(),
                        board.getCreatedDate(),
                        board.getReplies().size()))
                .collect(Collectors.toList());
    }

    @Override
    public List<InquireDTO> selectMyInquire(Long memberId) {
        return jpaQueryFactory.select(new QInquireDTO(
                inquire.inquireId,
                inquire.inquireQTitle,
                inquire.inquireQContent,
                inquire.inquireStatus,
                inquire.member.memberId,
                inquire.createdDate
        )).from(inquire).where(inquire.member.memberId.eq(memberId)).fetch();
    }

    @Override
    public List<MemberAlbaDTO> selectMyAlbaApply(Long memberId) {
        return jpaQueryFactory.selectFrom(memberAlba).join(memberAlba.alba, alba).fetchJoin()
                .where(memberAlba.member.memberId.eq(memberId)).fetch()
                .stream()
                .map(memberAlba -> new MemberAlbaDTO(
                        memberAlba.getAlbaApplyId(),
                        memberAlba.getAlba().getAlbaId(),
                        memberAlba.getMemberStatus(),
                        memberAlba.getAlba().getAlbaWorkDate(),
                        memberAlba.getAlba().getAlbaMainTitle(),
                        memberAlba.getAlba().getAlbaPrice(),
                        memberAlba.getAlba().getAlbaAddress()
                )).collect(Collectors.toList());
    }

    @Override
    public List<ProgramDTO> selectMyProgramApply(Long memberId) {
        return jpaQueryFactory.select(program).from(memberProgram, program, programFile).where(program.programId.eq(memberProgram.program.programId).and(program.programId.eq(programFile.program.programId))
                .and(memberProgram.member.memberId.eq(memberId))).fetch()
                .stream().map(program -> new ProgramDTO(
                        program.getProgramId(),
                        program.getProgramType(),
                        program.getProgramTitle(),
                        program.getProgramWorkDate(),
                        program.getProgramApplyStartDate(),
                        program.getProgramLocation(),
                        program.getProgramFiles().stream().map(programFile -> new ProgramFileDTO()).collect(Collectors.toList()),
                        program.getMemberPrograms().stream().map(memberProgram -> new MemberProgramDTO()).collect(Collectors.toList()),
                        program.getMemberPrograms().stream().map(memberProgram -> memberProgram.getProgramStatus()).collect(Collectors.toList()),
                        program.getMemberPrograms().stream().map(memberProgram -> memberProgram.getProgramApplyId()).collect(Collectors.toList())
                )).collect(Collectors.toList());

//                jpaQueryFactory.selectFrom(program).innerJoin(program, programFile.program)
//                .leftJoin(program, memberProgram.program)
//                .where(program.member.memberId.eq(memberId)).fetch()
//                .stream()
//                .map(program -> new ProgramDTO(
//                        program.getProgramId(),
//                        program.getProgramType(),
//                        program.getProgramTitle(),
//                        program.getProgramWorkDate(),
//                        program.getProgramApplyStartDate(),
//                        program.getProgramLocation(),
//                        program.getProgramFiles(),
//                        program.get
//                )).collect(Collectors.toList());

//        this.programId = programId;
//        this.programType = programType;
//        this.programTitle = programTitle;
//        this.programWorkDate = programWorkDate;
//        this.programApplyStartDate = programApplyStartDate;
//        this.programLocation = programLocation;
//        this.files = files;
//        this.programStatus = programStatus;
//        this.programApplyId = programApplyId;
    }

    @Override
    public List<MemberProgramDTO> selectMyPay(Long memberId) {
        return jpaQueryFactory.selectFrom(memberProgram).join(memberProgram.program, program).fetchJoin()
                .where(memberProgram.member.memberId.eq(memberId)).fetch()
                .stream()
                .map(memberProgram -> new MemberProgramDTO(
                        memberProgram.getProgramApplyId(),
                        memberProgram.getProgramStatus(),
                        memberProgram.getProgramPayment(),
                        memberProgram.getUpdatedDate(),
                        memberProgram.getProgram().getProgramTitle()
                )).collect(Collectors.toList());
    }


}


