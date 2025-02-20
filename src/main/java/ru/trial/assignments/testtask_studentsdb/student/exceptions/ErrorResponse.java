package ru.trial.assignments.testtask_studentsdb.student.exceptions;

public record ErrorResponse(String error) {

    @Override
    @SuppressWarnings("unused")
    public String error() {
        return error;
    }
}
