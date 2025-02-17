package ru.trial.assignments.testtask_studentsdb.student.service;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trial.assignments.testtask_studentsdb.student.dto.StudentDto;
import ru.trial.assignments.testtask_studentsdb.student.mapper.StudentMapper;
import ru.trial.assignments.testtask_studentsdb.student.model.Student;
import ru.trial.assignments.testtask_studentsdb.student.repository.StudentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    @Override
    public void addStudent(StudentDto studentDto) {
        Student student = studentMapper.toEntity(studentDto);
        studentRepository.save(student);
    }

/*    @Override
    public List<StudentDto> getStudents() {
        return null;
    }*/

/*    @Override
    public List<StudentDto> getStudentsByLastName(String lastName) {
        return List.of();
    }*/

/*    @Override
    public List<StudentDto> getStudentsByGroupNumber(String groupNumber) {
        return List.of();
    }*/

/*    @Override
    public void editStudent(StudentDto studentDto) {

    }*/

/*    @Override
    public void deleteStudent(StudentDto studentDto) {

    }*/
}
