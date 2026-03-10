public class MaxprodWords {

    public static int maxProd(String[] words) {

        int n = words.length;
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            for (char c : words[i].toCharArray()) {
                arr[i] |= (1 << (c - 'a'));
            }
        }

        int ans = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                if ((arr[i] & arr[j]) == 0) {
                    int prod = words[i].length() * words[j].length();
                    ans = Math.max(ans, prod);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        String[] words = { "a", "ab", "abc", "d", "cd", "bcd", "abcd" };

        int result = maxProd(words);

        System.out.println("Maximum prod: " + result);
    }
}
