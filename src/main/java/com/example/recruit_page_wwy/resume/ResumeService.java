package com.example.recruit_page_wwy.resume;


import com.example.recruit_page_wwy.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeRepository resumeRepository;

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

    public ResumeResponse.DetailDTO Detail(Integer id) {
        Resume resume = resumeRepository.findByResumeId(id);
        User user = resume.getUser();

        return new ResumeResponse.DetailDTO(
                resume.getId(),
                resume.getUser().getId(),             // 유저 ID
                resume.getJob(),
                resume.getResumeStackList(),
                resume.getUser().getUsername(),       // 유저 이름
                resume.getUser().getEmail(),          // 이메일
                resume.getUser().getPhone(),          // 전화번호
                resume.getTitle(),
                resume.getExp(),
                resume.getEdu(),
                resume.getLocation(),
                resume.getQualified(),
                resume.getActivity(),
                resume.getImgUrl(),
                resume.getLetter()
        );
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
