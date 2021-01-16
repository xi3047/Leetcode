package round2.system_database_concurrency.multithreading_tutorial.part2_basic_thread_synchronization;

import java.util.Scanner;

/**
 * @author Xi Zhang
 * @date 1/12/21 1:37 AM
 * @topic round2.system_database_concurrency.multithreading_tutorial.part2_basic_thread_synchronization
 * @link https://www.youtube.com/watch?v=_aNO6x8HXZ0&list=PLBB24CFB073F1048E&index=2&ab_channel=CaveofProgramming
 * @description purpose of volatile keyword, prevent threads from caching variables,
 * it guarantees the current state of the volatile variable is visible to all the threads
 */
class Processor extends Thread {

    private volatile boolean running = true;


    public void run() {
        while (running) {
            System.out.println("hello");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}
public class App {
    public static void main(String[] args) {
        Processor proc1 = new Processor();
        proc1.start();

        System.out.println("Press return to stop");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        proc1.shutdown();


    }
}
