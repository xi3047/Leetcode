package round2.system_database_concurrency.multithreading_tutorial.part1_creating_threads.demo2;

/**
 * @author Xi Zhang
 * @date 1/12/21 1:27 AM
 * @topic round2.system_database_concurrency.multithreading_tutorial.thread1
 * @link
 * @description Creating a thread by implementing a runnable
 */
class Runner implements Runnable {
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
}

public class App {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runner());
        Thread t2 = new Thread(new Runner());
        t1.start();
        t2.start();
    }
}
