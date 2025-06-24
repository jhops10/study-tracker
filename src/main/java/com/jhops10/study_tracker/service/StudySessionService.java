package com.jhops10.study_tracker.service;

import com.jhops10.study_tracker.dto.study_session.StudySessionRequestDTO;
import com.jhops10.study_tracker.dto.study_session.StudySessionResponseDTO;
import com.jhops10.study_tracker.dto.study_session.StudySessionUpdateDTO;
import com.jhops10.study_tracker.exception.StudySessionNotFoundException;
import com.jhops10.study_tracker.exception.TechnologyNotFoundException;
import com.jhops10.study_tracker.model.StudySession;
import com.jhops10.study_tracker.model.Technology;
import com.jhops10.study_tracker.repository.StudySessionRepository;
import com.jhops10.study_tracker.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudySessionService {

    private final StudySessionRepository studySessionRepository;
    private final TechnologyRepository technologyRepository;

    public StudySessionResponseDTO create(StudySessionRequestDTO dto) {
        Technology technology = technologyRepository.findById(dto.technologyId())
                .orElseThrow(() -> new TechnologyNotFoundException("Tecnologia com id " + dto.technologyId() + " não encontrada"));

        StudySession session = StudySession.builder()
                .date(dto.date())
                .topic(dto.topic())
                .technology(technology)
                .hoursStudied(dto.hoursStudied())
                .build();

        StudySession saved = studySessionRepository.save(session);
        return StudySessionResponseDTO.fromEntity(saved);
    }

    public List<StudySessionResponseDTO> getAll() {
        return studySessionRepository.findAll().stream()
                .map(StudySessionResponseDTO::fromEntity)
                .toList();
    }

    public StudySessionResponseDTO getById(Long id) {
        StudySession session = studySessionRepository.findById(id)
                .orElseThrow(() -> new StudySessionNotFoundException("Sessão com id " + id + " não encontrada"));
        return StudySessionResponseDTO.fromEntity(session);
    }

    public StudySessionResponseDTO update(Long id, StudySessionUpdateDTO updateDTO) {
        StudySession existingSession = studySessionRepository.findById(id)
                .orElseThrow(() -> new StudySessionNotFoundException("Sessão com id " + id + " não encontrada"));

        updateDTO.applyUpdatesTo(existingSession, technologyRepository);

        StudySession updated = studySessionRepository.save(existingSession);
        return StudySessionResponseDTO.fromEntity(updated);
    }

    public void delete(Long id) {
        if (!studySessionRepository.existsById(id)) {
            throw new StudySessionNotFoundException("Sessão com o id " + id + " não encontrada.");
        }
        studySessionRepository.deleteById(id);
    }

}
