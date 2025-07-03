package com.jhops10.study_tracker.service;

import com.jhops10.study_tracker.dto.study_session.StudySessionRequestDTO;
import com.jhops10.study_tracker.dto.study_session.StudySessionResponseDTO;
import com.jhops10.study_tracker.exception.TechnologyNotFoundException;
import com.jhops10.study_tracker.model.StudySession;
import com.jhops10.study_tracker.model.Technology;
import com.jhops10.study_tracker.repository.StudySessionRepository;
import com.jhops10.study_tracker.repository.TechnologyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudySessionServiceTest {

    @InjectMocks
    private StudySessionService studySessionService;

    @Mock
    private StudySessionRepository studySessionRepository;

    @Mock
    private TechnologyRepository technologyRepository;

    private StudySession defaultStudySession;

    private Technology defaultTechnology;

    @BeforeEach
    void setUp() throws ParseException {
        defaultTechnology = createNewTech(1L, "Example Name", "www.exampleurl.com", "Short description");

        Date date = new SimpleDateFormat("dd-MM-yyyy").parse("22-03-2033");

        defaultStudySession = createNewSession(1L, date, "Example Topic", defaultTechnology, 2.5);
    }

    private StudySession createNewSession(Long id, Date date, String topic, Technology tech, Double hoursStudied) {
        return StudySession.builder()
                .id(id)
                .date(date)
                .topic(topic)
                .technology(tech)
                .hoursStudied(hoursStudied)
                .build();
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
    void createStudySession_shouldReturnStudySession() {
        StudySessionRequestDTO requestDTO = new StudySessionRequestDTO(defaultStudySession.getDate(), defaultStudySession.getTopic(), defaultStudySession.getTechnology().getId(), defaultStudySession.getHoursStudied());

        when(studySessionRepository.save(any(StudySession.class))).thenReturn(defaultStudySession);
        when(technologyRepository.findById(1L)).thenReturn(Optional.of(defaultTechnology));

        StudySessionResponseDTO sut = studySessionService.create(requestDTO);

        assertNotNull(sut);
        assertEquals(1L, sut.id());
        assertEquals("Example Topic", sut.topic());
        assertEquals(1L, sut.technologyId());
        assertEquals(2.5, sut.hoursStudied());

        verify(studySessionRepository).save(any(StudySession.class));
        verify(technologyRepository).findById(1L);
        verifyNoMoreInteractions(studySessionRepository);
        verifyNoMoreInteractions(technologyRepository);
    }

    @Test
    void createStudySession_whenTechnologyIdDoesNotExists_shouldThrowException() {
        Long nonexistentTechId = 999999L;
        StudySessionRequestDTO requestDTO = new StudySessionRequestDTO(defaultStudySession.getDate(), defaultStudySession.getTopic(), nonexistentTechId, defaultStudySession.getHoursStudied());

        when(technologyRepository.findById(nonexistentTechId)).thenReturn(Optional.empty());

        assertThrows(TechnologyNotFoundException.class, () -> studySessionService.create(requestDTO));

        verify(technologyRepository).findById(nonexistentTechId);
        verifyNoMoreInteractions(technologyRepository);
        verifyNoInteractions(studySessionRepository);
    }

    @Test
    void getAll_shouldReturnAllStudySessions() {
        when(studySessionRepository.findAll()).thenReturn(List.of(defaultStudySession));

        List<StudySessionResponseDTO> sut = studySessionService.getAll();

        assertEquals(1, sut.size());
        assertEquals(1, sut.get(0).id());
        assertEquals("Example Topic", sut.get(0).topic());
        assertEquals(1L, sut.get(0).technologyId());
        assertEquals(2.5, sut.get(0).hoursStudied());

        verify(studySessionRepository).findAll();
        verifyNoMoreInteractions(studySessionRepository);
    }

    @Test
    void getAll_whenNoneAvailable_shouldReturnEmptyList() {
        when(studySessionRepository.findAll()).thenReturn(List.of());

        List<StudySessionResponseDTO> sut = studySessionService.getAll();

        assertNotNull(sut);
        assertTrue(sut.isEmpty());

        verify(studySessionRepository).findAll();
        verifyNoMoreInteractions(studySessionRepository);
    }



}