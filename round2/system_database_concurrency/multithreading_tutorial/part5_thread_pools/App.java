package round2.system_database_concurrency.multithreading_tutorial.part5_thread_pools;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author Xi Zhang
 * @date 1/13/21 12:03 AM
 * @topic round2.system_database_concurrency.multithreading_tutorial.part5_thread_pools
 * @link https://www.youtube.com/watch?v=KUdro0G1BV4&list=PLBB24CFB073F1048E&index=5&ab_channel=CaveofProgramming
 * @description
 * Threads pool is to manage multiple threads at the same time
 * We can avoid the overhead of starting new threads by recycling the threads in the thread pool
 */

class Processor implements Runnable {

    private int id;
    public Processor(int id) {
        this.id = id;
    }
    @Override
    public void run() {
        System.out.println("Starting:  " + id);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Completed: " + id);
    }
}
public class App {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        for (int i = 0; i < 5; i++) {
            executor.submit(new Processor(i));
        }

        executor.shutdown(); // wait for all the threads to complete

        System.out.println("All tasks submitted.");
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All tasks completed.");

    }
}
