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


    public List<ScrapRequest.UserScrapDTO> scrapUserfind(User sessinUser) {
        List<ScrapRequest.UserScrapDTO> userScrapList = scrapRepository.findAllUserScrapById(sessinUser.getId());
        return userScrapList;
    }

    public List<ScrapRequest.ComScrapDTO> scrapComfind(User sessinUser) {
        List<ScrapRequest.ComScrapDTO> comScrapList = scrapRepository.findAllComScrapById(sessinUser.getId());
        return comScrapList;
    }

    // UserScrapSave
    @Transactional
    public ScrapResponse.UserSaveDTO userScrapSave(ScrapRequest.userScrapSaveDTO reqDTO, Integer sessionUserId) {
        Scrap scrapPS = scrapRepository.save(reqDTO.toEntity(sessionUserId));

        return new ScrapResponse.UserSaveDTO(scrapPS.getId());
    }

    // UserScrapDelete
    @Transactional
    public ScrapResponse.UserDeleteDTO deleteUserScrap(Integer employmentId) {
        Scrap scrapPS = scrapRepository.findById(employmentId);
        if (scrapPS == null) throw new RuntimeException("좋아요를 하지 않았습니다.");

        scrapRepository.deleteByUserId(employmentId);

        return new ScrapResponse.UserDeleteDTO(employmentId);
    }

    // ComScrapSave
    @Transactional
    public ScrapResponse.ComSaveDTO comScrapSave(ScrapRequest.comScrapSaveDTO reqDTO, Integer sessionUserId) {
        Scrap scrapPS = scrapRepository.save(reqDTO.toEntity(sessionUserId));

        return new ScrapResponse.ComSaveDTO(scrapPS.getId());
    }

    // ComScrapDelete
    @Transactional
    public ScrapResponse.ComDeleteDTO deleteComScrap(Integer resumeId) {
        Scrap scrapPS = scrapRepository.findById(resumeId);
        if (scrapPS == null) throw new RuntimeException("좋아요를 하지 않았습니다.");

        scrapRepository.deleteByComId(resumeId);

        return new ScrapResponse.ComDeleteDTO(resumeId);
    }
}
