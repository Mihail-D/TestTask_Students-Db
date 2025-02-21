package ru.trial.assignments.testtask_studentsdb.student.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.trial.assignments.testtask_studentsdb.student.dto.StudentDto;
import ru.trial.assignments.testtask_studentsdb.student.service.StudentService;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/students")
public class StudentController {

    StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping()
    public ResponseEntity<Void> addStudent(@RequestBody @Valid StudentDto studentDto) {
        studentService.addStudent(studentDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping()
    ResponseEntity<Void> updateStudent(@RequestBody @Valid StudentDto studentDto) {
        studentService.updateStudent(studentDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteStudent(@RequestBody @Valid StudentDto studentDto) {
        studentService.deleteStudent(studentDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/byLastName")
    public List<StudentDto> findAllStudentsByLastName(@Valid String lastName) {
        return studentService.findAllStudentsByLastName(lastName);
    }

    @GetMapping("/byGroupNumber")
    public List<StudentDto> getStudentsByGroupNumber(@Valid String groupId) {
        return studentService.getStudentsByGroupNumber(groupId);
    }

}
