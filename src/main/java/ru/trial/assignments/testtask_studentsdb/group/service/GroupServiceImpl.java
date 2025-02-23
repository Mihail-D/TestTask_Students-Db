package ru.trial.assignments.testtask_studentsdb.group.service;

import org.springframework.stereotype.Service;
import ru.trial.assignments.testtask_studentsdb.group.dto.GroupDto;
import ru.trial.assignments.testtask_studentsdb.group.mapper.GroupMapper;
import ru.trial.assignments.testtask_studentsdb.group.model.Group;
import ru.trial.assignments.testtask_studentsdb.group.repository.GroupRepository;
import ru.trial.assignments.testtask_studentsdb.student.exceptions.ClassFieldErrorException;
import ru.trial.assignments.testtask_studentsdb.student.exceptions.NotFoundException;
import ru.trial.assignments.testtask_studentsdb.student.utility.Validator;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;
    private final Validator validator;

    public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper, Validator validator) {
        this.groupRepository = groupRepository;
        this.groupMapper = groupMapper;
        this.validator = validator;
    }

    @Override
    public void addGroup(GroupDto groupDto) {
        validator.isValidGroupDto(groupDto).ifPresent(field -> {
            throw new ClassFieldErrorException(GroupDto.class, "Invalid field: " + field);
        });

        Optional<Group> existingGroup = groupRepository.findGroupByGroupId(groupDto.getGroupId());

        if (existingGroup.isPresent()) {
            throw new IllegalArgumentException("Group with the same ID already exists");
        }

        Group group = groupMapper.toEntity(groupDto);
        Group savedGroup = groupRepository.save(group);
        groupMapper.toDto(savedGroup);
    }

    @Override
    public void updateGroup(GroupDto groupDto) {
        if (!validator.isGroupExists(groupDto.getId(), groupRepository)) {
            throw new NotFoundException(Group.class, " not found");
        }
        Group group = groupMapper.toEntity(groupDto);
        groupRepository.save(group);
    }

    @Override
    public void deleteGroup(GroupDto groupDto) {
        if (groupDto.getId() == null) {
            throw new IllegalArgumentException("Group ID must not be null");
        }
        if (!validator.isGroupExists(groupDto.getId(), groupRepository)) {
            throw new NotFoundException(Group.class, " not found");
        }
        Group group = groupMapper.toEntity(groupDto);
        groupRepository.delete(group);
    }

    @Override
    public List<GroupDto> getGroups() {
        return groupRepository.findAll().stream()
                .map(groupMapper::toDto)
                .collect(Collectors.toList());
    }

}
