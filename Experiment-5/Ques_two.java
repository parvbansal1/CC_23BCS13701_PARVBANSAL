import java.util.*;

class Solution {

    public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {

        int n = deadline.length;

        List<int[]> jobs = new ArrayList<>();

        // Store deadline and profit
        for (int i = 0; i < n; i++) {
            jobs.add(new int[]{deadline[i], profit[i]});
        }

        // Sort by deadline
        Collections.sort(jobs, (a, b) -> a[0] - b[0]);

        // Min Heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int[] job : jobs) {
            pq.add(job[1]);

            if (pq.size() > job[0]) {
                pq.poll();
            }
        }

        int cnt = 0;
        int totalProfit = 0;

        while (!pq.isEmpty()) {
            cnt++;
            totalProfit += pq.poll();
        }

        ArrayList<Integer> ans = new ArrayList<>();
        ans.add(cnt);
        ans.add(totalProfit);

        return ans;
    }
}
