package org.softuni.mobilele.service.impl;

import lombok.AllArgsConstructor;
import org.softuni.mobilele.service.UserLogoutService;
import org.softuni.mobilele.util.CurrentUser;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserLogoutServiceImpl implements UserLogoutService {
    private final CurrentUser currentUser;
    @Override
    public void logoutUser() {
        currentUser.logout();
    }
}
