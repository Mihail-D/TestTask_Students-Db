package ru.trial.assignments.testtask_studentsdb.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.trial.assignments.testtask_studentsdb.student.dto.StudentDto;
import ru.trial.assignments.testtask_studentsdb.student.model.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByFirstNameAndLastNameAndMiddleNameAndBirthDateAndGroupId(
            String firstName, String lastName, String middleName, LocalDate birthDate, Long groupId);

    @Query("SELECT new ru.trial.assignments.testtask_studentsdb.student.dto.StudentDto(s.id, s.firstName, s.lastName, s.middleName, s.birthDate, s.groupId) " +
            "FROM Student s WHERE s.lastName = :lastName")
    List<StudentDto> findAllStudentsByLastName(@Param("lastName") String lastName);
}
