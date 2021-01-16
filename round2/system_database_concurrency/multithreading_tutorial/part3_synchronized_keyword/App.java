package round2.system_database_concurrency.multithreading_tutorial.part3_synchronized_keyword;

/**
 * @author Xi Zhang
 * @date 1/12/21 1:50 AM
 * @topic round2.system_database_concurrency.multithreading_tutorial.part3_synchronized_keyword
 * @link https://www.youtube.com/watch?v=lotAYC3hLVo&list=PLBB24CFB073F1048E&index=3&ab_channel=CaveofProgramming
 * @description
 * Using synchronized keyword to deal with interleaving operations (multiple threads) on a shared resource
 * Every object in java has an intrinsic lock or monitor lock (mutex),
 * Synchronized ensures you to have to acquire the intrinsic lock before calling it, only one thread can acquire the
 * lock at one time, other threads have to wait until the first thread release the lock when finished executing.
 */
public class App {
    private  int count = 0;

    public synchronized void increment() {
        count++;
        /*
            actually contains 3 steps
            1. get value of count
            2. add 1 to it
            3. store it back to count
         */
    }

    public static void main(String[] args) throws InterruptedException {
        App app = new App();
        app.doWork();
    }

    public void doWork() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();

                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    increment();
                }
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Count is " + count);

    }
}
