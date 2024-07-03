package org.basilomp.oop.hw3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Queue;

@Slf4j
@AllArgsConstructor
@Getter
public class MyTask extends Thread {
    private final String taskName;
    private final MyThreadPool threadPool;


    @Override
    public void run() {
        long id = Thread.currentThread().getId();
        log.info("Thread id {} is executing Task {}", id, this.taskName);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Thread {} has been interrupted", id);
        }
    }
}