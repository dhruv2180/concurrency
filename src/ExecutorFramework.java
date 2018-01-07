import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExecutorFramework {

    private static final int NTHREADS=4;

    /*

    Thread pool static factory methods:

        1. NewFixedThreadPool : Keep pool size constant, adds new thread only
        when an existing thread dies unexpectedly.

        2. NewCachedThreadPool : More flexible to reap idle threads when current size
        of the pool exceeds the demand for processing and add new thread when demand increases

        3. NewSingleThreadExecutor : single worker thread.

        4. NewScheduledThreadPool : A fixed-size thread pool that supports deplayed and
        periodic task execution, similar to timer.

     */

    private static final Executor executor= Executors.newFixedThreadPool(NTHREADS);
    public static void main(String args[]) {
        /*
        1. Task : Logical unit of work
        2. Threads : mechanism by which tasks can run asynchronously
        3. Execution Framework:
            ->Decouples task execution from task submission
            ->Provide lifecycle support and hooks for adding statics gathering
            , application management and monitoring
            -> Based on producer-consumer pattern

         */


        //Declare task
        Runnable task = () -> { printThread(); };

        int numOfTasks = 500;

        while (numOfTasks>0){
            executor.execute(task);
            numOfTasks--;
        }

    }

    public static void printThread() {

        System.out.println("Executing thread : " + Thread.currentThread().getName());

    }
}
