package org.example.stockalarms.concurrency;

import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


@Service
public class ConcurrencyManager {
    private final ExecutorService executorService = Executors.newFixedThreadPool(20);
    public void submitTask(Runnable task) {
        executorService.submit(task);
    }

    @PreDestroy  // used to call this method before shutting down the app
    public void shutdownExecutorService() {
        executorService.shutdown();
        try {
            executorService.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // in order to set the interrupt flag (so that we know the interruption occurred)
            System.out.println("Failed to check the alarm");
        }
    }
}
