package org.basilomp.oop.hw3;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.lang.Thread.currentThread;

@Slf4j
public class Runner {

    public static void main(String[] args) {
        MyThreadPool myThreadPool = new MyThreadPool(10);
        for (int i = 0; i < 10; i++) {
            final int position = i;
            myThreadPool.execute(() -> {
                long id = currentThread().getId();
                MyTask[] tasks = myThreadPool.getTasks();
                log.info("ThreadPool contains {} tasks", tasks.length);
                MyTask task = tasks[position];
                String name = task.getTaskName();
                log.info("Thread id {} is executing a task with name {} ", id, name);
            });
        }
        myThreadPool.shutdown();
        myThreadPool.awaitTermination();
    }
}
