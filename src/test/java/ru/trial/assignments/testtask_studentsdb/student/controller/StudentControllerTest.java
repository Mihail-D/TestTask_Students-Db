package ru.trial.assignments.testtask_studentsdb.student.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.trial.assignments.testtask_studentsdb.student.dto.StudentDto;
import ru.trial.assignments.testtask_studentsdb.student.service.StudentService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    public StudentControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addStudent_createsStudent() {
        StudentDto studentDto = new StudentDto();
        doNothing().when(studentService).addStudent(any(StudentDto.class));

        ResponseEntity<Void> response = studentController.addStudent(studentDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(studentService, times(1)).addStudent(studentDto);
    }

    @Test
    void updateStudent_updatesStudent() {
        StudentDto studentDto = new StudentDto();
        doNothing().when(studentService).updateStudent(any(StudentDto.class));

        ResponseEntity<Void> response = studentController.updateStudent(studentDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(studentService, times(1)).updateStudent(studentDto);
    }

    @Test
    void deleteStudent_deletesStudent() {
        StudentDto studentDto = new StudentDto();
        doNothing().when(studentService).deleteStudent(any(StudentDto.class));

        ResponseEntity<Void> response = studentController.deleteStudent(studentDto);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(studentService, times(1)).deleteStudent(studentDto);
    }

    @Test
    void findAllStudentsByLastName_returnsStudents() {
        String lastName = "Doe";
        List<StudentDto> students = Collections.singletonList(new StudentDto());
        when(studentService.findAllStudentsByLastName(lastName)).thenReturn(students);

        List<StudentDto> response = studentController.findAllStudentsByLastName(lastName);

        assertEquals(students, response);
        verify(studentService, times(1)).findAllStudentsByLastName(lastName);
    }

    @Test
    void getStudentsByGroupNumber_returnsStudents() {
        String groupId = "123";
        List<StudentDto> students = Collections.singletonList(new StudentDto());
        when(studentService.getStudentsByGroupNumber(groupId)).thenReturn(students);

        List<StudentDto> response = studentController.getStudentsByGroupNumber(groupId);

        assertEquals(students, response);
        verify(studentService, times(1)).getStudentsByGroupNumber(groupId);
    }

    @Test
    void getStudents_returnsAllStudents() {
        List<StudentDto> students = Collections.singletonList(new StudentDto());
        when(studentService.getStudents()).thenReturn(students);

        List<StudentDto> response = studentController.getStudents();

        assertEquals(students, response);
        verify(studentService, times(1)).getStudents();
    }
}
