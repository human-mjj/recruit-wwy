package com.example.recruit_page_wwy.resume;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeRepository resumeRepository;

    @Transactional
    public void save(ResumeRequest.SaveDTO saveDTO) {
        resumeRepository.save(saveDTO.getUser_id(), saveDTO.getTitle(), saveDTO.getExp(), saveDTO.getEdu(), saveDTO.getJob_id(), saveDTO.getLocation(), saveDTO.getQualified(),
                saveDTO.getActivity(), saveDTO.getImg_url());

    }
}
