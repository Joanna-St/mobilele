package org.softuni.mobilele.service;

import org.softuni.mobilele.models.dto.BrandDTO;
import org.softuni.mobilele.models.entity.Brand;

import java.util.List;

public interface AllBrandsService {
    List<BrandDTO> getAllBrands();
}
