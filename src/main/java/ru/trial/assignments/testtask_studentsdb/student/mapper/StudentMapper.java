package ru.trial.assignments.testtask_studentsdb.student.mapper;

import org.mapstruct.Mapper;
import ru.trial.assignments.testtask_studentsdb.student.dto.StudentDto;
import ru.trial.assignments.testtask_studentsdb.student.model.Student;

@Mapper
public interface StudentMapper {
    StudentDto toDto(Student student);
    Student toEntity(StudentDto studentDto);
}
