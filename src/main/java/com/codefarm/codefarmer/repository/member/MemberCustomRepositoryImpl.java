package com.codefarm.codefarmer.repository.member;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.alba.QAlbaDTO;
import com.codefarm.codefarmer.domain.mentor.MentorDTO;
import com.codefarm.codefarmer.domain.program.MemberProgramDTO;
import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.domain.program.QMemberProgramDTO;
import com.codefarm.codefarmer.domain.program.QProgramDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.alba.MemberAlba;
import com.codefarm.codefarmer.entity.alba.QAlba;
import com.codefarm.codefarmer.entity.alba.QMemberAlba;
import com.codefarm.codefarmer.entity.board.Board;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.inquire.QInquire;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.entity.member.QMember;
import com.codefarm.codefarmer.entity.program.MemberProgram;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.entity.program.QMemberProgram;
import com.codefarm.codefarmer.type.Status;
import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.codefarm.codefarmer.entity.alba.QAlba.*;
import static com.codefarm.codefarmer.entity.alba.QAlba.alba;
import static com.codefarm.codefarmer.entity.alba.QMemberAlba.*;
import static com.codefarm.codefarmer.entity.board.QBoard.board;
import static com.codefarm.codefarmer.entity.inquire.QInquire.*;
import static com.codefarm.codefarmer.entity.member.QMember.*;
import static com.codefarm.codefarmer.entity.program.QMemberProgram.memberProgram;
import static com.codefarm.codefarmer.entity.program.QProgram.program;

@Repository
@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements MemberCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Integer checkNick(String nickname) {
        return Math.toIntExact(jpaQueryFactory.select(member.memberNickname.count())
                .from(member)
                .where(member.memberNickname.eq(nickname))
                .fetchOne());
    }

    @Override
    public List<MemberProgram> findMyProgramApplyers(Long memberId) {
        return jpaQueryFactory.select(memberProgram)
                .from(memberProgram)
                .join(program).on((program.member.memberId.eq(memberId)).and(memberProgram.program.programId.eq(program.programId)))
                .fetch();
    }
    @Override
    public List<MemberAlba> findMyAlbaApplyers(Long memberId) {
        return jpaQueryFactory.select(memberAlba)
                .from(memberAlba)
                .join(alba).on((alba.member.memberId.eq(memberId)).and(memberAlba.alba.albaId.eq(alba.albaId)))
                .fetch();
    }

    @Override
    public Integer checkOauth(String oauthId) {
        return Math.toIntExact(jpaQueryFactory.select(member.memberOauthId.count())
                .from(member)
                .where(member.memberOauthId.eq(oauthId))
                .fetchOne());
    }

    @Override
    public List<AlbaDTO> findMyAlba(Long memberId) {
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
    public List<ProgramDTO> findMyProgram(Long memberId) {
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
    public List<Board> findMyBoard(Long memberId) {
        return jpaQueryFactory.select(board)
                .from(board).join(board.member)
                .where(board.member.memberId.eq(memberId))
                .fetchJoin().fetch();
    }

    @Override
    public List<Inquire> findMyInquire(Long memberId) {
        return jpaQueryFactory.select(inquire)
                .from(inquire).join(inquire.member)
                .where(inquire.member.memberId.eq(memberId))
                .fetchJoin().fetch();
    }

}


