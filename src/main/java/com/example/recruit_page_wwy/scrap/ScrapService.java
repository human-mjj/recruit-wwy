package com.example.recruit_page_wwy.scrap;


import com.example.recruit_page_wwy.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScrapService {
    private final ScrapRepository scrapRepository;

    public List<ScrapRequest.UserScrapDTO> find(User sessinUser) {
        List<ScrapRequest.UserScrapDTO> scrapList = scrapRepository.findAllUserScrapById(sessinUser.getId());
        return scrapList;
    }
}
