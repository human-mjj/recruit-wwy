package com.example.recruit_page_wwy.employment;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmploymentService {
    private final EmploymentRepository employmentRepository;

    public List<EmploymentResponse.ListDTO> employmentList(Integer userId) {
        List<Employment> employmentList = employmentRepository.findAllByUserId(userId);

        List<EmploymentResponse.ListDTO> dtoList = new ArrayList<>();
        for (Employment e : employmentList) {
            EmploymentResponse.ListDTO dto = new EmploymentResponse.ListDTO(e);
            dtoList.add(dto);
        }

        return dtoList;
    }

    public List<EmploymentResponse.PublicListDTO> emplymentAllList(Integer userId) {
        List<Employment> employmentAllList = employmentRepository.findAll();

        List<EmploymentResponse.PublicListDTO> dtoList = new ArrayList<>();
        for (Employment e : employmentAllList) {
            EmploymentResponse.PublicListDTO dto = new EmploymentResponse.PublicListDTO(e);
            dtoList.add(dto);
        }

        return dtoList;
    }
}
