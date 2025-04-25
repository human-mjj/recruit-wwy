package com.example.recruit_page_wwy.scrap;


import com.example.recruit_page_wwy.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScrapService {
    private final ScrapRepository scrapRepository;

    public List<ScrapRequest.ScrapDTO> find(User sessinUser) {
        List<ScrapRequest.ScrapDTO> scrapList = scrapRepository.findAll(sessinUser.getId());
        return scrapList;
    }
}
