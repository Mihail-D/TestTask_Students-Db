package ru.trial.assignments.testtask_studentsdb.student.service;

import ru.trial.assignments.testtask_studentsdb.student.dto.StudentDto;

import java.util.List;

public interface StudentService {
    void addStudent(StudentDto studentDto);
    void updateStudent(StudentDto studentDto);
    void deleteStudent(StudentDto studentDto);
    List<StudentDto> findAllStudentsByLastName(String lastName);
    List<StudentDto> getStudentsByGroupNumber(String groupId);
    List<StudentDto> getStudents();
}
