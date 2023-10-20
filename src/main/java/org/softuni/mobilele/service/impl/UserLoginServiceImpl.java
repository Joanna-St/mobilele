package org.softuni.mobilele.service.impl;

import lombok.AllArgsConstructor;
import org.softuni.mobilele.models.dto.UserLoginDTO;
import org.softuni.mobilele.models.entity.User;
import org.softuni.mobilele.repository.UserRepository;
import org.softuni.mobilele.service.UserLoginService;
import org.softuni.mobilele.util.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class UserLoginServiceImpl implements UserLoginService {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private CurrentUser currentUser;

    @Override
    public boolean loginUser(UserLoginDTO userLoginDTO) {
        Optional<User> findUser = userRepository.findUserByUsername(userLoginDTO.getUsername());

        if (findUser.isPresent()) {
            String encodedPassword = findUser.get().getPassword();
            String rawPassword = userLoginDTO.getPassword();

            if (passwordEncoder.matches(rawPassword, encodedPassword)) {
                currentUser.login(findUser.get());
                return true;
            }
        }

        return false;
    }
}
