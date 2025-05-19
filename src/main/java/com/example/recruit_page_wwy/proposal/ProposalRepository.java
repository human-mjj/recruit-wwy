package com.example.recruit_page_wwy.proposal;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProposalRepository {
    private final EntityManager em;

    public Proposal save(Proposal proposal) {
        em.persist(proposal);
        return proposal;
    }
}
