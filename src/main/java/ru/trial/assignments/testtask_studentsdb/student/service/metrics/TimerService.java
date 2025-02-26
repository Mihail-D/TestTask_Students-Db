package ru.trial.assignments.testtask_studentsdb.student.service.metrics;

import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class TimerService {

    private final Timer timer;

    public TimerService(MeterRegistry registry) {
        this.timer = Timer.builder("my.service.method.execution.time")
                .description("Время выполнения метода")
                .tags("region", "us-east")
                .register(registry);
    }

    public void recordExecutionTime(Runnable runnable) {
        timer.record(runnable);
    }

}
