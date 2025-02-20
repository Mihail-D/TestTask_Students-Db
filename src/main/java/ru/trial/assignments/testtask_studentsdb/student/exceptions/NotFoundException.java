package ru.trial.assignments.testtask_studentsdb.student.exceptions;

public class NotFoundException extends RuntimeException {

    public NotFoundException(Class<?> entityClass, String message) {
        super(entityClass.getSimpleName() + ": " + message);
    }
}
