package ru.trial.assignments.testtask_studentsdb.student.exceptions;

public class ClassFieldErrorException extends RuntimeException {

    public ClassFieldErrorException(Class<?> entityClass, String message) {
        super(entityClass.getSimpleName() + ": " + message);
    }

}
