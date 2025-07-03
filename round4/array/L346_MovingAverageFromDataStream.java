package round4.array;

import java.util.LinkedList;
import java.util.Queue;

public class L346_MovingAverageFromDataStream {
    Queue<Integer> queue;
    double sum;
    int size;

    public L346_MovingAverageFromDataStream(int size) {
        this.size = size;
        this.queue = new LinkedList<>();
        this.sum = 0;
    }

    public double next(int val) {
        if (queue.size() == size) {
            sum -= queue.poll();
        }
        queue.offer(val);
        sum += val;
        return sum / queue.size();
    }
}
