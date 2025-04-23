package com.example.recruit_page_wwy.user;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        if (user == null) throw new RuntimeException("아이디 혹은 비밀번호가 틀렸습니다.");

        if (!user.getPassword().equals(reqDTO.getPassword())) {
            throw new RuntimeException("아이디 혹은 비밀번호가 틀렸습니다.");
        }

        if (user.getRole() != reqDTO.getRole()) {
            throw new RuntimeException("구분이 틀렸습니다.");
        }

        return user;
    }
}
