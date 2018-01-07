import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class ExecutorLifecycle {
    /*
    ExecutionService extends executor
        3 states:

            1. running

            2. shutting down

            3. terminated

        Methods:

            1. shutdown : graceful shutdown, no new tasks accepted but previously submitted
            tasks are allowed to complete

            2. shutdownNow: initiates abrupt shutdown, cancel outstanding tasks, does not
            start any tasks that queued but not begun

            rejectedExecutionHandler : handles tasks submitted to ExecutorService after it has
            been shutdown

     */

    private static final int NTHREADS = 4;
    private static final ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);

    public static void main(String args[]) {

        Runnable task = () -> {
            printThread();
        };

        int numOfTasks = 500;

        while (numOfTasks > 0 && !executor.isShutdown()) {
            try {

                //calling shutdown , when numOfTasks=100
                if (numOfTasks == 100) {
                    stop();
                }
                executor.execute(task);

            } catch (RejectedExecutionException ex) {

                if (!executor.isShutdown()) {
                    System.out.println("task submission rejectded " + ex);
                }
            }

            numOfTasks--;
        }

    }

    public static void stop() {
        executor.shutdown();
        System.out.println("Shutdown submitted");
    }

    public static void printThread() {

        System.out.println("Executing thread : " + Thread.currentThread().getName());

    }

}