package ru.trial.assignments.testtask_studentsdb.student.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@Entity
@Table(name="students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "First name cannot be null.")
    @NotBlank(message = "First name cannot be empty or contain spaces.")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotNull(message = "First name cannot be null.")
    @NotBlank(message = "First name cannot be empty or contain spaces.")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @NotNull(message = "Birth Date cannot be null.")
    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @NotNull(message = "Group ID cannot be null.")
    @Column(name = "group_id")
    private Long groupId;

    public Student(Long id, String firstName, String lastName, String middleName, LocalDate birthDate, Long groupId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.birthDate = birthDate;
        this.groupId = groupId;
    }
}
