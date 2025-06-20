package com.jhops10.study_tracker.dto.technology;

import com.jhops10.study_tracker.model.Technology;

public record TechnologyResponseDTO(
        Long id,
        String name,
        String imageUrl,
        String shortDescription
) {

    public static TechnologyResponseDTO fromEntity(Technology entity) {
        return new TechnologyResponseDTO(
                entity.getId(),
                entity.getName(),
                entity.getImageUrl(),
                entity.getShortDescription()
        );
    }
}
