package ru.trial.assignments.testtask_studentsdb.student.service;

import ru.trial.assignments.testtask_studentsdb.student.dto.StudentDto;
import ru.trial.assignments.testtask_studentsdb.student.model.Student;

import java.util.List;

public interface StudentService {
    void addStudent(StudentDto studentDto);
    //List<StudentDto> getStudents();
    //List<StudentDto> getStudentsByGroupNumber(String groupNumber);
    //List<StudentDto> getStudentsByLastName(String lastName);
    //void editStudent(StudentDto studentDto);
    //void deleteStudent(StudentDto studentDto);
}
