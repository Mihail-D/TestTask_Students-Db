package ru.trial.assignments.testtask_studentsdb.student.utility;

import lombok.experimental.UtilityClass;
import ru.trial.assignments.testtask_studentsdb.student.repository.StudentRepository;

@UtilityClass
public class Validator {
    StudentRepository studentRepository;

    public boolean isStudentExists(Long studentId) {
        return studentRepository.existsById(studentId);
    }


}
