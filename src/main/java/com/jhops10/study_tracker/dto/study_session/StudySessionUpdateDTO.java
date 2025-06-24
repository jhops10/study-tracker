package com.jhops10.study_tracker.dto.study_session;

import com.jhops10.study_tracker.exception.TechnologyNotFoundException;
import com.jhops10.study_tracker.model.StudySession;
import com.jhops10.study_tracker.model.Technology;
import com.jhops10.study_tracker.repository.TechnologyRepository;

import java.util.Date;

public record StudySessionUpdateDTO(
        Date date,
        String topic,
        Long technologyId,
        Double hoursStudied
) {
    public void applyUpdatesTo(StudySession session, TechnologyRepository technologyRepository) {
        if (date != null) session.setDate(date);
        if (topic != null) session.setTopic(topic);
        if (technologyId != null) {
            Technology tech = technologyRepository.findById(technologyId)
                    .orElseThrow(() -> new TechnologyNotFoundException("Tecnologia com id " + technologyId + " não encontrada"));
            session.setTechnology(tech);
        };
        if (hoursStudied != null) session.setHoursStudied(hoursStudied);
    }
}
