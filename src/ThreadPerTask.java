

public class ThreadPerTask {

    /*
    Tasks : independent activities , does not depended on other tasks
     */
    public static void main(String args[]) {


        int numOfTasks = 50;


        /*
        Runnable task=new Runnable() {
            @Override
            public void run() {
                printThread();
            }
        };
        */


        //Replacing runnable with lambda expression

        Runnable task = () -> {
            printThread();
        };

        while (numOfTasks > 0) {
            new Thread(task).start();
            numOfTasks--;
        }
    }

    public static void printThread() {

        System.out.println("Executing thread : " + Thread.currentThread().getName());

    }

}
