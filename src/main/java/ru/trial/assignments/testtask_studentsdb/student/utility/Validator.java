package ru.trial.assignments.testtask_studentsdb.student.utility;

import org.springframework.stereotype.Component;
import ru.trial.assignments.testtask_studentsdb.student.dto.StudentDto;
import ru.trial.assignments.testtask_studentsdb.student.model.Student;
import ru.trial.assignments.testtask_studentsdb.student.repository.StudentRepository;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class Validator {

    public boolean isStudentExists(Long studentId, StudentRepository studentRepository) {
        return studentRepository.existsById(studentId);
    }

    public boolean isValidFirstName(String firstName) {
        return firstName != null && !firstName.trim().isEmpty() && !firstName.isEmpty() && firstName.length() <= 50;
    }

    public boolean isValidLastName(String lastName) {
        return lastName != null && !lastName.trim().isEmpty() && !lastName.isEmpty() && lastName.length() <= 50;
    }

    public boolean isValidMiddleName(String middleName) {
        return middleName == null || (!middleName.isEmpty() && middleName.length() <= 50);
    }

    public boolean isValidBirthDate(LocalDate birthDate) {
        return birthDate != null && birthDate.isBefore(LocalDate.now());
    }

    public boolean isValidGroupId(Long groupId) {
        return groupId != null && groupId > 0;
    }

    public boolean isValidStudent(Student student) {
        return isValidFirstName(student.getFirstName()) &&
                isValidLastName(student.getLastName()) &&
                isValidMiddleName(student.getMiddleName()) &&
                isValidBirthDate(student.getBirthDate()) &&
                isValidGroupId(student.getGroupId());
    }

    public Optional<String> isValidStudentDto(StudentDto studentDto) {
        if (!isValidFirstName(studentDto.getFirstName())) {
            return Optional.of("firstName");
        }
        if (!isValidLastName(studentDto.getLastName())) {
            return Optional.of("lastName");
        }
        if (!isValidMiddleName(studentDto.getMiddleName())) {
            return Optional.of("middleName");
        }
        if (!isValidBirthDate(studentDto.getBirthDate())) {
            return Optional.of("birthDate");
        }
        if (!isValidGroupId(studentDto.getGroupId())) {
            return Optional.of("groupId");
        }
        return Optional.empty();
    }
}
