package com.codefarm.codefarmer.service.admin;

import com.codefarm.codefarmer.domain.admin.Criteria;
import com.codefarm.codefarmer.entity.admin.Crop;
import com.codefarm.codefarmer.entity.admin.Policy;
import com.codefarm.codefarmer.repository.admin.CropRepository;
import com.codefarm.codefarmer.repository.admin.PolicyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Transactional
    public void policyUpdate(Policy policy){
        log.info("정책 -> " + policy);
        policy.update(policy);
    }

//    정책 삭제
    public Long policyDelete(Long policyId){
        policyRepository.delete(policyRepository.findById(policyId).get());
        return policyId;
    }

//    정책 목록
    public List<Policy> policyShowAll(Criteria criteria){
        return policyRepository.OrderByPolicyId(criteria);
    }

//    정책 디테일
    public Policy policyShowOne(Long policyId){
        return policyRepository.findById(policyId).get();
    }

//    정책 글 개수
    public int countByPolicy(Criteria criteria) { return policyRepository.countByPolicy(criteria); }

//    농업정보 추가
    public void cropAdd(Crop crop){
        cropRepository.save(crop);
    }

//    농업정보 수정
    public void cropUpdate(Crop crop){
        Crop cropModify = cropRepository.findById(crop.getCropId()).get();
        cropModify.update(crop);
    }

//    농업정보 삭제
    public Long cropDelete(Long cropId){
        cropRepository.delete(cropRepository.findById(cropId).get());
        return cropId;
    }

//    농업정보 목록
    public List<Crop> cropShowAll(){
        return cropRepository.OrderByCropId();
    }

//    농업정보 디테일
    public Crop cropShowOne(Long cropId){
        return cropRepository.findById(cropId).get();
    }

//    농업정보 글 개수
    public int countByCrop() { return cropRepository.countByCrop(); }
}
