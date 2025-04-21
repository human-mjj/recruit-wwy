package com.example.recruit_page_wwy.resume;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeRepository resumeRepository;
}
