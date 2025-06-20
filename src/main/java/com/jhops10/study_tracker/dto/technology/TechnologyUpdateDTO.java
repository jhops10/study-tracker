package com.jhops10.study_tracker.dto.technology;

import com.jhops10.study_tracker.model.Technology;

public record TechnologyUpdateDTO(
        String name,
        String imageUrl,
        String shortDescription
) {

    public void applyUpdatesTo(Technology entity) {
        if (name != null) entity.setName(name);
        if (imageUrl != null) entity.setImageUrl(imageUrl);
        if (shortDescription != null) entity.setShortDescription(shortDescription);
    }
}
