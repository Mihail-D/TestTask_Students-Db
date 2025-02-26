package ru.trial.assignments.testtask_studentsdb.student.service.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Service;

@Service
public class CounterService {

    private final Counter methodCallCounter;

    public CounterService(MeterRegistry registry) {
        this.methodCallCounter = Counter.builder("counter.service.method.calls")
                .description("Количество вызовов метода")
                .tags("region", "ru-msk")
                .register(registry);
    }

    public void incrementCounter() {
        methodCallCounter.increment();
    }
}
