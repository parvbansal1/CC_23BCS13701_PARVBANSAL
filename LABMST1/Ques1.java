import java.util.*;

public class minEnergy {

    public static int minEffort(int[][] arr) {

        Arrays.sort(arr, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));

        int initial = 0;
        int curr = 0;

        for (int[] task : arr) {

            int actual = task[0];
            int min = task[1];

            if (curr < min) {
                initial += (min - curr);
                curr = min;
            }

            curr -= actual;
        }

        return initial;
    }

    public static void main(String[] args) {
        int[][] arr = { { 1, 2 }, { 2, 4 }, { 4, 8 } };
        int res = minEffort(arr);
        System.out.println("Min Effort is: " + res);
    }
}
