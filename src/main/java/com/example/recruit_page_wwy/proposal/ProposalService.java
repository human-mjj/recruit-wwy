package com.example.recruit_page_wwy.proposal;


import com.example.recruit_page_wwy.employment.Employment;
import com.example.recruit_page_wwy.employment.EmploymentRepository;
import com.example.recruit_page_wwy.resume.Resume;
import com.example.recruit_page_wwy.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ProposalService {
    private final ProposalRepository proposalRepository;
    private final EmploymentRepository employmentRepository;

    @Transactional
    public void recommend(int resumeId, ProposalRequest.SaveDTO saveDTO, User sessionUser) {
        Resume resumeRef = Resume.builder().id(saveDTO.getResumeId()).build(); // 가능
        Employment employment = employmentRepository.findById(saveDTO.getEmploymentId());
        if (employment == null) throw new RuntimeException("채용공고를 찾을 수 없습니다.");

        Proposal proposal = Proposal.builder()
                .user(sessionUser)
                .resume(resumeRef)
                .employment(employment) // 실제 엔티티
                .build();

        proposalRepository.save(proposal);
    }
}
