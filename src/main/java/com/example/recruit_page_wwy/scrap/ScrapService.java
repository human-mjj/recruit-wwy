package com.example.recruit_page_wwy.scrap;


import com.example.recruit_page_wwy._core.error.ex.ExceptionApi403;
import com.example.recruit_page_wwy._core.error.ex.ExceptionApi404;
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
        return new ScrapRequest.ComScrapPageDTO(scraps, page, totalCount.intValue(), sessionUser);
    }

    // TODO : 저장 후 DTO에 담아서 반환
    @Transactional
    public ScrapResponse.SaveDTO save(ScrapRequest.SaveDTO reqDTO, User sessionUser) {
        int scrapId = scrapRepository.save(reqDTO.toEntity(sessionUser));

        return new ScrapResponse.SaveDTO(scrapId);
    }

    @Transactional
    public void cancelScrap(Integer scrapId, User sessionUser) {
        Scrap scrapPS = scrapRepository.findById(scrapId);
        if (scrapPS == null) throw new ExceptionApi404("좋아요를 하지 않았습니다.");
        if (scrapPS.getUser().getId() != sessionUser.getId()) throw new ExceptionApi403("403 Forbidden");
        scrapRepository.deleteById(scrapId);
    }
}
