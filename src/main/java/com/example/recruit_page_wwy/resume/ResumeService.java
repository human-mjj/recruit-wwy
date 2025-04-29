package com.example.recruit_page_wwy.resume;


import com.example.recruit_page_wwy.employment.Employment;
import com.example.recruit_page_wwy.employment.EmploymentRepository;
import com.example.recruit_page_wwy.scrap.Scrap;
import com.example.recruit_page_wwy.scrap.ScrapRepository;
import com.example.recruit_page_wwy.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final EmploymentRepository employmentRepository;
    private final ScrapRepository scrapRepository;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(ResumeRequest.SaveDTO saveDTO) {
        resumeRepository.save(saveDTO.getUser_id(), saveDTO.getTitle(), saveDTO.getExp(), saveDTO.getEdu(), saveDTO.getJob_id(), saveDTO.getLocation(), saveDTO.getQualified(),
                saveDTO.getActivity(), saveDTO.getImg_url(), saveDTO.getSkills());
    }

    public ResumeResponse.MainDTO findAll(Integer userId, Integer page) {
        int realPage = page - 1;
        int size = 5;
        Long totalCount = resumeRepository.totalCount(userId);
        List<Resume> resumes = resumeRepository.findAll(userId, realPage);
        return new ResumeResponse.MainDTO(resumes, page, totalCount.intValue());
    }

    public ResumeResponse.DetailDTO detailView(Integer resumeId, User sessionUser) {
        Resume resume = resumeRepository.findByResumeId(resumeId);
        if (resume == null) throw new RuntimeException("404 Not Found");

        boolean isScrap = false;
        Integer scrapId = null;

        // 채용공고 리스트 (기업인 경우만)
        List<ResumeResponse.DetailDTO.EmployDTO> employmentList = new ArrayList<>();
        if (sessionUser != null && sessionUser.getRole() == 1) {
            List<Employment> employments = employmentRepository.findByUserId(sessionUser.getId());
            for (Employment employment : employments) {
                employmentList.add(new ResumeResponse.DetailDTO.EmployDTO(employment));
                System.out.println(resume.getTitle());
            }

            Scrap scrap = scrapRepository.findByUserIdAndResumeId(sessionUser.getId(), resumeId);
            isScrap = scrap == null ? false : true;
            scrapId = scrap == null ? null : scrap.getId();
        }
        return new ResumeResponse.DetailDTO(sessionUser, resume, employmentList, isScrap, scrapId);
    }

    @Transactional
    public void update(Integer id, ResumeRequest.UpdateDTO updateDTO) {

        resumeRepository.update(
                id,
                updateDTO.getTitle(),
                updateDTO.getExp(),
                updateDTO.getEdu(),
                updateDTO.getJob_id(),
                updateDTO.getLocation(),
                updateDTO.getQualified(),
                updateDTO.getActivity(),
                updateDTO.getSkills()
        );


    }

    public Resume findById(Integer id) {
        return resumeRepository.findByResumeId(id);
    }

    @Transactional
    public void delete(int resumeId) {
        Resume resume = em.find(Resume.class, resumeId);
        if (resume != null) {
            em.remove(resume);
        }
    }
}
