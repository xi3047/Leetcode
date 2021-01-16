package round2.system_database_concurrency.multithreading_tutorial.part4_multiple_locks;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Xi Zhang
 * @date 1/12/21 10:23 AM
 * @topic round2.system_database_concurrency.multithreading_tutorial.part4_multiple_locks
 * @link
 * @description
 *
 * Only one intrinsic lock for the Worker object, so one thread will acquire the lock and other threads have to wait.
 * We can use lock object so we only acquiring the intrinsic locks for the lock object only
 */
public class Worker {

    private Random random = new Random();

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public void stageOne() {
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }

    }

    public synchronized void stageTwo() {
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));
        }

    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }
    public void main() {
        System.out.println("Starting...");
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(this::process);
        Thread t2 = new Thread(this::process);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("Time take: " + (end - start));
        System.out.println("List1: " + list1.size() + "; List2: " + list2.size());
    }
}
