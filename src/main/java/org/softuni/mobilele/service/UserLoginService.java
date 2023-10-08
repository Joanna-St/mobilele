package org.softuni.mobilele.service;

import org.softuni.mobilele.models.dto.UserLoginDTO;

public interface UserLoginService {
    boolean loginUser(UserLoginDTO userLoginDTO);
}
