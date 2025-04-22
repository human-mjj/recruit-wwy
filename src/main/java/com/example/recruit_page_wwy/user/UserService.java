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
}
