package com.codefarm.codefarmer.service.alba;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.alba.MemberAlbaDTO;
import com.codefarm.codefarmer.domain.alba.QAlbaDTO;
import com.codefarm.codefarmer.domain.program.MemberProgramDTO;
import com.codefarm.codefarmer.entity.admin.Criteria;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.member.Member;
import com.codefarm.codefarmer.repository.alba.AlbaCustomRepositoryImpl;
import com.codefarm.codefarmer.repository.alba.AlbaRepository;
import com.codefarm.codefarmer.repository.member.MemberRepository;
import com.codefarm.codefarmer.type.MemberType;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static com.codefarm.codefarmer.entity.alba.QAlba.alba;


@Service
@RequiredArgsConstructor
public class AlbaListService {

    private final JPAQueryFactory jpaQueryFactory;
    private final AlbaRepository albaRepository;
    private final MemberRepository memberRepository;
    private final AlbaCustomRepositoryImpl albaCustomRepository;

    // 곧 마감 아르바이트 8개 정렬
    public List<AlbaDTO> showListByRecentEndDate() {
        LocalDateTime localDateTime = LocalDateTime.now();
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
                .where(alba.albaApplyStartDate.before(localDateTime).and(alba.albaApplyEndDate.after(localDateTime)))
                .limit(8)
                .orderBy(alba.albaApplyEndDate.asc())
                .fetch();
    }

    // 알바 게시글 정렬에 맞게 개수
    public Long showCount(){
        LocalDateTime localDateTime = LocalDateTime.now();
       return jpaQueryFactory.select(alba.albaId.count())
                .from(alba)
                .where(alba.albaApplyStartDate.before(localDateTime).and(alba.albaApplyEndDate.after(localDateTime)))
                .fetchOne();
    }

    //    알바게시글 작성
    public void saveAll(AlbaDTO albaDTO) {
        Alba alba = albaDTO.toEntity();
        alba.changeMember(memberRepository.findById(albaDTO.getMemberId()).get());
       albaRepository.save(alba);}


    //최신순
    public Page<AlbaDTO> showAlbaNew(Pageable pageable){
        return albaCustomRepository.findByAlbaNewList(pageable);
    }

    // 시급순
    public Page<AlbaDTO> showAlbaPay(Pageable pageable){
        return albaCustomRepository.findByAlbaPayList(pageable);
    }

    // 마감순
    public Page<AlbaDTO> showAlbaEnd(Pageable pageable){
        return albaCustomRepository.findByAlbaEndList(pageable);
    }

    //    타입 구별
    public String getMemberType(Long memberId) {
        Optional<Member> memberType = memberRepository.findById(memberId);
        if (memberType.get().getMemberType() == MemberType.FARMER) {
            return "FARMER";
        } else if (memberType.get().getMemberType() == MemberType.USER) {
            return "USER";
        } else if (memberType.get().getMemberType() == MemberType.MENTOR) {
            return "MENTOR";
        } else if(memberType.get().getMemberType() == MemberType.MENTEE){
            return "MENTEE";
        }else{
            return "ADMIN";
        }
    }
}