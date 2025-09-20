package ru.trial.assignments.testtask_studentsdb.student.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.trial.assignments.testtask_studentsdb.student.dto.StudentDto;
import ru.trial.assignments.testtask_studentsdb.student.mapper.StudentMapper;
import ru.trial.assignments.testtask_studentsdb.student.model.Student;
import ru.trial.assignments.testtask_studentsdb.student.repository.StudentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentServiceImpl studentService;

    @Test
    void addStudent_savesStudent() {
        // Given
        StudentDto studentDto = StudentDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .patronymic("Smith")
                .dateOfBirth(LocalDate.of(2000, 1, 1))
                .groupId("GR-001")
                .build();
        
        Student student = Student.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .patronymic("Smith")
                .dateOfBirth(LocalDate.of(2000, 1, 1))
                .groupId("GR-001")
                .build();

        when(studentMapper.toStudent(studentDto)).thenReturn(student);

        // When
        studentService.addStudent(studentDto);

        // Then
        verify(studentMapper, times(1)).toStudent(studentDto);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void updateStudent_updatesExistingStudent() {
        // Given
        StudentDto studentDto = StudentDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("UpdatedDoe")
                .patronymic("Smith")
                .dateOfBirth(LocalDate.of(2000, 1, 1))
                .groupId("GR-001")
                .build();
        
        Student existingStudent = Student.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .patronymic("Smith")
                .dateOfBirth(LocalDate.of(2000, 1, 1))
                .groupId("GR-001")
                .build();
        
        Student updatedStudent = Student.builder()
                .id(1L)
                .firstName("John")
                .lastName("UpdatedDoe")
                .patronymic("Smith")
                .dateOfBirth(LocalDate.of(2000, 1, 1))
                .groupId("GR-001")
                .build();

        when(studentRepository.findById(studentDto.getId())).thenReturn(Optional.of(existingStudent));
        when(studentMapper.toStudent(studentDto)).thenReturn(updatedStudent);

        // When
        studentService.updateStudent(studentDto);

        // Then
        verify(studentRepository, times(1)).findById(studentDto.getId());
        verify(studentMapper, times(1)).toStudent(studentDto);
        verify(studentRepository, times(1)).save(updatedStudent);
    }

    @Test
    void updateStudent_throwsException_whenStudentNotFound() {
        // Given
        StudentDto studentDto = StudentDto.builder()
                .id(999L)
                .firstName("John")
                .lastName("Doe")
                .build();

        when(studentRepository.findById(studentDto.getId())).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> studentService.updateStudent(studentDto));
        verify(studentRepository, times(1)).findById(studentDto.getId());
        verify(studentRepository, never()).save(any());
    }

    @Test
    void deleteStudent_deletesExistingStudent() {
        // Given
        StudentDto studentDto = StudentDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .build();
        
        Student student = Student.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .build();

        when(studentRepository.findById(studentDto.getId())).thenReturn(Optional.of(student));

        // When
        studentService.deleteStudent(studentDto);

        // Then
        verify(studentRepository, times(1)).findById(studentDto.getId());
        verify(studentRepository, times(1)).delete(student);
    }

    @Test
    void deleteStudent_throwsException_whenStudentNotFound() {
        // Given
        StudentDto studentDto = StudentDto.builder()
                .id(999L)
                .firstName("John")
                .lastName("Doe")
                .build();

        when(studentRepository.findById(studentDto.getId())).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> studentService.deleteStudent(studentDto));
        verify(studentRepository, times(1)).findById(studentDto.getId());
        verify(studentRepository, never()).delete(any());
    }

    @Test
    void findAllStudentsByLastName_returnsStudentsWithMatchingLastName() {
        // Given
        String lastName = "Doe";
        Student student1 = Student.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .build();
        
        Student student2 = Student.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Doe")
                .build();

        StudentDto studentDto1 = StudentDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .build();
        
        StudentDto studentDto2 = StudentDto.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Doe")
                .build();

        List<Student> students = List.of(student1, student2);

        when(studentRepository.findAllByLastName(lastName)).thenReturn(students);
        when(studentMapper.toStudentDto(student1)).thenReturn(studentDto1);
        when(studentMapper.toStudentDto(student2)).thenReturn(studentDto2);

        // When
        List<StudentDto> result = studentService.findAllStudentsByLastName(lastName);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Doe", result.get(0).getLastName());
        assertEquals("Doe", result.get(1).getLastName());
        verify(studentRepository, times(1)).findAllByLastName(lastName);
    }

    @Test
    void getStudentsByGroupNumber_returnsStudentsInGroup() {
        // Given
        String groupId = "GR-001";
        Student student1 = Student.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .groupId("GR-001")
                .build();
        
        Student student2 = Student.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Smith")
                .groupId("GR-001")
                .build();

        StudentDto studentDto1 = StudentDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .groupId("GR-001")
                .build();
        
        StudentDto studentDto2 = StudentDto.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Smith")
                .groupId("GR-001")
                .build();

        List<Student> students = List.of(student1, student2);

        when(studentRepository.findAllByGroupId(groupId)).thenReturn(students);
        when(studentMapper.toStudentDto(student1)).thenReturn(studentDto1);
        when(studentMapper.toStudentDto(student2)).thenReturn(studentDto2);

        // When
        List<StudentDto> result = studentService.getStudentsByGroupNumber(groupId);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("GR-001", result.get(0).getGroupId());
        assertEquals("GR-001", result.get(1).getGroupId());
        verify(studentRepository, times(1)).findAllByGroupId(groupId);
    }

    @Test
    void getStudents_returnsAllStudents() {
        // Given
        Student student1 = Student.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .build();
        
        Student student2 = Student.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Smith")
                .build();

        StudentDto studentDto1 = StudentDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .build();
        
        StudentDto studentDto2 = StudentDto.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Smith")
                .build();

        List<Student> students = List.of(student1, student2);

        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toStudentDto(student1)).thenReturn(studentDto1);
        when(studentMapper.toStudentDto(student2)).thenReturn(studentDto2);

        // When
        List<StudentDto> result = studentService.getStudents();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(studentRepository, times(1)).findAll();
        verify(studentMapper, times(1)).toStudentDto(student1);
        verify(studentMapper, times(1)).toStudentDto(student2);
    }

    @Test
    void getStudents_returnsEmptyList_whenNoStudents() {
        // Given
        when(studentRepository.findAll()).thenReturn(List.of());

        // When
        List<StudentDto> result = studentService.getStudents();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(studentRepository, times(1)).findAll();
        verify(studentMapper, never()).toStudentDto(any());
    }
}