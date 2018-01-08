import java.util.concurrent.*;

public class CallableAndFuture {

    private static final int NTHREADS=4;
    private static final ExecutorService executor= Executors.newFixedThreadPool(NTHREADS);
    /*
        Runnable is limited , as run cannot :

        1. return a value
        2. throw an exception

        Tasks :
           1. finite : have a clear starting and end point

           2. life-cycle of a task executed by executor has 4 phases:

                1. created
                2. submitted
                3. started
                4. completed


        Futures:
            1. Represents lifecycle of a task
            2. Provides method to test :
                2.1 if method is completed
                2.2 retrieve its result
                2.3 cancel the task
     */
    public static void main(String args[]) {

        int numOfTasks = 50;

        Callable<String> task=new Callable<String>() {
            @Override
            public String call() throws Exception {

                return printThread();

            }
        };

        while(numOfTasks>0 ){
            Future<String> future=executor.submit(task);

            try {
                String result=future.get();
                System.out.println(result);

            } catch (InterruptedException e) {
                System.out.println("InterruptedException received");
                future.cancel(true);
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            numOfTasks--;
        }
    }
    public static String printThread() throws InterruptedException {

        return ("Executing thread : " + Thread.currentThread().getName());

    }
}
