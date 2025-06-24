package com.jhops10.study_tracker.exception;

public class StudySessionNotFoundException extends RuntimeException {
    public StudySessionNotFoundException(String message) {
        super(message);
    }
}
