package com.jhops10.study_tracker.controller;

import com.jhops10.study_tracker.dto.technology.TechnologyRequestDTO;
import com.jhops10.study_tracker.dto.technology.TechnologyResponseDTO;
import com.jhops10.study_tracker.dto.technology.TechnologyUpdateDTO;
import com.jhops10.study_tracker.service.TechnologyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/technologies")
@RequiredArgsConstructor
public class TechnologyController {

    private final TechnologyService technologyService;

    @PostMapping
    public ResponseEntity<TechnologyResponseDTO> create(@Valid @RequestBody TechnologyRequestDTO requestDTO) {
        TechnologyResponseDTO created = technologyService.create(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public ResponseEntity<List<TechnologyResponseDTO>> getAll() {
        List<TechnologyResponseDTO> allTechs = technologyService.getAll();
        return ResponseEntity.ok(allTechs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TechnologyResponseDTO> getById(@PathVariable("id") Long id) {
        TechnologyResponseDTO tech = technologyService.getById(id);
        return ResponseEntity.ok(tech);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TechnologyResponseDTO> update(@PathVariable("id") Long id, @RequestBody TechnologyUpdateDTO updateDTO) {
        TechnologyResponseDTO updated = technologyService.update(id, updateDTO);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        technologyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
