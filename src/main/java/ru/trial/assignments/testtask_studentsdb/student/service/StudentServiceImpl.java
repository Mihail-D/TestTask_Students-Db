package ru.trial.assignments.testtask_studentsdb.student.service;

import org.springframework.stereotype.Service;
import ru.trial.assignments.testtask_studentsdb.student.dto.StudentDto;
import ru.trial.assignments.testtask_studentsdb.student.mapper.StudentMapper;
import ru.trial.assignments.testtask_studentsdb.student.model.Student;
import ru.trial.assignments.testtask_studentsdb.student.repository.StudentRepository;

@Service

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    /*    @Override

    public List<StudentDto> getStudentsByGroupNumber(String groupNumber) {
        return List.of();
    }*/

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    public void addStudent(StudentDto studentDto) {
        Student student = studentMapper.toEntity(studentDto);
        studentRepository.save(student);
    }

    @Override
    public void updateStudent(StudentDto studentDto) {
        Student student = studentMapper.toEntity(studentDto);
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(StudentDto studentDto) {

    }



    /*    @Override
    public List<StudentDto> getStudents() {
        return null;
    }*/

/*    @Override
    public List<StudentDto> getStudentsByLastName(String lastName) {
        return List.of();
    }*/

}
