package ru.trial.assignments.testtask_studentsdb.student.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class StudentDto {

    private Long id;

    @NotNull(message = "First name cannot be null.")
    @NotBlank(message = "First name cannot be empty or contain spaces.")
    @Size(min = 1, max = 50, message = "First name must be between 1 and 50 characters.")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull(message = "First name cannot be null.")
    @NotBlank(message = "First name cannot be empty or contain spaces.")
    @Size(min = 1, max = 50, message = "Last name must be between 1 and 50 characters.")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Size(min = 1, max = 50, message = "Middle name must be between 1 and 50 characters.")
    @Column(name = "middle_name")
    private String middleName;

    @NotNull(message = "Birth Date cannot be null.")
    @Past(message = "Birth Date must be in the past.")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotNull(message = "Group ID cannot be null.")
    @Positive(message = "Group ID must be positive.")
    @Column(name = "group_id")
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
