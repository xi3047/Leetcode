package round2.system_database_concurrency;

import java.util.PriorityQueue;

/**
 * @author Xi Zhang
 * @date 12/26/2020 11:56 AM
 * @topic round2.shell_database_concurrency
 * @link
 * @description
 */
public class Deadlock {
    int count = 0;

    void increment() {
        count = count + 1;
    }

    static class Friend {
        private final String name;
        public Friend(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s"
                            + "  has bowed to me!%n",
                    this.name, bower.getName());
            bower.bowBack(this);
        }
        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s"
                            + " has bowed back to me!%n",
                    this.name, bower.getName());
        }
    }

    public static void main(String[] args) {
        final Friend John =
                new Friend("John");
        final Friend Mary =
                new Friend("Mary");
        for (int i = 0; i < 1; i++) {
            System.out.println("Times:" + i);
            new Thread(new Runnable() {
                public void run() { John.bow(Mary); }
            }).start();

            new Thread(new Runnable() {
                public void run() { Mary.bow(John); }
            }).start();
        }

    }
    public void test() {
        int size  = 4;
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        // if 4 < 2
        while (size >= 0) {
            int max = Math.min(size, heap.peek());
            size -= max;
        }
    }
}