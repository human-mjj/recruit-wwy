package com.example.recruit_page_wwy.employment;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class EmploymentRepository {
    private final EntityManager em;

}
