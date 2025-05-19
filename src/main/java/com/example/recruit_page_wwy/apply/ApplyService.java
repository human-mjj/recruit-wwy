package com.example.recruit_page_wwy.apply;


import com.example.recruit_page_wwy._core.error.ex.ExceptionApi403;
import com.example.recruit_page_wwy._core.error.ex.ExceptionApi404;
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

    public ApplyResponse.UserApplyListDTO findUserApply(User sessionUser) {
        if (sessionUser.getRole() != 0) throw new ExceptionApi403("403 Forbidden");
        ApplyResponse.UserApplyListDTO userApplyDTO = applyRepository.findUserApplyById(sessionUser.getId(), sessionUser);
        return userApplyDTO;
    }

    public ApplyResponse.ComApplyListDTO findComApply(User sessionUser) {
        if (sessionUser.getRole() != 1) throw new ExceptionApi403("403 Forbidden");
        ApplyResponse.ComApplyListDTO comApplyDTO = applyRepository.findComApplyById(sessionUser.getId(), sessionUser);
        return comApplyDTO;
    }

    @Transactional
    public ApplyResponse.DTO apply(User sessionUser, ApplyRequest.SaveDTO reqDTO) {
        Resume resume = resumeRepository.findByResumeId(reqDTO.getResumeId());
        if (resume == null) throw new ExceptionApi404("404 Not Found");
        if (resume.getUser().getId() != sessionUser.getId()) throw new ExceptionApi403("403 Forbidden");
        Employment employment = employmentRepository.findByEmploymentId(reqDTO.getEmploymentId());
        if (employment == null) throw new ExceptionApi404("404 Not Found");

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

        if (!hasCommonJob || !hasCommonSkill) throw new ExceptionApi403("403 Forbidden");

        // employment 함수 만들면 null 없애기
        Apply apply = ApplyRequest.SaveDTO.toEntity(sessionUser, resume, employment);
        Apply applyPS = applyRepository.save(apply);

        return new ApplyResponse.DTO(applyPS);
    }

    @Transactional
    public ApplyResponse.DTO updateProgress(Integer applyId, String progress) {
        Apply applyPS = applyRepository.update(applyId, progress);
        return new ApplyResponse.DTO(applyPS);
    }
}
