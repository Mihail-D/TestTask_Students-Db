package ru.trial.assignments.testtask_studentsdb.student.service.metrics;

import org.springframework.stereotype.Service;

@Service
public class MetricService {
    private final CounterService counterService;
    private final TimerService timerService;

    public MetricService(CounterService counterService, TimerService timerService) {
        this.counterService = counterService;
        this.timerService = timerService;
    }

    public void doCount() {
        counterService.incrementCounter();

        timerService.recordExecutionTime(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
