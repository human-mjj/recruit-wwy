package com.example.recruit_page_wwy.apply;


import com.example.recruit_page_wwy.employment.Employment;
import com.example.recruit_page_wwy.employment.EmploymentRepository;
import com.example.recruit_page_wwy.employstack.EmployStack;
import com.example.recruit_page_wwy.resume.Resume;
import com.example.recruit_page_wwy.resume.ResumeRepository;
import com.example.recruit_page_wwy.resumestack.ResumeStack;
import com.example.recruit_page_wwy.user.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ApplyService {
    private final ApplyRepository applyRepository;
    private final EmploymentRepository employmentRepository;
    private final ResumeRepository resumeRepository;

    public List<ApplyResponse.UserApplyDTO> findUserApply(User sessionUser) {
        List<ApplyResponse.UserApplyDTO> userApplyDTO = applyRepository.findUserApplyById(sessionUser.getId());
        return userApplyDTO;
    }

    public List<ApplyResponse.ComApplyDTO> findComApply(User sessionUser) {
        List<ApplyResponse.ComApplyDTO> comApplyDTO = applyRepository.findComApplyById(sessionUser.getId());
        return comApplyDTO;
    }

    @Transactional
    public void apply(User sessionUser, Integer resumeId, int employmentId) {
        Resume resume = resumeRepository.findByResumeId(resumeId);
        if (resume == null) throw new RuntimeException("404 Not Found");
        if (resume.getUser().getId() != sessionUser.getId()) throw new RuntimeException("403 Forbidden");
        Employment employment = employmentRepository.findByEmploymentId(employmentId);
        if (employment == null) throw new RuntimeException("404 Not Found");

        boolean hasCommonJob = false;
        if (resume.getJob().getId() == employment.getJob().getId()) hasCommonJob = true;


        List<ResumeStack> resumeStackList = resume.getResumeStackList();
        List<EmployStack> employStackList = employment.getEmployStackList();

        // 1. resume 쪽 스킬을 Set으로 정리
        Set<String> resumeSkills = new HashSet<>();
        for (ResumeStack rs : resumeStackList) {
            resumeSkills.add(rs.getSkill());
        }

        // 2. employ 쪽을 돌면서 하나라도 겹치는 게 있는지 확인
        boolean hasCommonSkill = false;
        for (EmployStack es : employStackList) {
            if (resumeSkills.contains(es.getSkill())) {
                hasCommonSkill = true;
                break;
            }
        }

        if (!hasCommonJob || !hasCommonSkill) throw new RuntimeException("403 Forbidden");

        // employment 함수 만들면 null 없애기
        Apply apply = ApplyRequest.SaveDTO.toEntity(sessionUser, resume, employment);
        applyRepository.save(apply);
    }
}
