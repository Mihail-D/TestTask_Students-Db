package ru.trial.assignments.testtask_studentsdb.utility;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.trial.assignments.testtask_studentsdb.group.dto.GroupDto;
import ru.trial.assignments.testtask_studentsdb.group.repository.GroupRepository;
import ru.trial.assignments.testtask_studentsdb.student.dto.StudentDto;
import ru.trial.assignments.testtask_studentsdb.student.repository.StudentRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ValidatorTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private Validator validator;

    @Test
    void isStudentExists_returnsTrue_whenStudentExists() {
        // Given
        Long studentId = 1L;
        when(studentRepository.existsById(studentId)).thenReturn(true);

        // When
        boolean result = validator.isStudentExists(studentId, studentRepository);

        // Then
        assertTrue(result);
        verify(studentRepository, times(1)).existsById(studentId);
    }

    @Test
    void isStudentExists_returnsFalse_whenStudentDoesNotExist() {
        // Given
        Long studentId = 1L;
        when(studentRepository.existsById(studentId)).thenReturn(false);

        // When
        boolean result = validator.isStudentExists(studentId, studentRepository);

        // Then
        assertFalse(result);
        verify(studentRepository, times(1)).existsById(studentId);
    }

    @Test
    void isValidFirstName_returnsTrue_forValidFirstName() {
        // When & Then
        assertTrue(validator.isValidFirstName("John"));
        assertTrue(validator.isValidFirstName("Jane"));
        assertTrue(validator.isValidFirstName("A".repeat(50))); // Max length
    }

    @Test
    void isValidFirstName_returnsFalse_forInvalidFirstName() {
        // When & Then
        assertFalse(validator.isValidFirstName(null));
        assertFalse(validator.isValidFirstName(""));
        assertFalse(validator.isValidFirstName("   "));
        assertFalse(validator.isValidFirstName("A".repeat(51))); // Over max length
    }

    @Test
    void isValidLastName_returnsTrue_forValidLastName() {
        // When & Then
        assertTrue(validator.isValidLastName("Doe"));
        assertTrue(validator.isValidLastName("Smith"));
        assertTrue(validator.isValidLastName("A".repeat(50))); // Max length
    }

    @Test
    void isValidLastName_returnsFalse_forInvalidLastName() {
        // When & Then
        assertFalse(validator.isValidLastName(null));
        assertFalse(validator.isValidLastName(""));
        assertFalse(validator.isValidLastName("   "));
        assertFalse(validator.isValidLastName("A".repeat(51))); // Over max length
    }

    @Test
    void isValidMiddleName_returnsTrue_forValidMiddleName() {
        // When & Then
        assertTrue(validator.isValidMiddleName(null)); // Null is valid
        assertTrue(validator.isValidMiddleName("Middle"));
        assertTrue(validator.isValidMiddleName("A".repeat(50))); // Max length
    }

    @Test
    void isValidMiddleName_returnsFalse_forInvalidMiddleName() {
        // When & Then
        assertFalse(validator.isValidMiddleName(""));
        assertFalse(validator.isValidMiddleName("A".repeat(51))); // Over max length
    }

    @Test
    void isValidBirthDate_returnsTrue_forValidBirthDate() {
        // When & Then
        assertTrue(validator.isValidBirthDate(LocalDate.of(2000, 1, 1)));
        assertTrue(validator.isValidBirthDate(LocalDate.now().minusDays(1)));
    }

    @Test
    void isValidBirthDate_returnsFalse_forInvalidBirthDate() {
        // When & Then
        assertFalse(validator.isValidBirthDate(null));
        assertFalse(validator.isValidBirthDate(LocalDate.now()));
        assertFalse(validator.isValidBirthDate(LocalDate.now().plusDays(1)));
    }

    @Test
    void isValidGroupId_returnsTrue_forValidGroupId() {
        // When & Then
        assertTrue(validator.isValidGroupId(1L));
        assertTrue(validator.isValidGroupId(999L));
    }

    @Test
    void isValidGroupId_returnsFalse_forInvalidGroupId() {
        // When & Then
        assertFalse(validator.isValidGroupId(null));
        assertFalse(validator.isValidGroupId(0L));
        assertFalse(validator.isValidGroupId(-1L));
    }

    @Test
    void isValidStudentDto_returnsEmpty_forValidStudent() {
        // Given
        StudentDto validStudent = StudentDto.builder()
                .firstName("John")
                .lastName("Doe")
                .middleName("Middle")
                .birthDate(LocalDate.of(2000, 1, 1))
                .groupId(1L)
                .build();

        // When
        Optional<String> result = validator.isValidStudentDto(validStudent);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void isValidStudentDto_returnsFieldName_forInvalidFirstName() {
        // Given
        StudentDto invalidStudent = StudentDto.builder()
                .firstName("")
                .lastName("Doe")
                .middleName("Middle")
                .birthDate(LocalDate.of(2000, 1, 1))
                .groupId(1L)
                .build();

        // When
        Optional<String> result = validator.isValidStudentDto(invalidStudent);

        // Then
        assertTrue(result.isPresent());
        assertEquals("firstName", result.get());
    }

    @Test
    void isValidStudentDto_returnsFieldName_forInvalidLastName() {
        // Given
        StudentDto invalidStudent = StudentDto.builder()
                .firstName("John")
                .lastName(null)
                .middleName("Middle")
                .birthDate(LocalDate.of(2000, 1, 1))
                .groupId(1L)
                .build();

        // When
        Optional<String> result = validator.isValidStudentDto(invalidStudent);

        // Then
        assertTrue(result.isPresent());
        assertEquals("lastName", result.get());
    }

    @Test
    void isValidStudentDto_returnsFieldName_forInvalidBirthDate() {
        // Given
        StudentDto invalidStudent = StudentDto.builder()
                .firstName("John")
                .lastName("Doe")
                .middleName("Middle")
                .birthDate(LocalDate.now().plusDays(1))
                .groupId(1L)
                .build();

        // When
        Optional<String> result = validator.isValidStudentDto(invalidStudent);

        // Then
        assertTrue(result.isPresent());
        assertEquals("birthDate", result.get());
    }

    @Test
    void isValidGroupDto_returnsEmpty_forValidGroup() {
        // Given
        GroupDto validGroup = GroupDto.builder()
                .groupId("GR-001")
                .groupName("Computer Science")
                .build();

        // When
        Optional<String> result = validator.isValidGroupDto(validGroup);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void isValidGroupDto_returnsFieldName_forInvalidGroupId() {
        // Given
        GroupDto invalidGroup = GroupDto.builder()
                .groupId("")
                .groupName("Computer Science")
                .build();

        // When
        Optional<String> result = validator.isValidGroupDto(invalidGroup);

        // Then
        assertTrue(result.isPresent());
        assertEquals("groupId", result.get());
    }

    @Test
    void isValidGroupDto_returnsFieldName_forInvalidGroupName() {
        // Given
        GroupDto invalidGroup = GroupDto.builder()
                .groupId("GR-001")
                .groupName("A".repeat(101)) // Over max length
                .build();

        // When
        Optional<String> result = validator.isValidGroupDto(invalidGroup);

        // Then
        assertTrue(result.isPresent());
        assertEquals("groupName", result.get());
    }

    @Test
    void isGroupExists_returnsTrue_whenGroupExists() {
        // Given
        Long groupId = 1L;
        when(groupRepository.existsById(groupId)).thenReturn(true);

        // When
        boolean result = validator.isGroupExists(groupId, groupRepository);

        // Then
        assertTrue(result);
        verify(groupRepository, times(1)).existsById(groupId);
    }

    @Test
    void isGroupExists_returnsFalse_whenGroupDoesNotExist() {
        // Given
        Long groupId = 1L;
        when(groupRepository.existsById(groupId)).thenReturn(false);

        // When
        boolean result = validator.isGroupExists(groupId, groupRepository);

        // Then
        assertFalse(result);
        verify(groupRepository, times(1)).existsById(groupId);
    }
}