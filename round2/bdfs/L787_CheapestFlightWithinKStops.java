package round2.bdfs;

import java.util.*;

/**
 * @author Xi Zhang
 * @date 1/24/2021 12:00 AM
 * @topic round2.bdfs
 * @link https://leetcode.com/problems/cheapest-flights-within-k-stops/
 * @description
 */
public class L787_CheapestFlightWithinKStops {
    class Dijstra {
        /**
         * Time: O(ElogV)
         */
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

            Map<Integer, Map<Integer, Integer>> prices = new HashMap<>();
            for (int[] f : flights) {
                if (!prices.containsKey(f[0])) prices.put(f[0], new HashMap<>());
                prices.get(f[0]).put(f[1], f[2]);
            }
            Queue<int[]> pq = new PriorityQueue<>((a, b) -> (Integer.compare(a[0], b[0])));
            pq.add(new int[] {0, src, K + 1});
            while (!pq.isEmpty()) {
                int[] top = pq.remove();
                int price = top[0];
                int city = top[1];
                int stops = top[2];
                if (city == dst) return price;
                if (stops > 0) {
                    Map<Integer, Integer> adj = prices.getOrDefault(city, new HashMap<>());
                    for (int a : adj.keySet()) {
                        pq.add(new int[] {price + adj.get(a), a, stops - 1});
                    }
                }
            }
            return -1;
        }
    }
    class DFS {
        int ans_dfs;
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K)
        {
            ans_dfs=Integer.MAX_VALUE;
            Map<Integer, List<int[]>> map=new HashMap<>();
            for(int[] i:flights)
            {
                map.putIfAbsent(i[0],new ArrayList<>());
                map.get(i[0]).add(new int[]{i[1],i[2]});
            }
            dfs(map,src,dst,K+1,0);
            return ans_dfs==Integer.MAX_VALUE?-1:ans_dfs;
        }
        public void dfs(Map<Integer,List<int[]>> map, int src, int dst, int k, int cost)
        {
            if(k<0)
                return;
            if(src==dst)
            {
                ans_dfs=cost;
                return;
            }
            if(!map.containsKey(src))
                return;
            for(int[] i:map.get(src))
            {
                if(cost+i[1]>ans_dfs)               //Pruning, check the sum of current price and next cost. If it's greater then the ans so far, continue
                    continue;
                dfs(map,i[0],dst,k-1,cost+i[1]);
            }
        }
    }
    class Bellman_Ford {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K)
        {
            int[] cost=new int[n];
            Arrays.fill(cost,Integer.MAX_VALUE);
            cost[src]=0;
            for(int i=0;i<=K;i++)
            {
                int[] temp= Arrays.copyOf(cost,n);
                for(int[] f: flights)
                {
                    int curr=f[0],next=f[1],price=f[2];
                    if(cost[curr]==Integer.MAX_VALUE)
                        continue;
                    temp[next]=Math.min(temp[next],cost[curr]+price);
                }
                cost=temp;
            }
            return cost[dst]==Integer.MAX_VALUE?-1:cost[dst];
        }
    }
}
