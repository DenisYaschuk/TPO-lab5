package task3;

import java.util.concurrent.*;

public class Logger implements Runnable
{
    private final BlockingQueue<Runnable> workQueue;
    private final ExecutorService executorService;
    private final int i;
    private final double exceptedTasks;
    public Logger(BlockingQueue<Runnable> workQueue,
        ExecutorService executorService, int i, double exceptedTasks) {
        this.workQueue = workQueue;
        this.executorService = executorService;
        this.i = i;
        this.exceptedTasks = exceptedTasks;
    }

    @Override
    public void run() {
        String status = executorService.isTerminated()? "terminated" : "running";
        System.out.printf(
            "[Iteration:%d\tStatus:%s\tQueue size:%d\tRemaining capacity:%d\tExcepted tasks:%.0f]\n",
            i, status, workQueue.size(), workQueue.remainingCapacity(), exceptedTasks
        );
    }
}