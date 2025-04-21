package com.example.recruit_page_wwy.scrap;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ScrapRepository {
    private final EntityManager em;

}
