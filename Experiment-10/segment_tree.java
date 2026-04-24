import java.util.*;

class Solution {
    
    int[] tree;
    int size;

    public List<Integer> countSmaller(int[] nums) {
        int offset = 10000; // shift for negative values
        size = 2 * 10000 + 1;
        tree = new int[4 * size];
        
        List<Integer> result = new ArrayList<>();
        
        for (int i = nums.length - 1; i >= 0; i--) {
            int val = nums[i] + offset;
            int count = query(0, val - 1, 0, size - 1, 0);
            result.add(count);
            update(val, 0, size - 1, 0);
        }
        
        Collections.reverse(result);
        return result;
    }
    
    private void update(int idx, int start, int end, int node) {
        if (start == end) {
            tree[node]++;
            return;
        }
        
        int mid = (start + end) / 2;
        
        if (idx <= mid)
            update(idx, start, mid, 2 * node + 1);
        else
            update(idx, mid + 1, end, 2 * node + 2);
        
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }
    
    private int query(int l, int r, int start, int end, int node) {
        if (r < start || end < l) return 0;
        if (l <= start && end <= r) return tree[node];
        
        int mid = (start + end) / 2;
        return query(l, r, start, mid, 2 * node + 1) +
               query(l, r, mid + 1, end, 2 * node + 2);
    }
}




// MERGE SORT BASED

import java.util.*;

class Solution {
    
    class Pair {
        int val, index;
        Pair(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }
    
    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Pair[] arr = new Pair[n];
        int[] count = new int[n];
        
        // Store value with original index
        for (int i = 0; i < n; i++) {
            arr[i] = new Pair(nums[i], i);
        }
        
        mergeSort(arr, 0, n - 1, count);
        
        List<Integer> result = new ArrayList<>();
        for (int c : count) {
            result.add(c);
        }
        
        return result;
    }
    
    private void mergeSort(Pair[] arr, int left, int right, int[] count) {
        if (left >= right) return;
        
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid, count);
        mergeSort(arr, mid + 1, right, count);
        merge(arr, left, mid, right, count);
    }
    
    private void merge(Pair[] arr, int left, int mid, int right, int[] count) {
        List<Pair> temp = new ArrayList<>();
        int i = left, j = mid + 1;
        int rightCount = 0;
        
        while (i <= mid && j <= right) {
            if (arr[j].val < arr[i].val) {
                rightCount++;
                temp.add(arr[j++]);
            } else {
                count[arr[i].index] += rightCount;
                temp.add(arr[i++]);
            }
        }
        
        while (i <= mid) {
            count[arr[i].index] += rightCount;
            temp.add(arr[i++]);
        }
        
        while (j <= right) {
            temp.add(arr[j++]);
        }
        
        // Copy back
        for (int k = 0; k < temp.size(); k++) {
            arr[left + k] = temp.get(k);
        }
    }
}
