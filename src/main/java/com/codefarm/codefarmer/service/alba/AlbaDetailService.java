package com.codefarm.codefarmer.service.alba;

import com.codefarm.codefarmer.domain.alba.AlbaDTO;
import com.codefarm.codefarmer.domain.alba.QAlbaDTO;
import com.codefarm.codefarmer.domain.program.ProgramDTO;
import com.codefarm.codefarmer.domain.program.QProgramDTO;
import com.codefarm.codefarmer.entity.alba.Alba;
import com.codefarm.codefarmer.entity.alba.QAlba;
import com.codefarm.codefarmer.entity.mentor.MentorBoard;
import com.codefarm.codefarmer.entity.program.Program;
import com.codefarm.codefarmer.repository.alba.AlbaRepository;
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


    //    프로그램Id로 수정 페이지 내용 출력
    public AlbaDTO showUpdate(Long albaId){
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
        albaRepository.save(albaDTO.toEntity());
    }

    public AlbaDTO albaShowOne(Long albaId){

        Alba alba = albaRepository.findById(albaId).get();


        AlbaDTO albaDTO = new AlbaDTO();

        albaDTO.setAlbaImage(alba.getAlbaImage());
        albaDTO.setAlbaAddress(alba.getAlbaAddress());
        albaDTO.setAlbaId(alba.getAlbaId());
        albaDTO.setAlbaApplyCount(alba.getAlbaApplyCount());
        albaDTO.setAlbaApplyEndDate(alba.getAlbaApplyEndDate());
        albaDTO.setAlbaApplyStartDate(alba.getAlbaApplyStartDate());
        albaDTO.setAlbaApplyTotalCount(alba.getAlbaApplyTotalCount());
        albaDTO.setAlbaBannerOne(alba.getAlbaBannerOne());
        albaDTO.setAlbaBannerTitle(alba.getAlbaBannerTitle());
        albaDTO.setAlbaMainContent(alba.getAlbaMainContent());
        albaDTO.setAlbaMainTitle(alba.getAlbaMainTitle());
        albaDTO.setAlbaPrice(alba.getAlbaPrice());
        albaDTO.setAlbaProfileContent1(alba.getAlbaProfileContent1());
        albaDTO.setAlbaProfileContent2(alba.getAlbaProfileContent2());
        albaDTO.setAlbaProfileTitle1(alba.getAlbaProfileTitle1());
        albaDTO.setAlbaProfileTitle2(alba.getAlbaProfileTitle2());
        albaDTO.setAlbaStrongContent1(alba.getAlbaStrongContent1());
        albaDTO.setAlbaStrongContent2(alba.getAlbaStrongContent2());
        albaDTO.setAlbaStrongContent3(alba.getAlbaStrongContent3());
        albaDTO.setAlbaStrongTitle1(alba.getAlbaStrongTitle1());
        albaDTO.setAlbaStrongTitle2(alba.getAlbaStrongTitle2());
        albaDTO.setAlbaStrongTitle3(alba.getAlbaStrongTitle3());
        albaDTO.setAlbaText(alba.getAlbaText());
        albaDTO.setAlbaTextTitle(alba.getAlbaTextTitle());
        albaDTO.setAlbaTitle(alba.getAlbaTitle());
        albaDTO.setAlbaTitleOne(alba.getAlbaTitleOne());
        albaDTO.setAlbaWorkDate(alba.getAlbaWorkDate());
        albaDTO.setMemberId(alba.getMember().getMemberId());

        return albaDTO;
    }


    // 알바 게시글 삭제하기
    public Long removeAlbaId(Long albaId){
        albaRepository.deleteById(albaId);
        return albaId;
    }
}
