package ru.trial.assignments.testtask_studentsdb.student.service;

import org.springframework.stereotype.Service;
import ru.trial.assignments.testtask_studentsdb.student.dto.StudentDto;
import ru.trial.assignments.testtask_studentsdb.student.exceptions.ClassFieldErrorException;
import ru.trial.assignments.testtask_studentsdb.student.exceptions.NotFoundException;
import ru.trial.assignments.testtask_studentsdb.student.mapper.StudentMapper;
import ru.trial.assignments.testtask_studentsdb.student.model.Student;
import ru.trial.assignments.testtask_studentsdb.student.repository.StudentRepository;
import ru.trial.assignments.testtask_studentsdb.student.utility.Validator;

import java.util.List;
import java.util.Optional;

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
        validator.isValidStudentDto(studentDto).ifPresent(field -> {
            throw new ClassFieldErrorException(StudentDto.class, "Invalid field: " + field);
        });

        Optional<Student> existingStudent = studentRepository.findByFirstNameAndLastNameAndMiddleNameAndBirthDateAndGroupId(
                studentDto.getFirstName(), studentDto.getLastName(), studentDto.getMiddleName(), studentDto.getBirthDate(), studentDto.getGroupId());

        if (existingStudent.isPresent()) {
            throw new IllegalArgumentException("Student with the same details already exists");
        }

        Student student = studentMapper.toEntity(studentDto);
        studentRepository.save(student);
    }

    @Override
    public void updateStudent(StudentDto studentDto) {
        if (!validator.isStudentExists(studentDto.getId(), studentRepository)) {
            throw new NotFoundException(Student.class, " not found");
        }
        Student student = studentMapper.toEntity(studentDto);
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(StudentDto studentDto) {
        if (studentDto.getId() == null) {
            throw new IllegalArgumentException("Student ID must not be null");
        }
        if (!validator.isStudentExists(studentDto.getId(), studentRepository)) {
            throw new NotFoundException(Student.class, " not found");
        }
        Student student = studentMapper.toEntity(studentDto);
        studentRepository.delete(student);
    }

    @Override
    public List<StudentDto> findAllStudentsByLastName(String lastName) {
        return studentRepository.findAllStudentsByLastName(lastName);
    }

    @Override
    public List<StudentDto> getStudentsByGroupNumber(String groupId) {
        return studentRepository.findAllStudentsByGroupId(groupId);
    }




    /*    @Override
    public List<StudentDto> getStudents() {
        return null;
    }*/

}
