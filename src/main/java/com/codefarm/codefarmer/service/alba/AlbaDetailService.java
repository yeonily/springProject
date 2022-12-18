package com.codefarm.codefarmer.service.alba;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.alba.MemberAlbaDTO;
import com.codefarm.codefarmer.domain.alba.QAlbaDTO;
import com.codefarm.codefarmer.domain.member.QMemberDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.alba.MemberAlba;
import com.codefarm.codefarmer.entity.member.QMember;
import com.codefarm.codefarmer.repository.alba.AlbaRepository;
import com.codefarm.codefarmer.repository.alba.MemberAlbaRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.codefarm.codefarmer.entity.alba.QAlba.alba;
import static com.codefarm.codefarmer.entity.alba.QMemberAlba.memberAlba;
import static com.codefarm.codefarmer.entity.member.QMember.member;

@Service
@Slf4j
@RequiredArgsConstructor
public class AlbaDetailService {

    private final JPAQueryFactory jpaQueryFactory;
    private final AlbaRepository albaRepository;
    private final MemberAlbaRepository memberAlbaRepository;
    private final MemberRepository memberRepository;

    public List<AlbaDTO> showAll(){
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
        )).from(alba)
                .fetch();
    }

    public AlbaDTO showByAlbaId(Long albaId) {

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
                alba.member.memberId,
                alba.member.memberName,
                alba.member.memberEmail
        )).from(alba)
                .where(alba.albaId.eq(albaId))
                .fetchOne();
    }

    // 알바 게시글 수정
    public void albaUpdate(AlbaDTO albaDTO) {
        Alba alba = albaDTO.toEntity();
        alba.changeMember(memberRepository.findById(albaDTO.getMemberId()).get());
        alba.update(albaDTO);
        albaRepository.save(alba);
    }

    // 알바 게시글 삭제하기
    public Long removeAlbaId(Long albaId){
        albaRepository.deleteById(albaId);
        return albaId;
    }

    // 지원 신청하기
    public void albaApply(MemberAlbaDTO memberAlbaDTO){
        MemberAlba memberAlba = memberAlbaDTO.toEntity();
        memberAlba.changeMember(memberRepository.findById(memberAlbaDTO.getMemberId()).get());
        memberAlba.changeAlba(albaRepository.findById(memberAlbaDTO.getAlbaId()).get());
        memberAlbaRepository.save(memberAlba);
    }

    // 멤버ID와 알바 게시글ID를 비교해서 지원하기ID를 조회하기
    public Long albaSelect(Long albaId, Long memberId){
        return jpaQueryFactory.select(memberAlba.albaApplyId)
                .from(memberAlba)
                .where(memberAlba.member.memberId.eq(memberId).and(memberAlba.alba.albaId.eq(albaId)))
                .fetchOne();
    }

    // 지원 취소하기
    public Long albaApplyCancel(Long albaApplyId){
        memberAlbaRepository.deleteById(albaApplyId);
        return albaApplyId;
    }

    public Long memberIdFind(Long albaId){
        return jpaQueryFactory.select(alba.member.memberId)
                .from(alba)
                .where(alba.albaId.eq(albaId))
                .fetchOne();
    }
}
