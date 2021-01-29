package round2.system_database_concurrency.multithreading_tutorial.part9_low_level_producer_consumer;

import java.util.LinkedList;
import java.util.Random;

/**
 * @author Xi Zhang
 * @date 1/25/2021 11:23 AM
 * @topic round2.system_database_concurrency.multithreading_tutorial.part9_low_level_producer_consumer
 * @link https://www.youtube.com/watch?v=wm1O8EE0X8k&list=PLBB24CFB073F1048E&index=9
 * @description
 */
public class Processor {
    private LinkedList<Integer> list = new LinkedList<>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (lock) {
                while (list.size() == LIMIT) {
                    lock.wait();
                }
                list.add(value++);
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        Random random = new Random();
        while (true) {
            synchronized (lock) {
                while (list.size() == 0) {
                    lock.wait();
                }
                System.out.print("List size is : " + list.size());
                int value = list.removeFirst();
                System.out.println("; value is: " + value);
                lock.notify();
            }
            Thread.sleep(random.nextInt(1000));
        }
    }
}
