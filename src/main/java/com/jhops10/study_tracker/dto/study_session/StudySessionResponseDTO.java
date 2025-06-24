package com.jhops10.study_tracker.dto.study_session;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jhops10.study_tracker.model.StudySession;

import java.util.Date;

public record StudySessionResponseDTO(
        Long id,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
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
