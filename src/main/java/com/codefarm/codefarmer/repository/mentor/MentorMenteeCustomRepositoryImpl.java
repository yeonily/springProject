package com.codefarm.codefarmer.repository.mentor;

import com.codefarm.codefarmer.domain.mentor.MentorMenteeDTO;
import com.codefarm.codefarmer.entity.member.QMember;
import com.codefarm.codefarmer.entity.mentor.MentorMentee;
import com.codefarm.codefarmer.entity.mentor.QMentorMentee;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
    public List<MentorMenteeDTO> selectByMentorId(Long mentorId) {
        return jpaQueryFactory.selectFrom(mentorMentee).join(mentorMentee.mentor, member).fetchJoin()
                .where(mentorMentee.mentor.memberId.eq(mentorId)).fetch()
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

}
