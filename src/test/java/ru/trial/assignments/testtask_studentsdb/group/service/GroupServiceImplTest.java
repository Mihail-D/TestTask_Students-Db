package ru.trial.assignments.testtask_studentsdb.group.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.trial.assignments.testtask_studentsdb.group.dto.GroupDto;
import ru.trial.assignments.testtask_studentsdb.group.mapper.GroupMapper;
import ru.trial.assignments.testtask_studentsdb.group.model.Group;
import ru.trial.assignments.testtask_studentsdb.group.repository.GroupRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupServiceImplTest {

    @Mock
    private GroupRepository groupRepository;

    @Mock
    private GroupMapper groupMapper;

    @InjectMocks
    private GroupServiceImpl groupService;

    @Test
    void addGroup_savesGroup() {
        // Given
        GroupDto groupDto = GroupDto.builder()
                .id(1L)
                .groupNumber("GR-001")
                .specialization("Computer Science")
                .build();
        
        Group group = Group.builder()
                .id(1L)
                .groupNumber("GR-001")
                .specialization("Computer Science")
                .build();

        when(groupMapper.toGroup(groupDto)).thenReturn(group);

        // When
        groupService.addGroup(groupDto);

        // Then
        verify(groupMapper, times(1)).toGroup(groupDto);
        verify(groupRepository, times(1)).save(group);
    }

    @Test
    void updateGroup_updatesExistingGroup() {
        // Given
        GroupDto groupDto = GroupDto.builder()
                .id(1L)
                .groupNumber("GR-001")
                .specialization("Updated Specialization")
                .build();
        
        Group existingGroup = Group.builder()
                .id(1L)
                .groupNumber("GR-001")
                .specialization("Computer Science")
                .build();
        
        Group updatedGroup = Group.builder()
                .id(1L)
                .groupNumber("GR-001")
                .specialization("Updated Specialization")
                .build();

        when(groupRepository.findById(groupDto.getId())).thenReturn(Optional.of(existingGroup));
        when(groupMapper.toGroup(groupDto)).thenReturn(updatedGroup);

        // When
        groupService.updateGroup(groupDto);

        // Then
        verify(groupRepository, times(1)).findById(groupDto.getId());
        verify(groupMapper, times(1)).toGroup(groupDto);
        verify(groupRepository, times(1)).save(updatedGroup);
    }

    @Test
    void updateGroup_throwsException_whenGroupNotFound() {
        // Given
        GroupDto groupDto = GroupDto.builder()
                .id(999L)
                .groupNumber("GR-999")
                .specialization("Non-existent")
                .build();

        when(groupRepository.findById(groupDto.getId())).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> groupService.updateGroup(groupDto));
        verify(groupRepository, times(1)).findById(groupDto.getId());
        verify(groupRepository, never()).save(any());
    }

    @Test
    void deleteGroup_deletesExistingGroup() {
        // Given
        GroupDto groupDto = GroupDto.builder()
                .id(1L)
                .groupNumber("GR-001")
                .specialization("Computer Science")
                .build();
        
        Group group = Group.builder()
                .id(1L)
                .groupNumber("GR-001")
                .specialization("Computer Science")
                .build();

        when(groupRepository.findById(groupDto.getId())).thenReturn(Optional.of(group));

        // When
        groupService.deleteGroup(groupDto);

        // Then
        verify(groupRepository, times(1)).findById(groupDto.getId());
        verify(groupRepository, times(1)).delete(group);
    }

    @Test
    void deleteGroup_throwsException_whenGroupNotFound() {
        // Given
        GroupDto groupDto = GroupDto.builder()
                .id(999L)
                .groupNumber("GR-999")
                .specialization("Non-existent")
                .build();

        when(groupRepository.findById(groupDto.getId())).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> groupService.deleteGroup(groupDto));
        verify(groupRepository, times(1)).findById(groupDto.getId());
        verify(groupRepository, never()).delete(any());
    }

    @Test
    void getGroups_returnsAllGroups() {
        // Given
        Group group1 = Group.builder()
                .id(1L)
                .groupNumber("GR-001")
                .specialization("Computer Science")
                .build();
        
        Group group2 = Group.builder()
                .id(2L)
                .groupNumber("GR-002")
                .specialization("Mathematics")
                .build();

        GroupDto groupDto1 = GroupDto.builder()
                .id(1L)
                .groupNumber("GR-001")
                .specialization("Computer Science")
                .build();
        
        GroupDto groupDto2 = GroupDto.builder()
                .id(2L)
                .groupNumber("GR-002")
                .specialization("Mathematics")
                .build();

        List<Group> groups = List.of(group1, group2);
        List<GroupDto> expectedGroupDtos = List.of(groupDto1, groupDto2);

        when(groupRepository.findAll()).thenReturn(groups);
        when(groupMapper.toGroupDto(group1)).thenReturn(groupDto1);
        when(groupMapper.toGroupDto(group2)).thenReturn(groupDto2);

        // When
        List<GroupDto> result = groupService.getGroups();

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedGroupDtos, result);
        verify(groupRepository, times(1)).findAll();
        verify(groupMapper, times(1)).toGroupDto(group1);
        verify(groupMapper, times(1)).toGroupDto(group2);
    }

    @Test
    void getGroups_returnsEmptyList_whenNoGroups() {
        // Given
        when(groupRepository.findAll()).thenReturn(List.of());

        // When
        List<GroupDto> result = groupService.getGroups();

        // Then
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(groupRepository, times(1)).findAll();
        verify(groupMapper, never()).toGroupDto(any());
    }
}