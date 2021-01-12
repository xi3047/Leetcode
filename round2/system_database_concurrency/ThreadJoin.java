package round2.system_database_concurrency;

/**
 * @author Xi Zhang
 * @date 1/1/2021 1:43 PM
 * @topic round2.shell_database_concurrency
 * @link
 * @description
 */
public class ThreadJoin {
    public static void main(String[] args) {
        // Create Thread 1
        Thread thread1 = new Thread(() -> {
            System.out.println("Entered : " + Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println("Interrupted by " + Thread.currentThread().getName());
                throw new IllegalStateException(e);

            }
            System.out.println("Exiting Thread 0");
        });

        // Create Thread 2
//        Runnable runnable = () -> {
//            System.out.println("Entered : " + Thread.currentThread().getName());
//        };
//        Thread thread2 = new Thread(runnable);
        Thread thread2 = new Thread(() -> {
            System.out.println("Entered " + Thread.currentThread().getName());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("Exiting Thread 1");
        });

        System.out.println("Starting Thread 0");
        thread1.start();

        System.out.println("Waiting for Thread 0 to complete");
        try {
            thread1.join(1000);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }

        System.out.println("Waited enough! Starting Thread 1 now");
        thread2.start();
    }
}
