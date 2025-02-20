package ru.trial.assignments.testtask_studentsdb.student.service;

import org.springframework.stereotype.Service;
import ru.trial.assignments.testtask_studentsdb.student.dto.StudentDto;
import ru.trial.assignments.testtask_studentsdb.student.exceptions.NotFoundException;
import ru.trial.assignments.testtask_studentsdb.student.mapper.StudentMapper;
import ru.trial.assignments.testtask_studentsdb.student.model.Student;
import ru.trial.assignments.testtask_studentsdb.student.repository.StudentRepository;
import ru.trial.assignments.testtask_studentsdb.student.utility.Validator;

@Service

public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final Validator validator;

    public StudentServiceImpl(StudentRepository studentRepository, StudentMapper studentMapper, Validator validator) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.validator = validator;
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
        if (!validator.isStudentExists(studentDto.getId(), studentRepository)) {
            throw new NotFoundException(Student.class, " not found");
        }
        Student student = studentMapper.toEntity(studentDto);
        studentRepository.delete(student);
    }

    /*    @Override

    public List<StudentDto> getStudentsByGroupNumber(String groupNumber) {
        return List.of();
    }*/




    /*    @Override
    public List<StudentDto> getStudents() {
        return null;
    }*/

/*    @Override
    public List<StudentDto> getStudentsByLastName(String lastName) {
        return List.of();
    }*/

}
