package ru.trial.assignments.testtask_studentsdb.utility;

import org.springframework.stereotype.Component;
import ru.trial.assignments.testtask_studentsdb.group.dto.GroupDto;
import ru.trial.assignments.testtask_studentsdb.group.repository.GroupRepository;
import ru.trial.assignments.testtask_studentsdb.student.dto.StudentDto;
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

    public Optional<String> isValidGroupDto(GroupDto groupDto) {
        if (groupDto.getGroupId() == null || groupDto.getGroupId().trim().isEmpty()) {
            return Optional.of("groupId");
        }
        if (groupDto.getGroupName() == null || groupDto.getGroupName().trim().isEmpty() || groupDto.getGroupName().length() > 100) {
            return Optional.of("groupName");
        }
        return Optional.empty();
    }

    public boolean isGroupExists(Long groupId, GroupRepository groupRepository) {
        return groupRepository.existsById(groupId);
    }
}
