package com.example.recruit_page_wwy.scrap;


import com.example.recruit_page_wwy.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScrapService {
    private final ScrapRepository scrapRepository;

    public ScrapRequest.UserScrapPageDTO scrapUserfind(User sessionUser, int page) {
        int realPage = page - 1;
        int size = 8;
        Long totalCount = scrapRepository.userScrapTotalCount(sessionUser.getId());
        List<ScrapRequest.UserScrapDTO> scraps = scrapRepository.findAllUserScrapById(sessionUser.getId(), realPage, size);
        return new ScrapRequest.UserScrapPageDTO(scraps, page, totalCount.intValue());
    }

    public ScrapRequest.ComScrapPageDTO scrapComfind(User sessionUser, int page) {
        int realPage = page - 1;
        int size = 5;
        Long totalCount = scrapRepository.comScrapTotalCount(sessionUser.getId());
        List<ScrapRequest.ComScrapDTO> scraps = scrapRepository.findAllComScrapById(sessionUser.getId(), realPage, size);
        return new ScrapRequest.ComScrapPageDTO(scraps, page, totalCount.intValue());
    }

//    public List<ScrapRequest.UserScrapDTO> scrapUserfind(User sessinUser) {
//        List<ScrapRequest.UserScrapDTO> userScrapList = scrapRepository.findAllUserScrapById(sessinUser.getId());
//        return userScrapList;
//    }
//
//    public List<ScrapRequest.ComScrapDTO> scrapComfind(User sessinUser) {
//        List<ScrapRequest.ComScrapDTO> comScrapList = scrapRepository.findAllComScrapById(sessinUser.getId());
//        return comScrapList;
//    }

    @Transactional
    public ScrapResponse.SaveDTO save(ScrapRequest.SaveDTO reqDTO, Integer sessionUserId) {
        Scrap scrapPS = scrapRepository.save(reqDTO.toEntity(sessionUserId));

        return new ScrapResponse.SaveDTO(scrapPS.getId());
    }

    @Transactional
    public ScrapResponse.DeleteDTO cancelScrap(Integer employmentId) {
        Scrap scrapPS = scrapRepository.findById(employmentId);
        if (scrapPS == null) throw new RuntimeException("좋아요를 하지 않았습니다.");

        scrapRepository.deleteById(employmentId);

        return new ScrapResponse.DeleteDTO(employmentId);
    }
}
