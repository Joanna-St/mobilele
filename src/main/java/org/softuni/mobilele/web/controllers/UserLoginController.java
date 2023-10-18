package org.softuni.mobilele.web.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.softuni.mobilele.models.dto.UserLoginDTO;
import org.softuni.mobilele.service.impl.UserLoginServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@RequestMapping("/users")
@Controller
public class UserLoginController {
    private UserLoginServiceImpl userLoginService;

    @GetMapping("/login")
    public String login(Model model) {
        if (!model.containsAttribute("userLoginDTO")) {
            model.addAttribute("userLoginDTO",
                    new UserLoginDTO());
        }
        return "auth-login";
    }

    @PostMapping("/login")
    public String login(@Valid UserLoginDTO userLoginDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("userLoginDTO", userLoginDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);

            return "redirect:/users/login";
        }
        return userLoginService.loginUser(userLoginDTO) ? "redirect:/" : "redirect:/users/login";
    }
}
