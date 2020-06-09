package leetcode;
/*
    @author: Xi Zhang
    @date:   2019-03-02
    @time:   20:10
 */
import java.util.LinkedList;
import java.util.Queue;

public class L785_isGraphBipartite {
    public boolean isBipartite(int[][] graph) {
        //BFS
        // 0(not meet), 1(black), 2(red)
        // set all unvisited vertex to color blank
        int[] visited = new int[graph.length];
        // go through every vertex as a source as the graph may not be strongly connected
        for (int i = 0; i < graph.length; i++) {
            // if the vertex is not an empty cluster and it hasn't been visited
            if (graph[i].length != 0 && visited[i] == 0) {
                //mark the source as Black
                visited[i] = 1;
                // create a queue and throw in the source
                Queue<Integer> q = new LinkedList<>();
                q.offer(i);
                while(! q.isEmpty()) {
                    int current = q.poll();
                    // for every adjacent vertex
                    for (int neighbor: graph    [current]) {
                        // if the neighbour isn't filled, fill it with different color and add it to the queue
                        if (visited[neighbor] == 0) {
                            visited[neighbor] = (visited[current] == 1) ? 2 : 1;
                            q.offer(neighbor);
                        } else {
                            // if the neighbour is filled and it is of the same color, return false
                            if (visited[neighbor] == visited[current]) return false;
                        }
                    }
                }

            }
        }
        return true;
    }
}

