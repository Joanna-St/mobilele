package org.softuni.mobilele.models.dto;

import org.softuni.mobilele.models.entity.enums.CarCategoryEnum;

import java.time.LocalDateTime;

public record ModelDTO(Long id,
                       String name,
                       CarCategoryEnum category,
                       Integer startYear,
                       Integer endYear,
                       String imageUrl) {
}
