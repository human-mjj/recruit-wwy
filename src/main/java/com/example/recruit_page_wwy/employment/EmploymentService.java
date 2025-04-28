package com.example.recruit_page_wwy.employment;


import com.example.recruit_page_wwy.employstack.EmployStackRepository;
import com.example.recruit_page_wwy.job.Job;
import com.example.recruit_page_wwy.resume.Resume;
import com.example.recruit_page_wwy.resume.ResumeRepository;
import com.example.recruit_page_wwy.scrap.Scrap;
import com.example.recruit_page_wwy.scrap.ScrapRepository;
import com.example.recruit_page_wwy.user.User;
import com.example.recruit_page_wwy.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class EmploymentService {
    private final EmploymentRepository employmentRepository;
    private final UserRepository userRepository;
    private final ResumeRepository resumeRepository;
    private final EmployStackRepository employStackRepository;
    private final ScrapRepository scrapRepository;

    public List<EmploymentResponse.ListDTO> employmentList(Integer userId) {
        List<Employment> employmentList = employmentRepository.findAllByUserId(userId);

        List<EmploymentResponse.ListDTO> dtoList = new ArrayList<>();
        for (Employment e : employmentList) {
            EmploymentResponse.ListDTO dto = new EmploymentResponse.ListDTO(e);
            dtoList.add(dto);
        }

        return dtoList;
    }

    // 채용공고 리스트, paging
    public EmploymentResponse.EmploymentPageDTO employmentAllList(User sessionUser, Integer page) {
        Long totalCount = employmentRepository.totalCount();
        List<Employment> employmentList = employmentRepository.findAll(page);

        List<EmploymentResponse.PublicListDTO> dtoList = new ArrayList<>();
        for (Employment e : employmentList) {
            dtoList.add(new EmploymentResponse.PublicListDTO(e));
        }

        return new EmploymentResponse.EmploymentPageDTO(dtoList, page, totalCount.intValue());
    }

    public List<Employment> viewEmployList() {
        return employmentRepository.findTop4ByOrderByIdDesc();
    }

    public EmploymentResponse.DetailDTO findEmploymentDetail(Integer employmentId, Integer sessionUserId) {

        // 채용 공고 데이터 조회
        Object[] result = (Object[]) employmentRepository.findDetailRawById(employmentId);

        Integer id = (Integer) result[0];
        String userImgUrl = (String) result[1];
        String title = (String) result[2];
        String comName = (String) result[3];
        String exp = (String) result[4];
        String edu = (String) result[5];
        String shift = (String) result[6];
        Integer sal = (Integer) result[7];
        String workingTime = (String) result[8];
        String location = (String) result[9];
        Date endDate = (Date) result[10];
        String jobName = (String) result[11];
        String dutyStr = (String) result[12];
        String qualificationStr = (String) result[13];

        List<String> duty = new ArrayList<>();
        if (dutyStr != null && !dutyStr.isBlank()) {
            String[] dutyArr = dutyStr.split("\\$");
            for (String d : dutyArr) {
                duty.add(d.trim());
            }
        }

        List<String> qualification = new ArrayList<>();
        if (qualificationStr != null && !qualificationStr.isBlank()) {
            String[] qualArr = qualificationStr.split("\\$");
            for (String q : qualArr) {
                qualification.add(q.trim());
            }
        }

        // 이력서 리스트 (구직자 로그인 시에만 보]이게)
        List<EmploymentResponse.DetailDTO.ResumeDTO> resumeList = new ArrayList<>();
        if (sessionUserId != null) {
            List<Resume> resumes = resumeRepository.findByUserId(sessionUserId);
            for (Resume resume : resumes) {
                resumeList.add(new EmploymentResponse.DetailDTO.ResumeDTO(resume));
            }
        }

        // 스택 리스트
        List<String> stackList = employmentRepository.findStackByEmploymentId(employmentId);
        String stackStr = String.join(", ", stackList);

        // 로그인 유저 체크
        User sessionUser = null;
        Integer sessionUserRole = null;
        boolean isOwner = false;
        boolean isApplicant = false;

        if (sessionUserId != null) {
            sessionUser = userRepository.findById(sessionUserId);

            if (sessionUser != null) {
                sessionUserRole = sessionUser.getRole(); // 0 = 구직자, 1 = 기업
                isApplicant = (sessionUserRole == 0);

                isOwner = employmentRepository.isOwner(employmentId, sessionUserId);
            }
        }

        Scrap scrap = scrapRepository.findByUserIdAndEmployId(sessionUserId, employmentId);
        Boolean isScrap = scrap == null ? false : true;
        Integer scrapId = scrap == null ? null : scrap.getId();

        return new EmploymentResponse.DetailDTO(
                sessionUserId,
                sessionUserRole,
                isOwner,
                isApplicant,
                id,
                userImgUrl,
                title,
                comName,
                exp,
                edu,
                shift,
                sal,
                workingTime,
                location,
                endDate,
                duty,
                qualification,
                jobName,
                stackList,
                stackStr,
                resumeList,
                isScrap,
                scrapId
        );
    }

    @Transactional
    public void save(EmploymentRequest.SaveDTO saveDTO, User sessionUser) {
        Job selectedJob = employmentRepository.findJobByName(saveDTO.getJobName());
        Employment savingEmployment = saveDTO.toEntity(sessionUser, selectedJob);
        employmentRepository.save(savingEmployment);
    }

//    @Transactional
//    public void saveEmployment(EmploymentRequest.SaveDTO dto, User user, Job job) {
//        Employment employment = dto.toEntity(user, job);
//        employmentRepository.save(employment);
//
//        for (String skill : dto.getStack()) {
//            EmployStack es = new EmployStack(employment, skill);
//            employStackRepository.save(es);
//        }
//    }
}
