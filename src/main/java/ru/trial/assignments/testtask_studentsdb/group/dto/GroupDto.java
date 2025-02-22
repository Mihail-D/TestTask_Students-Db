package ru.trial.assignments.testtask_studentsdb.group.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "groups")
public class GroupDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Group ID cannot be null.")
    @Column(name = "group_id", nullable = false)
    private String groupId;

    @NotNull(message = "Group name cannot be null.")
    @NotBlank(message = "Group name cannot be empty or contain spaces.")
    @Size(min = 1, max = 100, message = "Group name must be between 1 and 100 characters.")
    @Column(name = "group_name", nullable = false)
    private String groupName;
}
