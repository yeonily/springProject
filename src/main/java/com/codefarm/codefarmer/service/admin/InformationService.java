package com.codefarm.codefarmer.service.admin;

import com.codefarm.codefarmer.entity.admin.Crop;
import com.codefarm.codefarmer.entity.admin.Policy;
import com.codefarm.codefarmer.repository.admin.CropRepository;
import com.codefarm.codefarmer.repository.admin.PolicyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class InformationService {
    private final PolicyRepository policyRepository;
    private final CropRepository cropRepository;

//    정책 추가
    public void policyAdd(Policy policy){
        policyRepository.save(policy);
    }

//    정책 수정
    public void policyUpdate(Policy policy){
        policyRepository.save(policy);
    }

//    정책 삭제
    public Long policyDelete(Long policyId){
        policyRepository.delete(policyRepository.findById(policyId).get());
        return policyId;
    }

//    정책 목록
    @Transactional(readOnly = true)
    public Page<Policy> policyShowAll(Pageable pageable, String policyKeyword, String policyTitle, String policyContent){
        return policyRepository.findByPolicyKeywordContainingOrPolicyTitleContainingOrPolicyContentContaining(policyKeyword, policyTitle, policyContent, pageable);
    }

//    정책 검색
    @Transactional(readOnly = true)
    public Page<Policy> policySearchShowAll(Pageable pageable, String keyword, String policyTitle, String policyContent){
        if (keyword.equals("t")){
            return policyRepository.findByPolicyTitleContaining(policyTitle, pageable);
        } else if (keyword.equals("c")){
            return policyRepository.findByPolicyContentContaining(policyContent, pageable);
        } else {
            return policyRepository.findByPolicyTitleContainingOrPolicyContentContaining(policyTitle, policyContent, pageable);
        }
    }

//    정책 디테일
    public Policy policyShowOne(Long policyId){
        return policyRepository.findById(policyId).get();
    }

//    정책 글 개수
    public int countByPolicy() { return policyRepository.countByPolicy(); }

//    농업정보 추가
    public void cropAdd(Crop crop){
        cropRepository.save(crop);
    }

//    농업정보 수정
    public void cropUpdate(Crop crop){
        cropRepository.save(crop);
    }

//    농업정보 삭제
    public Long cropDelete(Long cropId){
        cropRepository.delete(cropRepository.findById(cropId).get());
        return cropId;
    }

//    농업정보 목록
    @Transactional(readOnly = true)
    public Page<Crop> cropShowAll(Pageable pageable, String cropKeyword, String cropTitle, String cropContent){
        return cropRepository.findByCropKeywordContainingOrCropTitleContainingOrCropContentContaining(cropKeyword, cropTitle, cropContent, pageable);
    }

//    농업정보 검색
    @Transactional(readOnly = true)
    public Page<Crop> cropSearchShowAll(Pageable pageable, String keyword, String cropTitle, String cropContent){
        if (keyword.equals("t")){
            return cropRepository.findByCropTitleContaining(cropTitle, pageable);
        } else if (keyword.equals("c")){
            return cropRepository.findByCropContentContaining(cropContent, pageable);
        } else {
            return cropRepository.findByCropTitleContainingOrCropContentContaining(cropTitle, cropContent, pageable);
        }
    }

//    농업정보 디테일
    public Crop cropShowOne(Long cropId){
        return cropRepository.findById(cropId).get();
    }

//    농업정보 글 개수
    public int countByCrop() { return cropRepository.countByCrop(); }
}
