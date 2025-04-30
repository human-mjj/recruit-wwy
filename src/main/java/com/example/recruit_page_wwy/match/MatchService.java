package com.example.recruit_page_wwy.match;


import com.example.recruit_page_wwy.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MatchService {
    private final MatchRepository matchRepository;

    public MatchResponse.MatchDTO matchEmployment(User sessionUser) {
        List<MatchResponse.EmploymentDTO> recommendedEmployList = matchRepository.findAllProposals(sessionUser.getId());
        List<MatchResponse.EmploymentDTO> matchEmployList = matchRepository.findAllRecommendedEmployments(sessionUser.getId());
        return new MatchResponse.MatchDTO(recommendedEmployList, matchEmployList, sessionUser);
    }

    public List<MatchResponse.ResumeDTO> matchResume(User sessionUser) {
        List<MatchResponse.ResumeDTO> matchResumeList = matchRepository.findAllRecommendedResumes(sessionUser.getId());
        return matchResumeList;
    }
}
