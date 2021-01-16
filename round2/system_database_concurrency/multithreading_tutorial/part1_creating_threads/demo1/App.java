package round2.system_database_concurrency.multithreading_tutorial.part1_creating_threads.demo1;

/**
 * @author Xi Zhang
 * @date 1/12/21 1:22 AM
 * @topic round2.system_database_concurrency.multithreading_tutorial.thread1
 * @link
 * @description Creating a thread by extending Thread
 */

class Runner extends Thread {
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
        Runner runner1 = new Runner();
        runner1.start();

        Runner runner2 = new Runner();
        runner2.start();
    }

}
