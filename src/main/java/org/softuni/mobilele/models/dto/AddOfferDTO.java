package org.softuni.mobilele.models.dto;

import org.softuni.mobilele.models.entity.enums.EngineEnum;
import org.softuni.mobilele.models.entity.enums.TransmissionEnum;

import java.math.BigDecimal;

public record AddOfferDTO(Long model,
                          BigDecimal price,
                          EngineEnum engine,
                          TransmissionEnum transmission,
                          Integer year,
                          Integer mileage,
                          String description,
                          String imageUrl) {
}
