package ru.trial.assignments.testtask_studentsdb.group.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.trial.assignments.testtask_studentsdb.group.dto.GroupDto;
import ru.trial.assignments.testtask_studentsdb.group.service.GroupService;
import ru.trial.assignments.testtask_studentsdb.student.dto.StudentDto;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/group")
public class GroupController {

    GroupService groupService;

    @Autowired

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping()
    public ResponseEntity<Void> addGroup(@RequestBody @Valid GroupDto groupDto) {
        groupService.addGroup(groupDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping()
    ResponseEntity<Void> updateGroup(@RequestBody @Valid GroupDto groupDto) {
        groupService.updateGroup(groupDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
