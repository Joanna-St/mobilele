package org.softuni.mobilele.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AllBrandsController {
    @GetMapping("/brands/all")
    public String index() {
        return "brands";
    }
}
