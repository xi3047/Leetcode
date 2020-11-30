package round1_misc.amazon;

import round1.UnionFind;
import round1.UnionFind.Node;
import org.junit.Test;

import java.util.*;

public class minimumCost {
    public int minimumCost(int numTotalEdgeNodes,
                            int numTotalAvailableNetworkRoutes,
                            List<List<Integer>> networkRoutesAvailable,
                            int numNewNetworkRoutesConstruct,
                            List<List<Integer>> costNewNetworkRoutesConstruct) {

        //corner cases
        if (numTotalEdgeNodes < 0 || networkRoutesAvailable == null || costNewNetworkRoutesConstruct == null ) return - 1;
        if (numTotalAvailableNetworkRoutes + numNewNetworkRoutesConstruct < numTotalEdgeNodes - 1) return - 1;
        if (numTotalEdgeNodes <= 1) return 0;

        // initialize union find --> O(N)
        UnionFind uf = new UnionFind(numTotalEdgeNodes);
        Node[] nodes = uf.nodes;

        Set<List<Integer>> set = new HashSet<>();
        for(List<Integer> route: costNewNetworkRoutesConstruct) set.add(route.subList(0, 2));

        for(List<Integer> route: networkRoutesAvailable) {
            if(!set.contains(route)) {
                if(uf.find(nodes[route.get(0) - 1], nodes[route.get(1) - 1])) continue;
                uf.union(nodes[route.get(0) - 1], nodes[route.get(1) - 1]);
            }
        }

        // sort new networks by cost in ascending order --> O(MlogM)
        Collections.sort(costNewNetworkRoutesConstruct, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> l1, List<Integer> l2) {
                return l1.get(2) - l2.get(2);
            }
        });

        int costSum = 0;
        // iterate new networks routes, from low cost to high cost, build new network until all nodes get connected --> O(MlogN)
        for(List<Integer> newRoute: costNewNetworkRoutesConstruct) {

            int n1 = newRoute.get(0);
            int n2 = newRoute.get(1);

            if(uf.find(nodes[n1 - 1], nodes[n2 - 1])) continue;
            uf.union(nodes[n1 - 1], nodes[n2 - 1]);
            costSum += newRoute.get(2);
            if(uf.setCount == 1) break;
        }

        if(uf.setCount != 1) return -1;
        return costSum;
    }

    @Test
    public void test() {

        List<List<Integer>> networkRoutesAvailable = new ArrayList<>();
        List<Integer> a = new ArrayList<>();
        a.add(1); a.add(2);
        List<Integer> b = new ArrayList<>();
        b.add(2); b.add(3);
        List<Integer> c = new ArrayList<>();
        c.add(3); c.add(4);
        List<Integer> d = new ArrayList<>();
        d.add(4); d.add(5);
        List<Integer> e = new ArrayList<>();
        e.add(1); e.add(5);
        networkRoutesAvailable.add(a);
        networkRoutesAvailable.add(b);
        networkRoutesAvailable.add(c);
        networkRoutesAvailable.add(d);
        networkRoutesAvailable.add(e);
        List<List<Integer>> costNewNetworkRoutesConstruct = new ArrayList<>();
        List<Integer> aa = new ArrayList<>();
        aa.add(1); aa.add(2); aa.add(12);
        List<Integer> bb = new ArrayList<>();
        bb.add(3); bb.add(4); bb.add(30);
        List<Integer> cc = new ArrayList<>();
        cc.add(1); cc.add(5); cc.add(8);
        costNewNetworkRoutesConstruct.add(aa);
        costNewNetworkRoutesConstruct.add(bb);
        costNewNetworkRoutesConstruct.add(cc);
        System.out.println(minimumCost(5, 5, networkRoutesAvailable, 3, costNewNetworkRoutesConstruct));//20
    }

}


