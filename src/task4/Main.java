package task4;

import java.util.Random;
import java.util.concurrent.*;

public class Main {
    public static final Random rnd = new Random();
    public static void main(String[] args) throws InterruptedException {
        int taskNum = 5000;
        int threadNum1 = 1;
        int threadNum2 = 4;

        long expTime1 = ExperimentLab5(threadNum1, taskNum);
        long expTime2 = ExperimentLab5(threadNum2, taskNum);

        double Sp = (double) expTime1 / expTime2;
        double Ep = Sp / threadNum2;
        double Cp = Sp * threadNum2;

        System.out.printf("SpeedUp(Sp):%f\n", Sp);
        System.out.printf("Efficiency(Ep):%f\n", Ep);
        System.out.printf("Cost(Cp):%f\n", Cp);
    }
    public static long ExperimentLab5 (int threadNum, int taskNum) throws InterruptedException {
        // thread pool
        int queueCapacity = taskNum; // 100

        int corePoolSize = threadNum; 
        int maximumPoolSize = threadNum; 
        long keepAliveTime = 0L;

        // task processing
        double numTask = taskNum;
        // simulation of queue movement delay
        int QdelayOrigin = 1;
        int QdelayBound = 5; // 10
        // simulating the delay in task processing
        int TdelayOrigin = 1;
        int TdelayBound = 20; // 150

        // collectable data (print)
        double queueSize = 0;
        double exceptedTasks = 0;

        // "обмежена черга"
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>(queueCapacity);
        // "пул потоків"
        try{
            ExecutorService executorService = new ThreadPoolExecutor(
                    corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MILLISECONDS, workQueue
            );
            long nanotime1 = System.nanoTime();
            for (int i = 0; i < numTask; i++) {
                // simulation of queue movement delay
                Thread.sleep(rnd.nextInt((QdelayBound - QdelayOrigin) + 1) + QdelayOrigin);
                try {
                    // "окремі підзадачі"
                    DelayedTask Task = new DelayedTask(rnd.nextInt((TdelayBound - TdelayOrigin) + 1) + TdelayOrigin);
                    executorService.submit(Task);

                    queueSize += workQueue.size();
                } catch (Exception e) {
                    queueSize += workQueue.size();
                    exceptedTasks++;
                }
            }
            executorService.shutdown();
            long nanotime2 = System.nanoTime();

            long runtime = (nanotime2 - nanotime1)/1000000;
            double failure = exceptedTasks / numTask * 100;
            double avgQueueSize = queueSize / numTask;

            System.out.printf("Tasks:%.0f | Excepted:%.0f | Failure:%.2f%% | Avg q size:%.2f\n",
                    numTask, exceptedTasks, failure, avgQueueSize);
            System.out.printf("Runtime:%dms | Pool size:%d | Queue cap:%d | Delay:%d\n\n",
                    runtime, corePoolSize, queueCapacity, QdelayBound);
            return runtime;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}