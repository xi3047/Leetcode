package round1;
/*
    @author: Xi Zhang
    @date:   2019-05-10
    @time:   10:46
 */
import org.junit.Test;

import java.util.*;

public class L332_ReconstructItinerary {

    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new LinkedList<>();
        Map<String, PriorityQueue<String>> graph = buildGraph(tickets);
        dfs("JFK", res, graph);
        return res;
    }

    private void dfs(String cur, List<String> res, Map<String, PriorityQueue<String>> graph){
        PriorityQueue<String> minHeap = graph.get(cur);
        while(minHeap != null && !minHeap.isEmpty()){
            String next = minHeap.poll();
            dfs(next, res, graph);
        }
        res.add(0, cur);
    }

    private Map<String, PriorityQueue<String>> buildGraph(List<List<String>> tickets){
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        for(List<String> ticket : tickets){
            PriorityQueue<String> minHeap = graph.getOrDefault(ticket.get(0), new PriorityQueue<>());
            minHeap.offer(ticket.get(1));
            graph.put(ticket.get(0), minHeap);
        }
        return graph;
    }

    @Test
    public void test () {
        List<List<String>> res = Arrays.asList(Arrays.asList("MUC", "LHR"), Arrays.asList("JFK", "MUC"), Arrays.asList("SFO", "SJC"), Arrays.asList("LHR", "SFO"));
        System.out.println(findItinerary(res));

    }


}
