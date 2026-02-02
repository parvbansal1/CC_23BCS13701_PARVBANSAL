class Solution {
    public int nthMagicalNumber(int n, int a, int b) {
        long mod = 1000000007;
        long low = Math.min(a, b);
        long high = (long) n * Math.min(a, b);

        long A = a, B = b;
        while (B != 0) {
            long temp = A;
            A = B;
            B = temp % B;
        }
        long gcd = A;
        long lcm = (a * b) / gcd;

        while (low < high) {
            long mid = low + (high - low) / 2;
            long count = mid / a + mid / b - mid / lcm;
            if (count >= n) high = mid;
            else low = mid + 1;
            }
        return (int) (low % mod);
    }
}
