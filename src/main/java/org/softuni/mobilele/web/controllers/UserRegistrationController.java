package org.softuni.mobilele.web.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.softuni.mobilele.models.dto.UserRegistrationDTO;
import org.softuni.mobilele.models.enums.UserRoleEnum;
import org.softuni.mobilele.service.impl.UserRegistrationServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@RequestMapping("/users")
@Controller
public class UserRegistrationController {
    private final UserRegistrationServiceImpl userRegistrationService;

    @ModelAttribute("roles")
    public UserRoleEnum[] roles(){
        return UserRoleEnum.values();
    }



    @GetMapping("/register")
    public String register(Model model) {
        if (!model.containsAttribute("userRegistrationDTO")) {
            model.addAttribute("userRegistrationDTO",
                    new UserRegistrationDTO());
        }

        return "auth-register";
    }


    @PostMapping("/register")
    public String register(@Valid UserRegistrationDTO userRegistrationDTO, BindingResult bindingResult, RedirectAttributes rAtt) {
        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);

            return "redirect:/users/register";
        }

        userRegistrationService.registerUser(userRegistrationDTO);
        return "redirect:/";
    }
}
