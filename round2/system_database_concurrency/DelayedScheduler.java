package round2.system_database_concurrency;

/**
 * @author Xi Zhang
 * @date 1/13/21 4:41 AM
 * @topic round2.system_database_concurrency
 * @link
 * @description
 */
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DelayedScheduler {

    private static long START_TIME;

    public static void main(String[] args) throws InterruptedException {

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(4);

        START_TIME = System.currentTimeMillis();
        Runnable task1 = printTask("T1");
        Runnable task2 = printTask("T2");
        Runnable task3 = printTask("T3");
        Runnable task4 = printTask("T4");

        scheduledExecutorService.scheduleAtFixedRate(task1, 3, 3, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(task2, 5, 3, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(task3, 0, 5, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleAtFixedRate(task4, 2, 2, TimeUnit.SECONDS);

        Thread.sleep(15000);
        scheduledExecutorService.shutdown();
        scheduledExecutorService.awaitTermination(6000, TimeUnit.SECONDS);
    }

    private static Runnable printTask(String prefix) {
        return () -> System.out.println(prefix + ": " + (System.currentTimeMillis() - START_TIME));
    }

}
