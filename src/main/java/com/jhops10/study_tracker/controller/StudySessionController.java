package com.jhops10.study_tracker.controller;

import com.jhops10.study_tracker.dto.study_session.StudySessionRequestDTO;
import com.jhops10.study_tracker.dto.study_session.StudySessionResponseDTO;
import com.jhops10.study_tracker.dto.study_session.StudySessionUpdateDTO;
import com.jhops10.study_tracker.service.StudySessionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
@RequiredArgsConstructor
public class StudySessionController {

    private final StudySessionService studySessionService;

    @PostMapping
    public ResponseEntity<StudySessionResponseDTO> create(@Valid @RequestBody StudySessionRequestDTO requestDTO) {
        StudySessionResponseDTO created = studySessionService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<StudySessionResponseDTO>> getAll() {
        List<StudySessionResponseDTO> allSessions = studySessionService.getAll();
        return ResponseEntity.ok(allSessions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudySessionResponseDTO> getById(@PathVariable("id") Long id) {
        StudySessionResponseDTO session = studySessionService.getById(id);
        return ResponseEntity.ok(session);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudySessionResponseDTO> update(@PathVariable("id") Long id, @RequestBody StudySessionUpdateDTO updateDTO) {
        StudySessionResponseDTO updated = studySessionService.update(id, updateDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        studySessionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
