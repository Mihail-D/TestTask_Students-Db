package ru.trial.assignments.testtask_studentsdb.group.mapper;

import org.springframework.stereotype.Component;
import ru.trial.assignments.testtask_studentsdb.group.dto.GroupDto;
import ru.trial.assignments.testtask_studentsdb.group.model.Group;

@Component
public class GroupMapper {

    public Group toEntity(GroupDto groupDto) {
        if (groupDto == null) {
            return null;
        }

        return new Group(
                groupDto.getId(),
                groupDto.getGroupId(),
                groupDto.getGroupName()
        );
    }

    public GroupDto toDto(Group group) {
        if (group == null) {
            return null;
        }

        return new GroupDto(
                group.getId(),
                group.getGroupId(),
                group.getGroupName()
        );
    }
}
