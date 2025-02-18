package ru.trial.assignments.testtask_studentsdb.student.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class StudentDto {

    private Long id;

    @NotNull(message = "First name cannot be null.")
    @NotBlank(message = "First name cannot be empty or contain spaces.")
    private String firstName;

    @NotNull(message = "Last name cannot be null.")
    @NotBlank(message = "Last name cannot be empty or contain spaces.")
    private String lastName;

    private String middleName;

    @NotNull(message = "Birth Date cannot be null.")
    private LocalDate birthDate;

    @NotNull(message = "Group ID cannot be null.")
    private Long groupId;

    public StudentDto(Long id, String firstName, String lastName, String middleName, LocalDate birthDate, Long groupId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.groupId = groupId;
    }
}
