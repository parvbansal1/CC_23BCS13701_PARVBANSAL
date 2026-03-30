import java.util.*;

class Solution {
    static class Tuple {
        int node, cost, stops;

        Tuple(int node, int cost, int stops) {
            this.node = node;
            this.cost = cost;
            this.stops = stops;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        // Step 1: Build graph
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] f : flights) {
            adj.get(f[0]).add(new int[]{f[1], f[2]});
        }

        // Step 2: Min Heap (cost based)
        PriorityQueue<Tuple> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.offer(new Tuple(src, 0, 0));

        // Step 3: Stops array (VERY IMPORTANT)
        int[] stopsArr = new int[n];
        Arrays.fill(stopsArr, Integer.MAX_VALUE);

        while (!pq.isEmpty()) {
            Tuple curr = pq.poll();

            int node = curr.node;
            int cost = curr.cost;
            int stops = curr.stops;

            // If destination reached → return immediately (min cost)
            if (node == dst) return cost;

            // If stops exceeded OR worse path → skip
            if (stops > k || stops > stopsArr[node]) continue;

            stopsArr[node] = stops;

            for (int[] nbr : adj.get(node)) {
                int next = nbr[0];
                int price = nbr[1];

                pq.offer(new Tuple(next, cost + price, stops + 1));
            }
        }

        return -1;
    }
}
