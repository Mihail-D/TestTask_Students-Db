package ru.trial.assignments.testtask_studentsdb.group.repository;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.trial.assignments.testtask_studentsdb.group.model.Group;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    Optional<Group> findGroupByGroupId(@NotNull(message = "Group ID cannot be null.") String groupId);
}
