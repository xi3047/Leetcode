package round2.system_database_concurrency.multithreading_tutorial.part8_wait_notify;

import java.util.Scanner;

/**
 * @author Xi Zhang
 * @date 1/13/21 12:46 AM
 * @topic round2.system_database_concurrency.multithreading_tutorial.part8_wait_notify
 * @link https://www.youtube.com/watch?v=gx_YUORX5vk&list=PLBB24CFB073F1048E&index=8&ab_channel=CaveofProgramming
 * @description
 */
public class Processor {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Producer thread running ...");
            // wait() can only call within synchronized block, get control of the lock, will not resume until
            // 1. this thread regains control the lock
            // 2. another thread that locks on the same object notify (wake up)
            wait();
            System.out.println("Resumed.");

        }
    }

    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(2000);
        synchronized (this) {
            System.out.println("Waiting for return key");
            scanner.nextLine();
            System.out.println("Return key pressed");
            notify(); // notify() does not handle over the control of the lock
            Thread.sleep(5000); // relinquish the lock after 5 seconds
        }
    }
}
