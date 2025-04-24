package com.example.recruit_page_wwy.resume;


import com.example.recruit_page_wwy.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeRepository resumeRepository;

    @Transactional
    public void save(ResumeRequest.SaveDTO saveDTO) {
        resumeRepository.save(saveDTO.getUser_id(), saveDTO.getTitle(), saveDTO.getExp(), saveDTO.getEdu(), saveDTO.getJob_id(), saveDTO.getLocation(), saveDTO.getQualified(),
                saveDTO.getActivity(), saveDTO.getImg_url(), saveDTO.getSkills());

    }


    public List<Resume> findAll(Integer userId) {
        List<Resume> resumes = resumeRepository.findAll(userId);
        return resumes;
    }

    public ResumeResponse.DetailDTO Detail(Integer id) {
        Resume resume = resumeRepository.findByResumeId(id);
        User user = resume.getUser();

        return new ResumeResponse.DetailDTO(
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
                updateDTO.getActivity()
        );


    }

    public Resume findById(Integer id) {
        return resumeRepository.findByResumeId(id);
    }
}
