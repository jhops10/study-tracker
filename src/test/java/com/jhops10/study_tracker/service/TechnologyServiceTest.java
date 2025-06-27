package com.jhops10.study_tracker.service;

import com.jhops10.study_tracker.dto.technology.TechnologyRequestDTO;
import com.jhops10.study_tracker.dto.technology.TechnologyResponseDTO;
import com.jhops10.study_tracker.model.Technology;
import com.jhops10.study_tracker.repository.TechnologyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
}