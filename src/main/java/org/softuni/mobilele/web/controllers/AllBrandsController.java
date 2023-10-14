package org.softuni.mobilele.web.controllers;

import lombok.AllArgsConstructor;
import org.softuni.mobilele.models.dto.BrandDTO;
import org.softuni.mobilele.service.impl.AllBrandsServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@AllArgsConstructor
@Controller
public class AllBrandsController {
    private AllBrandsServiceImpl allBrandsService;

    @GetMapping("/brands/all")
    public String listBrands(Model model) {
        List<BrandDTO> brands = allBrandsService.getAllBrands();
        model.addAttribute("brands", brands);

        return "brands";
    }
}
