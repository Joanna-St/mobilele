package org.softuni.mobilele.models.dto;

import org.softuni.mobilele.models.enums.CarCategoryEnum;

public record ModelDTO(Long id,
                       String name,
                       CarCategoryEnum category,
                       Integer startYear,
                       Integer endYear,
                       String imageUrl) {
}
