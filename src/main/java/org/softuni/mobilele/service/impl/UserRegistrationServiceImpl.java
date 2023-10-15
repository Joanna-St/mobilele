package org.softuni.mobilele.service.impl;

import lombok.AllArgsConstructor;
import org.softuni.mobilele.models.dto.UserRegistrationDTO;
import org.softuni.mobilele.models.entity.User;
import org.softuni.mobilele.models.entity.UserRole;
import org.softuni.mobilele.models.entity.enums.UserRoleEnum;
import org.softuni.mobilele.repository.UserRepository;
import org.softuni.mobilele.service.UserRegistrationService;
import org.softuni.mobilele.util.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserRegistrationServiceImpl implements UserRegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        userRepository.save(map(userRegistrationDTO));
        currentUser.login(map(userRegistrationDTO));
    }

    private User map(UserRegistrationDTO userRegistrationDTO) {
        User newUser = new User();
        newUser.setUsername(userRegistrationDTO.username());
        newUser.setPassword(passwordEncoder.encode(userRegistrationDTO.password()));
        newUser.setFirstName(userRegistrationDTO.firstName());
        newUser.setLastName(userRegistrationDTO.lastName());
        newUser.setRole(new UserRole(UserRoleEnum.valueOf(userRegistrationDTO.role())));
        newUser.setCreated(LocalDateTime.now()
        );
        newUser.setActive(true);

        return newUser;
    }


}
