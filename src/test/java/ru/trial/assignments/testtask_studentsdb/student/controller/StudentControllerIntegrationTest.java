package ru.trial.assignments.testtask_studentsdb.student.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.trial.assignments.testtask_studentsdb.student.dto.StudentDto;
import ru.trial.assignments.testtask_studentsdb.student.service.StudentService;
import ru.trial.assignments.testtask_studentsdb.student.service.metrics.MetricService;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @MockBean
    private MetricService metricService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addStudent_returnsCreated() throws Exception {
        // Given
        StudentDto studentDto = StudentDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .patronymic("Smith")
                .dateOfBirth(LocalDate.of(2000, 1, 1))
                .groupId("GR-001")
                .build();

        doNothing().when(studentService).addStudent(any(StudentDto.class));

        // When & Then
        mockMvc.perform(post("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDto)))
                .andExpect(status().isCreated());

        verify(studentService, times(1)).addStudent(any(StudentDto.class));
    }

    @Test
    void updateStudent_returnsOk() throws Exception {
        // Given
        StudentDto studentDto = StudentDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("UpdatedDoe")
                .patronymic("Smith")
                .dateOfBirth(LocalDate.of(2000, 1, 1))
                .groupId("GR-001")
                .build();

        doNothing().when(studentService).updateStudent(any(StudentDto.class));

        // When & Then
        mockMvc.perform(put("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDto)))
                .andExpect(status().isOk());

        verify(studentService, times(1)).updateStudent(any(StudentDto.class));
    }

    @Test
    void deleteStudent_returnsNoContent() throws Exception {
        // Given
        StudentDto studentDto = StudentDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .build();

        doNothing().when(studentService).deleteStudent(any(StudentDto.class));

        // When & Then
        mockMvc.perform(delete("/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDto)))
                .andExpect(status().isNoContent());

        verify(studentService, times(1)).deleteStudent(any(StudentDto.class));
    }

    @Test
    void findAllStudentsByLastName_returnsStudentsList() throws Exception {
        // Given
        String lastName = "Doe";
        StudentDto student1 = StudentDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .build();

        StudentDto student2 = StudentDto.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Doe")
                .build();

        List<StudentDto> students = List.of(student1, student2);
        when(studentService.findAllStudentsByLastName(lastName)).thenReturn(students);

        // When & Then
        mockMvc.perform(get("/students/{lastName}/students", lastName))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[0].lastName").value("Doe"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].firstName").value("Jane"))
                .andExpect(jsonPath("$[1].lastName").value("Doe"));

        verify(studentService, times(1)).findAllStudentsByLastName(lastName);
    }

    @Test
    void getStudentsByGroupNumber_returnsStudentsList() throws Exception {
        // Given
        String groupId = "GR-001";
        StudentDto student1 = StudentDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .groupId("GR-001")
                .build();

        StudentDto student2 = StudentDto.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Smith")
                .groupId("GR-001")
                .build();

        List<StudentDto> students = List.of(student1, student2);
        when(studentService.getStudentsByGroupNumber(groupId)).thenReturn(students);

        // When & Then
        mockMvc.perform(get("/students/{groupId}/students", groupId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].groupId").value("GR-001"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].groupId").value("GR-001"));

        verify(studentService, times(1)).getStudentsByGroupNumber(groupId);
    }

    @Test
    void getStudents_returnsStudentsList() throws Exception {
        // Given
        StudentDto student1 = StudentDto.builder()
                .id(1L)
                .firstName("John")
                .lastName("Doe")
                .build();

        StudentDto student2 = StudentDto.builder()
                .id(2L)
                .firstName("Jane")
                .lastName("Smith")
                .build();

        List<StudentDto> students = List.of(student1, student2);
        when(studentService.getStudents()).thenReturn(students);
        doNothing().when(metricService).doCount();

        // When & Then
        mockMvc.perform(get("/students/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].firstName").value("John"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].firstName").value("Jane"));

        verify(studentService, times(1)).getStudents();
        verify(metricService, times(1)).doCount();
    }

    @Test
    void getStudents_returnsEmptyList_whenNoStudents() throws Exception {
        // Given
        when(studentService.getStudents()).thenReturn(List.of());
        doNothing().when(metricService).doCount();

        // When & Then
        mockMvc.perform(get("/students/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(0));

        verify(studentService, times(1)).getStudents();
        verify(metricService, times(1)).doCount();
    }
}