package com.example.recruit_page_wwy.employment;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmploymentService {
    private final EmploymentRepository employmentRepository;

    public List<Employment> employmentList(Integer userId) {
        return employmentRepository.findByUserId(userId);
    }
}
