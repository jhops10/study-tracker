package com.jhops10.study_tracker.dto.study_session;

import com.jhops10.study_tracker.model.StudySession;

import java.util.Date;

public record StudySessionResponseDTO(
        Long id,
        Date date,
        String topic,
        Long technologyId,
        double hoursStudied
) {
    public static StudySessionResponseDTO fromEntity(StudySession studySession) {
        return new StudySessionResponseDTO(
                studySession.getId(),
                studySession.getDate(),
                studySession.getTopic(),
                studySession.getTechnology().getId(),
                studySession.getHoursStudied()
        );
    }
}
