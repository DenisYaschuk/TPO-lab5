package task2;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int experimentNum = 10;

        ArrayList<Result> resArray = new ArrayList<Result>();
        ArrayList<Thread> threadList = new ArrayList<Thread>();

        for (int i = 0; i < experimentNum; i++) {
            resArray.add(new Result());
            Thread t = new Thread(new Model(i + 1, resArray.get(i)));
            threadList.add(t);
            threadList.get(i).start();
        }

        for (Thread thread:
                threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        double avgExceptedTasks = 0;
        double avgFailure = 0;
        double avgQueSize = 0;
        for (Result result:
             resArray) {
            result.print();
            avgExceptedTasks = result.getExceptedTasks();
            avgFailure += result.getFail();
            avgQueSize += result.getAvgQueueSize();
        }
        System.out.printf("Statistic excepted tasks: %.2f\n", avgExceptedTasks/experimentNum);
        System.out.printf("Statistic failure: %.2f%%\n", avgFailure/experimentNum);
        System.out.printf("Statistic average queue size: %.2f\n", avgQueSize/experimentNum);
    }
}

