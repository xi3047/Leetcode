package round2.system_database_concurrency.multithreading_tutorial.part7_producer_consumer;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Xi Zhang
 * @date 1/13/21 12:31 AM
 * @topic round2.system_database_concurrency.multithreading_tutorial.part7_producer_consumer
 * @link
 * @description
 * Blocking Queue, thread safe
 * Best to avoid synchronized keywords as much as you can
 */
public class App {
    private static BlockingQueue<Integer> queue = new ArrayBlockingQueue(10);

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            try {
                producer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                consumer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    private static void producer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            queue.put(random.nextInt(100));
        }
    }

    private static void consumer() throws InterruptedException {
        Random random = new Random();
        while (true) {
            Thread.sleep(100);
            if (random.nextInt(10) == 0) {
                Integer value = queue.take();
                System.out.println("Taken value: " + value + "; Queue size is: " + queue.size());
            }
        }
    }



}
