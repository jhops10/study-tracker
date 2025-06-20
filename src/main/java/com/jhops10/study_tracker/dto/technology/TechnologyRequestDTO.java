package com.jhops10.study_tracker.dto.technology;

import com.jhops10.study_tracker.model.Technology;
import jakarta.validation.constraints.NotBlank;

public record TechnologyRequestDTO(
        @NotBlank(message = "O campo nome é obrigatório")
        String name,
        String imageUrl,
        String shortDescription
) {
        public Technology toEntity() {
                return Technology.builder()
                        .name(name)
                        .imageUrl(imageUrl)
                        .shortDescription(shortDescription)
                        .build();
        }
}
