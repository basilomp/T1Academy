package org.basilomp.oop.hw3;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;

@Slf4j
@Getter
public class MyThreadPool {
    private final int capacity;
    private final Queue<Runnable> taskQueue;
    private final MyTask[] tasks;
    private boolean isShutdown = false;

    public MyThreadPool(int capacity) {
        this.capacity = capacity;
        this.taskQueue = new LinkedList<>();
        this.tasks = new MyTask[capacity];
        for (int i = 0; i < capacity; i++) {
            tasks[i] = new MyTask(String.valueOf(i), this);
            tasks[i].start();
        }
        log.info("MyThreadPool created with capacity: {}", capacity);
    }

    public synchronized void execute(Runnable task) {
        if (isShutdown) {
            throw new IllegalStateException("ThreadPool is already shut down");
        }
        taskQueue.offer(task);
        notifyAll();
        log.info("Task added to the queue");
    }

    public synchronized void shutdown() {
        isShutdown = true;
        notifyAll();
        log.info("Shutting down thread pool");
        for (MyTask task : tasks) {
            task.interrupt();
        }
    }

    public synchronized void awaitTermination() {
        log.info("Waiting for thread pool to terminate");
        while (!taskQueue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            for (MyTask task : tasks) {
                try {
                    task.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        log.info("All threads are terminated");
    }
}
