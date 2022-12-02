package com.codefarm.codefarmer.service.admin;

import com.codefarm.codefarmer.entity.admin.Crop;
import com.codefarm.codefarmer.entity.admin.Policy;
import com.codefarm.codefarmer.repository.admin.CropRepository;
import com.codefarm.codefarmer.repository.admin.PolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        Policy policyModify = policyRepository.findById(policy.getPolicyId()).get();
        policyModify.update(policy);
    }

//    정책 삭제
    public Long policyDelete(Long policyId){
        policyRepository.delete(policyRepository.findById(policyId).get());
        return policyId;
    }

//    정책 목록
    public List<Policy> policySelectAll(){
        return policyRepository.findAll();
    }

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
    public List<Crop> cropSelectAll(){
        return cropRepository.findAll();
    }
}
