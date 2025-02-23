package ru.trial.assignments.testtask_studentsdb.group.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupDto {

    private Long id;

    @NotNull(message = "Group ID cannot be null.")
    private String groupId;

    @NotNull(message = "Group name cannot be null.")
    @NotBlank(message = "Group name cannot be empty or contain spaces.")
    @Size(min = 1, max = 100, message = "Group name must be between 1 and 100 characters.")
    private String groupName;
}
