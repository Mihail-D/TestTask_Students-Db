package ru.trial.assignments.testtask_studentsdb.student.controller.metrics;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.trial.assignments.testtask_studentsdb.student.service.metrics.MetricService;

@RestController
@RequestMapping("/metrics")
public class MetricsController {
    private final MetricService metricService;
    public MetricsController(MetricService metricService) {
        this.metricService = metricService;
    }
    @GetMapping("/counter")
    public String doSomething() {
        metricService.doCount();
        return "Метод doCount выполнен!";
    }

}
