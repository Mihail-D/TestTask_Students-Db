package ru.trial.assignments.testtask_studentsdb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class TestTaskStudentsDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestTaskStudentsDbApplication.class, args);
        System.out.println("======== THE PROGRAM IS RUNNING ========");
    }

}
