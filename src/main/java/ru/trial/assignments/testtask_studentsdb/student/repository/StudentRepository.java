package ru.trial.assignments.testtask_studentsdb.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.trial.assignments.testtask_studentsdb.student.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentByLastName(String lastName);
}
