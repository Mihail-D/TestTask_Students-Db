package ru.trial.assignments.testtask_studentsdb.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.trial.assignments.testtask_studentsdb.student.model.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByFirstNameAndLastNameAndMiddleNameAndBirthDateAndGroupId(
            String firstName, String lastName, String middleName, LocalDate birthDate, Long groupId);

    List<Student> findStudentByLastName(String lastName);
}
