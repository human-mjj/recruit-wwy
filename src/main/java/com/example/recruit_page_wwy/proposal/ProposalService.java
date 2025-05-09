package com.example.recruit_page_wwy.proposal;


import com.example.recruit_page_wwy.employment.Employment;
import com.example.recruit_page_wwy.employment.EmploymentRepository;
import com.example.recruit_page_wwy.employstack.EmployStack;
import com.example.recruit_page_wwy.resume.Resume;
import com.example.recruit_page_wwy.resume.ResumeRepository;
import com.example.recruit_page_wwy.resumestack.ResumeStack;
import com.example.recruit_page_wwy.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class ProposalService {
    private final ProposalRepository proposalRepository;
    private final ResumeRepository resumeRepository;
    private final EmploymentRepository employmentRepository;

    @Transactional
    public void recommend(int resumeId, ProposalRequest.SaveDTO saveDTO, User sessionUser) {
        Resume resume = resumeRepository.findByResumeId(resumeId);
        if (resume == null) throw new RuntimeException("404 Not Found");
        Employment employment = employmentRepository.findByEmploymentId(saveDTO.getEmploymentId());
        if (employment == null) throw new RuntimeException("404 Not Found");
        if (employment.getUser().getId() != sessionUser.getId()) throw new RuntimeException("403 Forbidden");

        boolean hasCommonJob = false;
        if (employment.getJob().getId() == resume.getJob().getId()) hasCommonJob = true;

        boolean hasCommonSkill = false;
        Set<String> resumeSkills = new HashSet<>();
        for (ResumeStack rs : resume.getResumeStackList()) {
            resumeSkills.add(rs.getSkill());
        }
        for (EmployStack es : employment.getEmployStackList()) {
            if (resumeSkills.contains(es.getSkill())) {
                hasCommonSkill = true;
                break;
            }
        }

        if (!hasCommonJob || !hasCommonSkill) throw new RuntimeException("403 Forbidden");
        Proposal proposal = saveDTO.toEntity(sessionUser, resume, employment);
        proposalRepository.save(proposal);
    }
}
