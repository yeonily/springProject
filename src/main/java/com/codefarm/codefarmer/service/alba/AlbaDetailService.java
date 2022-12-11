package com.codefarm.codefarmer.service.alba;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.alba.MemberAlbaDTO;
import com.codefarm.codefarmer.domain.alba.QAlbaDTO;
import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.domain.program.QProgramDTO;
import com.codefarm.codefarmer.entity.admin.Crop;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.alba.QAlba;
import com.codefarm.codefarmer.entity.inquire.Inquire;
import com.codefarm.codefarmer.entity.mentor.MentorBoard;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.repository.alba.AlbaRepository;
import com.codefarm.codefarmer.repository.alba.MemberAlbaRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.codefarm.codefarmer.entity.alba.QAlba.alba;
import static com.codefarm.codefarmer.entity.program.QProgram.program;

@Service
@RequiredArgsConstructor
public class AlbaDetailService {

    private final JPAQueryFactory jpaQueryFactory;
    private final AlbaRepository albaRepository;
    private final MemberAlbaRepository memberAlbaRepository;

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
                alba.member.memberId
        )).from(alba)
                .where(alba.albaId.eq(albaId))
                .fetchOne();
    }

    // 알바 게시글 수정
    public void albaUpdate(AlbaDTO albaDTO) {
        Alba alba = albaDTO.toEntity();
        alba.update(albaDTO);
        albaRepository.deleteById(alba.getAlbaId());
        albaRepository.save(alba);
    }

    // 알바 게시글 삭제하기
    public Long removeAlbaId(Long albaId){
        albaRepository.deleteById(albaId);
        return albaId;
    }

    // 지원 신청하기
    public void albaApply(MemberAlbaDTO memberAlbaDTO){
        memberAlbaRepository.save(memberAlbaDTO.toEntity());

    }
}
