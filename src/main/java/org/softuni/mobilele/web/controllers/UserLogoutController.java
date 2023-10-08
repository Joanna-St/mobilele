package org.softuni.mobilele.web.controllers;

import lombok.AllArgsConstructor;
import org.softuni.mobilele.service.impl.UserLogoutServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@AllArgsConstructor
@Controller
public class UserLogoutController {
    private UserLogoutServiceImpl userLogoutService;
    @GetMapping("/users/logout")
    public String logout() {
        userLogoutService.logoutUser();
        return "index";
    }
}
