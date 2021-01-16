package round2.system_database_concurrency.multithreading_tutorial.part6_countdown_latchtes;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Xi Zhang
 * @date 1/13/21 12:16 AM
 * @topic round2.system_database_concurrency.multithreading_tutorial.part6_countdown_latchtes
 * @link https://www.youtube.com/watch?v=1H-Vfu1v_2g&list=PLBB24CFB073F1048E&index=6&ab_channel=CaveofProgramming
 * @description
 *
 */

class Processor implements Runnable {

    private CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Started.");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();

    }
}

public class App {

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);
        ExecutorService executor = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            executor.submit(new Processor(latch));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        System.out.println("Completed.");

    }
}
