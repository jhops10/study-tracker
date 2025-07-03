package com.jhops10.study_tracker.service;

import com.jhops10.study_tracker.dto.technology.TechnologyRequestDTO;
import com.jhops10.study_tracker.dto.technology.TechnologyResponseDTO;
import com.jhops10.study_tracker.dto.technology.TechnologyUpdateDTO;
import com.jhops10.study_tracker.exception.TechnologyNotFoundException;
import com.jhops10.study_tracker.model.Technology;
import com.jhops10.study_tracker.repository.TechnologyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TechnologyServiceTest {

    @InjectMocks
    private TechnologyService technologyService;

    @Mock
    private TechnologyRepository technologyRepository;


    private Technology defaultTech;

    @BeforeEach
    void setUp() {
        defaultTech = createNewTech(1L, "Example Name", "www.exampleurl.com", "Short description");
    }

    private Technology createNewTech(Long id, String name, String imgUrl, String shortDescription) {
        return Technology.builder()
                .id(id)
                .name(name)
                .imageUrl(imgUrl)
                .shortDescription(shortDescription)
                .build();
    }

    @Test
    void createTechnology_shouldReturnTechnology() {
        TechnologyRequestDTO requestDTO = new TechnologyRequestDTO(defaultTech.getName(), defaultTech.getImageUrl(), defaultTech.getShortDescription());

        when(technologyRepository.save(any(Technology.class))).thenReturn(defaultTech);

        TechnologyResponseDTO sut = technologyService.create(requestDTO);

        assertNotNull(sut);
        assertEquals(defaultTech.getId(), sut.id());
        assertEquals(defaultTech.getName(), sut.name());
        assertEquals(defaultTech.getImageUrl(), sut.imageUrl());
        assertEquals(defaultTech.getShortDescription(), sut.shortDescription());

        verify(technologyRepository).save(any(Technology.class));
        verifyNoMoreInteractions(technologyRepository);
    }

    @Test
    void getAllTechnologies_shouldReturnAllTechnologies() {
        when(technologyRepository.findAll()).thenReturn(List.of(defaultTech));

        List<TechnologyResponseDTO> sut = technologyService.getAll();

        assertEquals(1, sut.size());
        assertEquals(1, sut.get(0).id());
        assertEquals("Example Name", sut.get(0).name());
        assertEquals("www.exampleurl.com", sut.get(0).imageUrl());
        assertEquals("Short description", sut.get(0).shortDescription());

        verify(technologyRepository).findAll();
        verifyNoMoreInteractions(technologyRepository);
    }

    @Test
    void getAllTechnologies_whenNoneAvailable_shouldReturnEmptyList() {
        when(technologyRepository.findAll()).thenReturn(List.of());

        List<TechnologyResponseDTO> sut = technologyService.getAll();

        assertNotNull(sut);
        assertTrue(sut.isEmpty());

        verify(technologyRepository).findAll();
        verifyNoMoreInteractions(technologyRepository);
    }

    @Test
    void getTechnologyById_whenIdExists_shouldReturnTechnology() {
        when(technologyRepository.findById(1L)).thenReturn(Optional.of(defaultTech));

        TechnologyResponseDTO sut = technologyService.getById(1L);

        assertEquals("Example Name", sut.name());
        assertEquals("www.exampleurl.com", sut.imageUrl());
        assertEquals("Short description", sut.shortDescription());

        verify(technologyRepository).findById(1L);
        verifyNoMoreInteractions(technologyRepository);
    }

    @Test
    void getTechnologyById_whenIdDoesNotExist_shouldThrowException() {
        when(technologyRepository.findById(99999L)).thenReturn(Optional.empty());

        assertThrows(TechnologyNotFoundException.class, () -> technologyService.getById(99999L));

        verify(technologyRepository).findById(99999L);
        verifyNoMoreInteractions(technologyRepository);
    }

    @Test
    void updateTechnology_whenIdExists_shouldReturnUpdatedTechnology() {
        TechnologyUpdateDTO updateDTO = new TechnologyUpdateDTO("Updated Name", "www.updatedurl.com", "Updated description");
        Technology updatedTech = createNewTech(defaultTech.getId(), updateDTO.name(), updateDTO.imageUrl(), updateDTO.shortDescription());

        when(technologyRepository.findById(defaultTech.getId())).thenReturn(Optional.of(defaultTech));
        when(technologyRepository.save(any(Technology.class))).thenReturn(updatedTech);


        TechnologyResponseDTO sut = technologyService.update(defaultTech.getId(), updateDTO);
        
        assertNotNull(sut);
        assertEquals(updatedTech.getId(), sut.id());
        assertEquals(updatedTech.getName(), sut.name());
        assertEquals(updatedTech.getImageUrl(), sut.imageUrl());
        assertEquals(updatedTech.getShortDescription(), sut.shortDescription());

        verify(technologyRepository).findById(defaultTech.getId());
        verify(technologyRepository).save(defaultTech);
        verifyNoMoreInteractions(technologyRepository);
    }

    @Test
    void updateTechnology_whenIdDoesNotExist_shouldThrowException() {
        TechnologyUpdateDTO updateDTO = new TechnologyUpdateDTO("Updated Name", "updated-url", "Updated Description");

        when(technologyRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(TechnologyNotFoundException.class, () -> technologyService.update(999L, updateDTO));

        verify(technologyRepository).findById(999L);
        verifyNoMoreInteractions(technologyRepository);
    }

    @Test
    void deleteTechnology_whenIdExists_shouldDeleteTechnologySuccessfully() {
        when(technologyRepository.existsById(defaultTech.getId())).thenReturn(true);

        technologyService.delete(defaultTech.getId());

        verify(technologyRepository).existsById(1L);
        verify(technologyRepository).deleteById(1L);
        verifyNoMoreInteractions(technologyRepository);
    }

    @Test
    void deleteTechnology_whenIdDoesNotExist_shouldThrowException() {
        when(technologyRepository.existsById(999L)).thenReturn(false);

        assertThrows(TechnologyNotFoundException.class, () -> technologyService.delete(999L));

        verify(technologyRepository).existsById(999L);
        verifyNoMoreInteractions(technologyRepository);
    }
}