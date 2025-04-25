package com.example.recruit_page_wwy.apply;


import com.example.recruit_page_wwy.employment.EmploymentRepository;
import com.example.recruit_page_wwy.resume.Resume;
import com.example.recruit_page_wwy.resume.ResumeRepository;
import com.example.recruit_page_wwy.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ApplyService {
    private final ApplyRepository applyRepository;
    private final EmploymentRepository employmentRepository;
    private final ResumeRepository resumeRepository;

    @Transactional
    public void apply(User sessionUser, ApplyRequest.SaveDTO saveDTO) {
        Resume resume = resumeRepository.findByResumeId(saveDTO.getResumeId());
        if (resume == null) throw new RuntimeException("이력서를 찾을 수 없습니다.");
//        Employment employment = employmentRepository.findById(saveDTO.getEmploymentId());
//        if (employment == null) throw new RuntimeException("채용 공고를 찾을 수 없습니다.");

        // employment 함수 만들면 null 없애기
        Apply apply = saveDTO.toEntity(sessionUser, resume, null);
        applyRepository.save(apply);
    }

    public List<ApplyResponse.UserApplyDTO> findUserApply(User sessionUser) {
        List<ApplyResponse.UserApplyDTO> userApplyDTO = applyRepository.findUserApplyById(sessionUser.getId());
        return userApplyDTO;
    }

    public List<ApplyResponse.ComApplyDTO> findComApply(User sessionUser) {
        List<ApplyResponse.ComApplyDTO> comApplyDTO = applyRepository.findComApplyById(sessionUser.getId());
        return comApplyDTO;
    }
}
