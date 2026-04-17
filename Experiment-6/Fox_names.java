import java.util.*;

public class FoxAndNames {

    public static String findOrder(String[] words) {
        // Step 1: Graph + indegree
        Map<Character, List<Character>> adj = new HashMap<>();
        int[] indegree = new int[26];

        for (char c = 'a'; c <= 'z'; c++) {
            adj.put(c, new ArrayList<>());
        }

        // Step 2: Build graph
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];

            // Edge case: prefix issue
            if (w1.length() > w2.length() && w1.startsWith(w2)) {
                return "Impossible";
            }

            for (int j = 0; j < Math.min(w1.length(), w2.length()); j++) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);

                if (c1 != c2) {
                    adj.get(c1).add(c2);
                    indegree[c2 - 'a']++;
                    break;
                }
            }
        }

        // Step 3: Topological sort (BFS)
        Queue<Character> queue = new LinkedList<>();

        for (char c = 'a'; c <= 'z'; c++) {
            if (indegree[c - 'a'] == 0) {
                queue.add(c);
            }
        }

        StringBuilder result = new StringBuilder();

        while (!queue.isEmpty()) {
            char curr = queue.poll();
            result.append(curr);

            for (char neighbor : adj.get(curr)) {
                indegree[neighbor - 'a']--;
                if (indegree[neighbor - 'a'] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Step 4: Check cycle
        if (result.length() != 26) {
            return "Impossible";
        }

        return result.toString();
    }

    public static void main(String[] args) {
        String[] words = {"baa", "abcd", "abca", "cab", "cad"};
        System.out.println(findOrder(words));
    }
}
