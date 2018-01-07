import java.util.concurrent.*;

public class ScheduledTasks {

    /*
        ScheduledThreadPoolExecutor

        1. can timer too but has disadvantages like single task at a time
        2. Can help build own scheduling service can use DelayOeue, BlockingQueue
            implementation that provides the scheduling functionality
            of ScheduledThreadPoolExecutor
     */
    private static final int NTHREADS=4;

    private static final ScheduledExecutorService executor= Executors.newScheduledThreadPool(NTHREADS);
    public static void main(String args[]) {

        //Declare task
        Runnable task = () -> { printThread(); };

        int numOfTasks = 500;

        while (numOfTasks>0){
            executor.schedule(task,5, TimeUnit.SECONDS);
            numOfTasks--;
        }

        //Shutdown the executor
        executor.shutdown();

    }

    public static void printThread() {

        System.out.println("Executing thread : " + Thread.currentThread().getName());

    }
}
