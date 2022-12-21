package com.codefarm.codefarmer.repository.mentor;

import com.codefarm.codefarmer.domain.mentor.MentorMenteeDTO;
import com.codefarm.codefarmer.entity.member.QMember;
import com.codefarm.codefarmer.entity.mentor.MentorMentee;
import com.codefarm.codefarmer.entity.mentor.QMentorMentee;
import com.codefarm.codefarmer.type.Status;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static com.codefarm.codefarmer.entity.member.QMember.*;
import static com.codefarm.codefarmer.entity.mentor.QMentorMentee.*;

@Repository
@RequiredArgsConstructor
public class MentorMenteeCustomRepositoryImpl implements MentorMenteeCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MentorMenteeDTO> selectByMenteeId(Long menteeId) {
        return jpaQueryFactory.selectFrom(mentorMentee).join(mentorMentee.mentee, member).fetchJoin()
                .where(mentorMentee.mentee.memberId.eq(menteeId)).fetch()
                .stream().map(mentorMentee -> new MentorMenteeDTO(
                        mentorMentee.getMentorMenteeId(),
                        mentorMentee.getMentor().getMemberId(),
                        mentorMentee.getMentee().getMemberId(),
                        mentorMentee.getMenteeStatus(),
                        mentorMentee.getUpdatedDate(),
                        mentorMentee.getMentor().getMemberNickname(),
                        mentorMentee.getMentee().getMemberNickname(),
                        mentorMentee.getMenteeComment()
                )).collect(Collectors.toList());
    }

    @Override
    public List<MentorMenteeDTO> selectByMentorId(Long mentorId, Status status) {
        return jpaQueryFactory.selectFrom(mentorMentee).join(mentorMentee.mentor, member).fetchJoin()
                .where((mentorMentee.mentor.memberId.eq(mentorId)), (statusEqs(status))).fetch()
                .stream().map(mentorMentee -> new MentorMenteeDTO(
                        mentorMentee.getMentorMenteeId(),
                        mentorMentee.getMentor().getMemberId(),
                        mentorMentee.getMentee().getMemberId(),
                        mentorMentee.getMenteeStatus(),
                        mentorMentee.getUpdatedDate(),
                        mentorMentee.getMentor().getMemberNickname(),
                        mentorMentee.getMentee().getMemberNickname(),
                        mentorMentee.getMenteeComment()
                )).collect(Collectors.toList());
    }


    @Override
    public List<MentorMenteeDTO> findByAdminMentee(Long mentorId) {
        return jpaQueryFactory.selectFrom(mentorMentee).join(mentorMentee.mentor, member).fetchJoin()
                .where(mentorMentee.mentor.memberId.eq(mentorId).and(mentorMentee.menteeStatus.eq(Status.CONFIRM))).fetch()
                .stream().map(mentorMentee -> new MentorMenteeDTO(
                        mentorMentee.getMentorMenteeId(),
                        mentorMentee.getMentee().getMemberId(),
                        mentorMentee.getMentee().getMemberName(),
                        mentorMentee.getMentee().getMemberNickname(),
                        mentorMentee.getMentee().getMemberPhone()
                )).collect(Collectors.toList());
    }


    private BooleanExpression statusEq(Status status){
        return status.name().equals("CONFIRM") ? mentorMentee.menteeStatus.eq(status) : null;
    }

    private BooleanExpression statusNotEq(Status status){
        return !(status.name().equals("CONFIRM")) ? mentorMentee.menteeStatus.eq(status) : null;
    }

    private BooleanExpression statusEqOrNotEq(Status status){
        return statusNotEq(status).and(statusEq(status));
    }

    private BooleanExpression statusEqs(Status status){
        return !(status.name().equals("CONFIRM")) ? mentorMentee.menteeStatus.eq(status) : mentorMentee.menteeStatus.eq(status);
    }

    BooleanBuilder builder = new BooleanBuilder();
}
