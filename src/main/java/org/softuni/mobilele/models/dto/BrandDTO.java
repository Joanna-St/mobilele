package org.softuni.mobilele.models.dto;

import java.util.List;

public record BrandDTO(String name,
                       List<ModelDTO> models) {
}
