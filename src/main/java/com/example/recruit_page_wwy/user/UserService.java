package com.example.recruit_page_wwy.user;


import com.example.recruit_page_wwy._core.error.ex.Exception401;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void joinUser(UserRequest.UserDTO reqDTO) {
        userRepository.saveUser(reqDTO.toEntity());

    }

    @Transactional
    public void joinCom(UserRequest.ComDTO reqDTO) {
        userRepository.saveCom(reqDTO.toEntity());
    }

    public User login(UserRequest.LoginDTO reqDTO) {
        User user = userRepository.findByEmailAndPassword(reqDTO.getEmail(), reqDTO.getPassword());

        if (user == null) throw new Exception401("아이디 혹은 비밀번호가 틀렸습니다.");

        if (!user.getPassword().equals(reqDTO.getPassword())) {
            throw new Exception401("아이디 혹은 비밀번호가 틀렸습니다.");
        }

        if (user.getRole() != reqDTO.getRole()) {
            throw new Exception401("구분이 틀렸습니다.");
        }
        return user;
    }

    @Transactional
    public User userUpdate(UserRequest.UpdateDTO reqDTO, User sessionUser) {
        User userPS = userRepository.findById(sessionUser.getId());
        MultipartFile imgFile = reqDTO.getUploadingImg();
        String imgFilename = UUID.randomUUID() + "_" + imgFile.getOriginalFilename();
        System.out.println("img Filename: " + imgFilename);
        Path imgPath = Paths.get("./upload/" + imgFilename);
        try {
            Files.write(imgPath, imgFile.getBytes());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 더티 체킹
        userPS.update(reqDTO.toEntity(imgFilename));
        return userPS;
    }

    public User mypage(Integer id) {
        return userRepository.findById(id);
    }
}
