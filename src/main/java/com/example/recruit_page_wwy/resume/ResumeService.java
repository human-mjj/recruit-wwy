package com.example.recruit_page_wwy.resume;


import com.example.recruit_page_wwy._core.error.ex.ExceptionApi404;
import com.example.recruit_page_wwy.employment.Employment;
import com.example.recruit_page_wwy.employment.EmploymentRepository;
import com.example.recruit_page_wwy.job.Job;
import com.example.recruit_page_wwy.resumestack.ResumeStack;
import com.example.recruit_page_wwy.scrap.Scrap;
import com.example.recruit_page_wwy.scrap.ScrapRepository;
import com.example.recruit_page_wwy.stack.Stack;
import com.example.recruit_page_wwy.user.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ResumeService {
    private final ResumeRepository resumeRepository;
    private final EmploymentRepository employmentRepository;
    private final ScrapRepository scrapRepository;

    @PersistenceContext
    private EntityManager em;

    // TODO : 이미지 Encoding 추가
    // TODO : 저장 후 DTO에 담아서 반환
    @Transactional
    public void save(ResumeRequest.SaveDTO saveDTO, User sessionUser) {
        MultipartFile imgFile = saveDTO.getUploadingImg();
        String imgFilename = null;
        if (!"null".contains(imgFile.getOriginalFilename())) {
            imgFilename += UUID.randomUUID() + "_" + imgFile.getOriginalFilename();
            System.out.println("img Filename: " + imgFilename);
            Path imgPath = Paths.get("./upload/" + imgFilename);
            try {
                Files.write(imgPath, imgFile.getBytes());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        Resume resumePS = resumeRepository.save(saveDTO.toEntity(sessionUser, imgFilename));
        resumeRepository.updateStack(resumePS.getId(), saveDTO.getSkills());
    }

    public ResumeResponse.MainDTO findAll(Integer userId, Integer page) {
        int realPage = page - 1;
        int size = 5;
        Long totalCount = resumeRepository.totalCount(userId);
        List<Resume> resumes = resumeRepository.findAll(userId, realPage);
        return new ResumeResponse.MainDTO(resumes, page, totalCount.intValue());
    }

    public ResumeResponse.DetailDTO detailView(Integer resumeId, User sessionUser) {
        Resume resume = resumeRepository.findByResumeId(resumeId);
        if (resume == null) throw new RuntimeException("404 Not Found");

        boolean isScrap = false;
        Integer scrapId = null;

        // 채용공고 리스트 (기업인 경우만)
        List<ResumeResponse.DetailDTO.EmployDTO> employmentList = new ArrayList<>();
        if (sessionUser != null && sessionUser.getRole() == 1) {
            List<Employment> employments = employmentRepository.findByUserId(sessionUser.getId());
            for (Employment employment : employments) {
                employmentList.add(new ResumeResponse.DetailDTO.EmployDTO(employment));
                System.out.println(resume.getTitle());
            }

            Scrap scrap = scrapRepository.findByUserIdAndResumeId(sessionUser.getId(), resumeId);
            isScrap = scrap != null;
            scrapId = scrap == null ? null : scrap.getId();
        }
        return new ResumeResponse.DetailDTO(sessionUser, resume, employmentList, isScrap, scrapId);
    }

    // TODO : 이미지 Encoding 추가
    // TODO : 업데이트 후 DTO에 담아서 반환
    @Transactional
    public void update(Integer id, ResumeRequest.SaveDTO updateDTO) {
        Resume resume = resumeRepository.findByResumeId(id);
        if (resume == null) throw new ExceptionApi404("해당하는 이력서가 없습니다.");
        MultipartFile imgFile = updateDTO.getUploadingImg();
        String imgFilename = null;
        if (!"null".contains(imgFile.getOriginalFilename())) {
            imgFilename += UUID.randomUUID() + "_" + imgFile.getOriginalFilename();
            System.out.println("img Filename: " + imgFilename);
            Path imgPath = Paths.get("./upload/" + imgFilename);
            try {
                Files.write(imgPath, imgFile.getBytes());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        resume.update(updateDTO, imgFilename);
        resumeRepository.updateStack(resume.getId(), updateDTO.getSkills());
    }

    public ResumeResponse.UpdateViewDTO findById(Integer resumeId) {
        Resume resume = resumeRepository.findByResumeId(resumeId);
        if (resume == null) throw new ExceptionApi404("404 Not Found");

        List<Job> jobList = resumeRepository.findAllJobs();

        List<Stack> stackList = resumeRepository.findAllStacks();

        // 4. 현재 employment에 등록된 스택(skill) 목록 뽑기
        List<String> selectedStacks = new ArrayList<>();
        for (ResumeStack resumeStack : resume.getResumeStackList()) {
            selectedStacks.add(resumeStack.getSkill());
        }

        // 5. jobList -> jobDTOList로 변환
        List<ResumeResponse.UpdateViewDTO.JobDTO> jobDTOList = new ArrayList<>();
        for (Job job : jobList) {
            ResumeResponse.UpdateViewDTO.JobDTO dto = new ResumeResponse.UpdateViewDTO.JobDTO();
            dto.setId(job.getId());
            dto.setName(job.getName());
            jobDTOList.add(dto);
        }

        // 6. stackList -> stackDTOList로 변환
        List<ResumeResponse.UpdateViewDTO.StackDTO> stackDTOList = new ArrayList<>();
        for (Stack stack : stackList) {
            ResumeResponse.UpdateViewDTO.StackDTO dto = new ResumeResponse.UpdateViewDTO.StackDTO();
            dto.setSkill(stack.getSkill());
            stackDTOList.add(dto);
        }

        // 7. selectedStacks 저장
        List<ResumeStack> selectedStackList = resumeRepository.findAllStacksByResumeId(resumeId);
        ResumeResponse.UpdateViewDTO updateViewDTO = new ResumeResponse.UpdateViewDTO(resume, jobDTOList, stackDTOList, selectedStackList);
        return updateViewDTO;
    }

    @Transactional
    public void delete(int resumeId) {
        Resume resume = em.find(Resume.class, resumeId);
        if (resume != null) {
            em.remove(resume);
        }
    }

    public ResumeResponse.TableDTO viewJobAndStackList() {
        List<Job> jobList = resumeRepository.findAllJobs();
        List<Stack> stackList = resumeRepository.findAllStacks();
        return new ResumeResponse.TableDTO(jobList, stackList);
    }
}
