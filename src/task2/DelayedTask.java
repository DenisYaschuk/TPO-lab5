package task2;

class DelayedTask implements Runnable {
    private int delay;
    public DelayedTask(int delay) {
        this.delay = delay;
    }
    @Override
    public void run() {
        try
        {
            // simulating the delay in task processing
            Thread.sleep(this.delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}