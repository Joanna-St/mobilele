package org.softuni.mobilele.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.softuni.mobilele.models.enums.EngineEnum;
import org.softuni.mobilele.models.enums.TransmissionEnum;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddOfferDTO {
    @NotNull
    private Long model;
    @NotNull
    private BigDecimal price;
    @NotNull
    private EngineEnum engine;
    @NotNull
    private TransmissionEnum transmission;
    @NotNull
    @Positive
    private Integer year;
    @NotNull
    @Positive
    private Integer mileage;
    @NotBlank
    private String description;
    @NotBlank
    @Size(min = 8, max = 512)
    private String imageUrl;
}
