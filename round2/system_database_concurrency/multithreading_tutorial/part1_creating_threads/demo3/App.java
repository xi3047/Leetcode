package round2.system_database_concurrency.multithreading_tutorial.part1_creating_threads.demo3;

/**
 * @author Xi Zhang
 * @date 1/12/21 1:31 AM
 * @topic round2.system_database_concurrency.multithreading_tutorial.part1.demo3
 * @link
 * @description
 * Creating a thread instance with anonymous Runnable
 *
 * Difference between two methods:
 * 1. When we extend Thread class, we can't extend any other class
 * 2. Extending Thread class will let every thread creates a unique object and associate with it.
 *    When we implements with Runnable, it shares the same object to multiple threads
 */
public class App {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Hello " + i);
                    try {
                        // static method
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t1.start();
    }

}
