package ru.trial.assignments.testtask_studentsdb.group.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.trial.assignments.testtask_studentsdb.group.dto.GroupDto;
import ru.trial.assignments.testtask_studentsdb.group.service.GroupService;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GroupController.class)
class GroupControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GroupService groupService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addGroup_returnsCreated() throws Exception {
        // Given
        GroupDto groupDto = GroupDto.builder()
                .id(1L)
                .groupNumber("GR-001")
                .specialization("Computer Science")
                .build();

        doNothing().when(groupService).addGroup(any(GroupDto.class));

        // When & Then
        mockMvc.perform(post("/group")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupDto)))
                .andExpect(status().isCreated());

        verify(groupService, times(1)).addGroup(any(GroupDto.class));
    }

    @Test
    void updateGroup_returnsOk() throws Exception {
        // Given
        GroupDto groupDto = GroupDto.builder()
                .id(1L)
                .groupNumber("GR-001")
                .specialization("Updated Specialization")
                .build();

        doNothing().when(groupService).updateGroup(any(GroupDto.class));

        // When & Then
        mockMvc.perform(put("/group")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupDto)))
                .andExpect(status().isOk());

        verify(groupService, times(1)).updateGroup(any(GroupDto.class));
    }

    @Test
    void deleteGroup_returnsNoContent() throws Exception {
        // Given
        GroupDto groupDto = GroupDto.builder()
                .id(1L)
                .groupNumber("GR-001")
                .specialization("Computer Science")
                .build();

        doNothing().when(groupService).deleteGroup(any(GroupDto.class));

        // When & Then
        mockMvc.perform(delete("/group")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(groupDto)))
                .andExpect(status().isNoContent());

        verify(groupService, times(1)).deleteGroup(any(GroupDto.class));
    }

    @Test
    void getGroups_returnsGroupsList() throws Exception {
        // Given
        GroupDto group1 = GroupDto.builder()
                .id(1L)
                .groupNumber("GR-001")
                .specialization("Computer Science")
                .build();

        GroupDto group2 = GroupDto.builder()
                .id(2L)
                .groupNumber("GR-002")
                .specialization("Mathematics")
                .build();

        List<GroupDto> groups = List.of(group1, group2);
        when(groupService.getGroups()).thenReturn(groups);

        // When & Then
        mockMvc.perform(get("/group/groups"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].groupNumber").value("GR-001"))
                .andExpect(jsonPath("$[0].specialization").value("Computer Science"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].groupNumber").value("GR-002"))
                .andExpect(jsonPath("$[1].specialization").value("Mathematics"));

        verify(groupService, times(1)).getGroups();
    }

    @Test
    void getGroups_returnsEmptyList_whenNoGroups() throws Exception {
        // Given
        when(groupService.getGroups()).thenReturn(List.of());

        // When & Then
        mockMvc.perform(get("/group/groups"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(0));

        verify(groupService, times(1)).getGroups();
    }
}