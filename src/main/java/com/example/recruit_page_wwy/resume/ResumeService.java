package com.example.recruit_page_wwy.resume;


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
                saveDTO.getActivity(), saveDTO.getImg_url());

    }


    public ResumeResponse.MainDTO findAll(Integer userId) {
        List<Resume> resumes = resumeRepository.findAll(userId);
        return new ResumeResponse.MainDTO(resumes);
    }

}
