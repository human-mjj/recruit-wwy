package com.example.recruit_page_wwy.scrap;


import com.example.recruit_page_wwy.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScrapService {
    private final ScrapRepository scrapRepository;

    public List<ScrapRequest.UserScrapDTO> ScrapUserfind(User sessinUser) {
        List<ScrapRequest.UserScrapDTO> userScrapList = scrapRepository.findAllUserScrapById(sessinUser.getId());
        return userScrapList;
    }

    public List<ScrapRequest.ComScrapDTO> ScrapComfind(User sessinUser) {
        List<ScrapRequest.ComScrapDTO> comScrapList = scrapRepository.findAllComScrapById(sessinUser.getId());
        return comScrapList;
    }
}
