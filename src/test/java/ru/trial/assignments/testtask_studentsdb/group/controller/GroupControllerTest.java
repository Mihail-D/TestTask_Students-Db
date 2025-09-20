package ru.trial.assignments.testtask_studentsdb.group.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.trial.assignments.testtask_studentsdb.group.dto.GroupDto;
import ru.trial.assignments.testtask_studentsdb.group.service.GroupService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GroupControllerTest {

    @Mock
    private GroupService groupService;

    @InjectMocks
    private GroupController groupController;

    @Test
    void addGroup_createsGroup() {
        GroupDto groupDto = new GroupDto();
        doNothing().when(groupService).addGroup(any(GroupDto.class));

        ResponseEntity<Void> response = groupController.addGroup(groupDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        verify(groupService, times(1)).addGroup(groupDto);
    }

    @Test
    void updateGroup_updatesGroup() {
        GroupDto groupDto = new GroupDto();
        doNothing().when(groupService).updateGroup(any(GroupDto.class));

        ResponseEntity<Void> response = groupController.updateGroup(groupDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(groupService, times(1)).updateGroup(groupDto);
    }

    @Test
    void deleteGroup_deletesGroup() {
        GroupDto groupDto = new GroupDto();
        doNothing().when(groupService).deleteGroup(any(GroupDto.class));

        ResponseEntity<Void> response = groupController.deleteGroup(groupDto);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(groupService, times(1)).deleteGroup(groupDto);
    }

    @Test
    void getStudents_returnsAllGroups() {
        List<GroupDto> groups = Collections.singletonList(new GroupDto());
        when(groupService.getGroups()).thenReturn(groups);

        List<GroupDto> response = groupController.getStudents();

        assertEquals(groups, response);
        verify(groupService, times(1)).getGroups();
    }
}
