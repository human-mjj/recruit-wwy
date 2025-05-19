package com.example.recruit_page_wwy.match;


import com.example.recruit_page_wwy._core.error.ex.ExceptionApi403;
import com.example.recruit_page_wwy._core.error.ex.ExceptionApi404;
import com.example.recruit_page_wwy.user.User;
import com.example.recruit_page_wwy.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final UserRepository userRepository;

    // TODO : 예외 처리
    public MatchResponse.MatchDTO matchEmployment(User sessionUser) {
        User userPS = userRepository.findUserById(sessionUser.getId());
        if (userPS == null) throw new ExceptionApi404("해당하는 유저가 없습니다.");
        if (userPS.getRole() != 0) throw new ExceptionApi403("권한이 존재하지 않습니다.");
        List<MatchResponse.EmploymentDTO> recommendedEmployList = matchRepository.findAllProposals(sessionUser.getId());
        List<MatchResponse.EmploymentDTO> matchEmployList = matchRepository.findAllRecommendedEmployments(sessionUser.getId());
        return new MatchResponse.MatchDTO(recommendedEmployList, matchEmployList, sessionUser);
    }

    // TODO : 예외 처리
    public MatchResponse.ResumeListDTO matchResume(User sessionUser) {
        User userPS = userRepository.findUserById(sessionUser.getId());
        if (userPS == null) throw new ExceptionApi404("해당하는 유저가 없습니다.");
        if (userPS.getRole() != 1) throw new ExceptionApi403("권한이 존재하지 않습니다.");
        MatchResponse.ResumeListDTO matchResumeList = matchRepository.findAllRecommendedResumes(sessionUser.getId(), sessionUser);
        return matchResumeList;
    }
}
