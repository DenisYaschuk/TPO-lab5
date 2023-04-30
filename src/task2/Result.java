package task2;

public class Result {
    private int id;
    private double numTask;
    private double exceptedTasks;
    private double queueSize;
    private int corePoolSize;
    private int queueCapacity;
    private int QdelayBound;

    public void setResult(int id, double numTask, double exceptedTasks,
        double queueSize, int corePoolSize, int queueCapacity, int QdelayBound) {
        this.id = id;
        this.numTask = numTask;
        this.exceptedTasks = exceptedTasks;
        this.queueSize = queueSize;
        this.corePoolSize = corePoolSize;
        this.queueCapacity = queueCapacity;
        this.QdelayBound = QdelayBound;
    }
    public void print() {
        System.out.printf("Task-%d:\n", id);
        System.out.printf("Tasks number: %.0f\n", numTask);
        System.out.printf("Excepted tasks: %.0f\n", exceptedTasks);
        System.out.printf("Failure prob: %.2f%%\n", exceptedTasks / numTask * 100);
        System.out.printf("Average queue size: %.2f\n", queueSize / numTask);
        System.out.printf("Pool - %d | Q capacity - %d | Delay - %d\n\n", corePoolSize, queueCapacity, QdelayBound);
    }
    public double getExceptedTasks () {
        return exceptedTasks;
    }
    public double getFail() {
        return exceptedTasks / numTask * 100;
    }
    public double getAvgQueueSize() {
        return queueSize / numTask;
    }
}
