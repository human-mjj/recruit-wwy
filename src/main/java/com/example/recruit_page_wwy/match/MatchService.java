package com.example.recruit_page_wwy.match;


import com.example.recruit_page_wwy.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MatchService {
    private final MatchRepository matchRepository;

    // TODO : 예외 처리
    public MatchResponse.MatchDTO matchEmployment(User sessionUser) {
        List<MatchResponse.EmploymentDTO> recommendedEmployList = matchRepository.findAllProposals(sessionUser.getId());
        List<MatchResponse.EmploymentDTO> matchEmployList = matchRepository.findAllRecommendedEmployments(sessionUser.getId());
        return new MatchResponse.MatchDTO(recommendedEmployList, matchEmployList, sessionUser);
    }

    // TODO : 예외 처리
    public MatchResponse.ResumeListDTO matchResume(User sessionUser) {
        MatchResponse.ResumeListDTO matchResumeList = matchRepository.findAllRecommendedResumes(sessionUser.getId(), sessionUser);
        return matchResumeList;
    }
}
