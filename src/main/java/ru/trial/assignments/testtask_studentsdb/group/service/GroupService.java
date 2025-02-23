package ru.trial.assignments.testtask_studentsdb.group.service;

import ru.trial.assignments.testtask_studentsdb.group.dto.GroupDto;

import java.util.List;

public interface GroupService {
    void addGroup(GroupDto groupDto);
    void updateGroup(GroupDto groupDto);
    //void deleteGroup(GroupDto groupDto);
    //List<GroupDto> getGroups();

}
