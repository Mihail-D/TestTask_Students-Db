package ru.trial.assignments.testtask_studentsdb.student.mapper;

import org.springframework.stereotype.Component;
import ru.trial.assignments.testtask_studentsdb.student.dto.StudentDto;
import ru.trial.assignments.testtask_studentsdb.student.model.Student;

@Component
public class StudentMapper {

    public Student toEntity(StudentDto studentDto) {
        if (studentDto == null) {
            return null;
        }

        return new Student(
                studentDto.getId(),
                studentDto.getFirstName(),
                studentDto.getLastName(),
                studentDto.getMiddleName(),
                studentDto.getBirthDate(),
                studentDto.getGroupId()
        );
    }

    public StudentDto toDto(Student student) {
        if (student == null) {
            return null;
        }

        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getMiddleName(),
                student.getBirthDate(),
                student.getGroupId()
        );
    }
}
