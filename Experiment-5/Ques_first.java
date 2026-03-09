import java.util.*;

class Job {
    int id;
    int deadline;
    int profit;

    Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class Main {

    public static int jobScheduling(List<Job> jobs) {

        // Sort jobs by deadline
        Collections.sort(jobs, (a, b) -> a.deadline - b.deadline);

        // Min Heap to store profits
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (Job job : jobs) {
            minHeap.add(job.profit);

            if (minHeap.size() > job.deadline) {
                minHeap.poll(); // remove smallest profit
            }
        }

        int maxProfit = 0;

        while (!minHeap.isEmpty()) {
            maxProfit += minHeap.poll();
        }

        return maxProfit;
    }

    public static void main(String[] args) {

        List<Job> jobs = new ArrayList<>();

        jobs.add(new Job(1, 2, 100));
        jobs.add(new Job(2, 1, 19));
        jobs.add(new Job(3, 2, 27));
        jobs.add(new Job(4, 1, 25));
        jobs.add(new Job(5, 3, 15));

        System.out.println("Maximum Profit: " + jobScheduling(jobs));
    }
}
