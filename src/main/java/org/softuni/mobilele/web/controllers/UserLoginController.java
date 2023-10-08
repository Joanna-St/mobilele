package org.softuni.mobilele.web.controllers;

import lombok.AllArgsConstructor;
import org.softuni.mobilele.models.dto.UserLoginDTO;
import org.softuni.mobilele.service.impl.UserLoginServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RequestMapping("/users")
@Controller
public class UserLoginController {
    private UserLoginServiceImpl userLoginService;

    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    @PostMapping("/login")
    public String login(UserLoginDTO userLoginDTO) {
        return userLoginService.loginUser(userLoginDTO) ? "redirect:/" : "auth-login";
    }
}
