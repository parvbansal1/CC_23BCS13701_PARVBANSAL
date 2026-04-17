class Solution {
    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int dp[] = new int[n];
        Arrays.fill(dp, -1);
        return maxSum(arr, k, dp, 0);
    }

    public int maxSum(int arr[], int k, int dp[], int st) {
        int n = arr.length;
        if (st >= n) {
            return 0;
        }
        if (dp[st] != -1)
            return dp[st];

        int currMax = 0, ans = 0;
        int end = Math.min(n, st + k);
        for (int i = st; i < end; i++) {
            currMax = Math.max(currMax,arr[i]);
            ans = Math.max(ans, currMax * (i- st + 1) + 
            maxSum(arr,k,dp,i+1));
        }
        return dp[st] = ans;
    }
}
