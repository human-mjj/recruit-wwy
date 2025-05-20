package com.example.recruit_page_wwy.user;


import com.example.recruit_page_wwy._core.error.ex.ExceptionApi401;
import com.example.recruit_page_wwy._core.util.Base64Util;
import com.example.recruit_page_wwy._core.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    // JWT는 Access Token만 사용 (만료기간 1시간)

    @Transactional
    public UserResponse.DTO joinUser(UserRequest.UserDTO reqDTO) {
        String encPassword = BCrypt.hashpw(reqDTO.getPassword(), BCrypt.gensalt());
        reqDTO.setPassword(encPassword);

        User userPS = userRepository.saveUser(reqDTO.toEntity());
        return new UserResponse.DTO(userPS, null);
    }

    @Transactional
    public UserResponse.DTO joinCom(UserRequest.ComDTO reqDTO) {
        String encPassword = BCrypt.hashpw(reqDTO.getPassword(), BCrypt.gensalt());
        reqDTO.setPassword(encPassword);

        User userPS = userRepository.saveCom(reqDTO.toEntity());
        return new UserResponse.DTO(userPS, null);
    }

    public UserResponse.TokenDTO login(UserRequest.LoginDTO reqDTO) {
        User user = userRepository.findByEmail(reqDTO.getEmail());

        if (user == null) throw new ExceptionApi401("아이디 혹은 비밀번호가 틀렸습니다.");

        Boolean isSame = BCrypt.checkpw(reqDTO.getPassword(), user.getPassword());

        if (!isSame) throw new ExceptionApi401("유저네임 혹은 비밀번호가 틀렸습니다");

        if (user.getRole() != reqDTO.getRole()) {
            throw new ExceptionApi401("구분이 틀렸습니다.");
        }

        String accessToken = JwtUtil.create(user);


        return UserResponse.TokenDTO.builder().accessToken(accessToken).build();
    }

    // TODO : JWT 재발행 (파기 후 다시 발급)
    @Transactional
    public UserResponse.DTO userUpdate(UserRequest.UpdateDTO reqDTO, User sessionUser) {
        User userPS = userRepository.findById(sessionUser.getId());
        String imgFilename = null;

        // 새 이미지가 Base64 문자열로 넘어온 경우에만 저장
        String imgUrl = reqDTO.getImgUrl();
        if (imgUrl != null && imgUrl.startsWith("data:image/")) {
            imgFilename = UUID.randomUUID() + "_" + userPS.getUsername();
            Path imgPath = Paths.get("./upload/" + imgFilename);

            try {
                // 폴더가 없으면 생성, 있으면 넘어감
                Files.createDirectories(imgPath.getParent());
                byte[] decodedImageBytes = Base64Util.decodeAsBytes(imgUrl);
                Files.write(imgPath, decodedImageBytes);
            } catch (Exception e) {
                throw new RuntimeException("이미지 저장 실패", e);
            }
        }

        String encPassword = BCrypt.hashpw(reqDTO.getPassword(), BCrypt.gensalt());
        reqDTO.setPassword(encPassword);

        // 더티 체킹
        userPS.update(reqDTO.toEntity(imgFilename));
        String accessToken = JwtUtil.create(userPS);
        return new UserResponse.DTO(userPS, accessToken);
    }

    // TODO : User가 아니라 DTO에 담아서 반환 ✅
    public UserResponse.MyPageDTO mypage(Integer id) {
        User user = userRepository.findById(id);
        return new UserResponse.MyPageDTO(user);
    }
}
