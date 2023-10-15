package org.softuni.mobilele.service.impl;

import lombok.AllArgsConstructor;
import org.softuni.mobilele.models.dto.BrandDTO;
import org.softuni.mobilele.models.dto.ModelDTO;
import org.softuni.mobilele.models.entity.Brand;
import org.softuni.mobilele.models.entity.Model;
import org.softuni.mobilele.repository.BrandRepository;
import org.softuni.mobilele.service.AllBrandsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AllBrandsServiceImpl implements AllBrandsService {
    private final BrandRepository brandRepository;

    @Override
    public List<BrandDTO> getAllBrands() {
        return brandRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    private BrandDTO map(Brand brand) {
        return new BrandDTO(brand.getName(),
                brand.getModels()
                        .stream()
                        .map(this::map)
                        .toList());
    }

    private ModelDTO map(Model model) {
        return new ModelDTO(model.getId(),
                model.getName(),
                model.getCategory(),
                model.getStartYear(),
                model.getEndYear(),
                model.getImageUrl());
    }
}
