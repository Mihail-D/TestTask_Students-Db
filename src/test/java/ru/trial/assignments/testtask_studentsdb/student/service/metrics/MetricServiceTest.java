package ru.trial.assignments.testtask_studentsdb.student.service.metrics;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MetricServiceTest {

    @Mock
    private CounterService counterService;

    @Mock
    private TimerService timerService;

    @InjectMocks
    private MetricService metricService;

    @Test
    void doCount_callsCounterServiceAndTimerService() {
        // Given
        doNothing().when(counterService).incrementCounter();
        doNothing().when(timerService).recordExecutionTime(any(Runnable.class));

        // When
        metricService.doCount();

        // Then
        verify(counterService, times(1)).incrementCounter();
        verify(timerService, times(1)).recordExecutionTime(any(Runnable.class));
    }

    @Test
    void doCount_handlesInterruptedException() {
        // Given
        doNothing().when(counterService).incrementCounter();
        doAnswer(invocation -> {
            Runnable runnable = invocation.getArgument(0);
            runnable.run(); // Выполняем переданный Runnable
            return null;
        }).when(timerService).recordExecutionTime(any(Runnable.class));

        // When
        metricService.doCount();

        // Then
        verify(counterService, times(1)).incrementCounter();
        verify(timerService, times(1)).recordExecutionTime(any(Runnable.class));
    }
}