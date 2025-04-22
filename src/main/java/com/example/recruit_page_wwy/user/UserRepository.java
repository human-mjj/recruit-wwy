package com.example.recruit_page_wwy.user;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepository {
    private final EntityManager em;

    public void saveUser(User user) {
        em.persist(user);
    }

    public void saveCom(User user) {
        em.persist(user);
    }


}
