package com.jhops10.study_tracker.dto.study_session;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.util.Date;

public record StudySessionRequestDTO(
        @NotNull(message = "A data da sessão é obrigatória")
        @PastOrPresent(message = "A data não pode estar no futuro")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        Date date,
        @NotBlank(message = "O tópico é obrigatório")
        String topic,
        @NotNull(message = "O ID da tecnologia é obrigatório")
        Long technologyId,
        @Positive(message = "As horas estudadas devem ser um valor positivo")
        double hoursStudied
) {
}
