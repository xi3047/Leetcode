package round2.system_database_concurrency.multithreading_tutorial.part8_wait_notify;

/**
 * @author Xi Zhang
 * @date 1/13/21 12:45 AM
 * @topic round2.system_database_concurrency.multithreading_tutorial.part8_wait_notify
 * @link https://www.youtube.com/watch?v=gx_YUORX5vk&list=PLBB24CFB073F1048E&index=8&ab_channel=CaveofProgramming
 * @description
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        final Processor processor = new Processor();
        Thread t1 = new Thread(() -> {
            try {
                processor.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread t2 = new Thread(() -> {
            try {
                processor.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

}
