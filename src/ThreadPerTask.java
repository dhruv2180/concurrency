

public class ThreadPerTask {

    /*
        Disadvantage of the approach:
        1. Thread lifecycle overhead
        2. Resource Consumption
        3. Stability

        Over a certain limit, threads may slow down the program,
        can cause application to crash due to outOfMemory
    */
    public static void main(String args[]) {

        int numOfTasks = 50;

        Runnable task=new Runnable() {
            @Override
            public void run() {
                printThread();
            }
        };


        //Runnable with lambda expression
        Runnable task2 = () -> { printThread(); };

        while (numOfTasks > 0) {
            new Thread(task2).start();
            numOfTasks--;
        }
    }

    public static void printThread() {

        System.out.println("Executing thread : " + Thread.currentThread().getName());

    }

}
