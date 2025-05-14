package com.example.recruit_page_wwy.user;


import com.example.recruit_page_wwy._core.error.ex.ExceptionApi401;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    // JWT는 Access Token만 사용 (만료기간 1시간)

    // TODO : 저장 후 DTO에 담아서 반환 ✅
    // TODO : BCrypt 사용해서 비밀번호 해시 생성
    // TODO : JWT 생성
    @Transactional
    public UserResponse.DTO joinUser(UserRequest.UserDTO reqDTO) {
        User userPS = userRepository.saveUser(reqDTO.toEntity());
        return new UserResponse.DTO(userPS);
    }

    // TODO : 저장 후 DTO에 담아서 반환 ✅
    // TODO : BCrypt 사용해서 비밀번호 해시 생성
    // TODO : JWT 생성
    @Transactional
    public UserResponse.DTO joinCom(UserRequest.ComDTO reqDTO) {
        User userPS = userRepository.saveCom(reqDTO.toEntity());
        return new UserResponse.DTO(userPS);
    }

    // TODO : User가 아니라 DTO에 담아서 반환 ✅
    // TODO : BCrypt 사용해서 비밀번호 검증
    // TODO : JWT 검증
    public UserResponse.DTO login(UserRequest.LoginDTO reqDTO) {
        User user = userRepository.findByEmail(reqDTO.getEmail());

        if (user == null) throw new ExceptionApi401("아이디 혹은 비밀번호가 틀렸습니다.");

        if (!user.getPassword().equals(reqDTO.getPassword())) {
            throw new ExceptionApi401("아이디 혹은 비밀번호가 틀렸습니다.");
        }

        if (user.getRole() != reqDTO.getRole()) {
            throw new ExceptionApi401("구분이 틀렸습니다.");
        }
        return new UserResponse.DTO(user);
    }

    // TODO : User가 아니라 DTO에 담아서 반환 ✅
    // TODO : 이미지 null일 경우 안 올라가게 수정
    // TODO : 이미지 Encoding 추가
    // 이 아래는 userUpdate 수업 때 진행하고 나서 하기
    // TODO : 비밀번호는 BCrypt 사용해서 해시 후 집어넣기
    // TODO : JWT 재발행 (파기 후 다시 발급)
    @Transactional
    public UserResponse.DTO userUpdate(UserRequest.UpdateDTO reqDTO, User sessionUser) {
        User userPS = userRepository.findById(sessionUser.getId());

        // MultipartFile imgFile = reqDTO.getUploadingImg();
        String imgFilename = null;
//        if (imgFile != null && !imgFile.isEmpty()) {
//            imgFilename += UUID.randomUUID() + "_" + imgFile.getOriginalFilename();
//            System.out.println("img Filename: " + imgFilename);
//            Path imgPath = Paths.get("./upload/" + imgFilename);
//            try {
//                Files.write(imgPath, imgFile.getBytes());
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }

        // 더티 체킹
        userPS.update(reqDTO.toEntity(imgFilename));
        return new UserResponse.DTO(userPS);
    }

    // TODO : User가 아니라 DTO에 담아서 반환 ✅
    public UserResponse.MyPageDTO mypage(User sessionUser) {
        User user = userRepository.findById(sessionUser.getId());
        return new UserResponse.MyPageDTO(user);
    }
}
