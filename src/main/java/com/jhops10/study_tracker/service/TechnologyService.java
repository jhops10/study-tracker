package com.jhops10.study_tracker.service;

import com.jhops10.study_tracker.dto.technology.TechnologyRequestDTO;
import com.jhops10.study_tracker.dto.technology.TechnologyResponseDTO;
import com.jhops10.study_tracker.dto.technology.TechnologyUpdateDTO;
import com.jhops10.study_tracker.exception.TechnologyNotFoundException;
import com.jhops10.study_tracker.model.Technology;
import com.jhops10.study_tracker.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnologyService {

    private final TechnologyRepository technologyRepository;


    public TechnologyResponseDTO create(TechnologyRequestDTO dto) {
        Technology technology = dto.toEntity();
        Technology saved = technologyRepository.save(technology);
        return TechnologyResponseDTO.fromEntity(saved);
    }

    public List<TechnologyResponseDTO> getAll() {
        return technologyRepository.findAll().stream()
                .map(TechnologyResponseDTO::fromEntity)
                .toList();
    }

    public TechnologyResponseDTO getById(Long id) {
        Technology tech = technologyRepository.findById(id)
                .orElseThrow(() -> new TechnologyNotFoundException("Tecnologia com o id " + id + " não encontrada."));
        return TechnologyResponseDTO.fromEntity(tech);
    }

    public TechnologyResponseDTO update(Long id, TechnologyUpdateDTO updateDTO) {
        Technology existing = technologyRepository.findById(id)
                .orElseThrow(() -> new TechnologyNotFoundException("Tecnologia com o id " + id + " não encontrada."));

        updateDTO.applyUpdatesTo(existing);

        Technology updated = technologyRepository.save(existing);
        return TechnologyResponseDTO.fromEntity(updated);
    }

    public void delete(Long id) {
        if (!technologyRepository.existsById(id)) {
            throw new TechnologyNotFoundException("Tecnologia com o id " + id + " não encontrada.");
        }
        technologyRepository.deleteById(id);
    }

}
