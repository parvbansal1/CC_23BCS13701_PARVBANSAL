// Topological Sort

import java.util.*;

class Solution {
    public static ArrayList<Integer> topoSort(int n, int[][] arr) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        int indegree[] = new int[n];
        for(int i=0;i<arr.length;i++){
            int u = arr[i][0];
            int v = arr[i][1];
            adj.get(u).add(v);
            indegree[v]++;
        }
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for(int i=0;i<n;i++){
            if(indegree[i]==0){
                q.add(i);
            }
        }
        while(q.size()>0){
            int top = q.remove();
            ans.add(top);
            for(int i:adj.get(top)){
                indegree[i]--;
                if(indegree[i]==0){
                    q.add(i);
                }
            }
        }
        return ans;
    }
}
