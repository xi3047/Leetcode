package round2.system_database_concurrency.multithreading_tutorial.part9_low_level_producer_consumer;

/**
 * @author Xi Zhang
 * @date 1/25/2021 11:23 AM
 * @topic round2.system_database_concurrency.multithreading_tutorial.part9_low_level_producer_consumer
 * @link
 * @description
 */
public class App {
    public static void main(String[] args) throws InterruptedException {
        Processor processor = new Processor();
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
