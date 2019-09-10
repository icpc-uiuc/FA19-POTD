import java.util.*;

public class Main {
    public static List<Integer> getPrimesUnder70() {
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= 70; i++) {
            boolean isprime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isprime = false;
                    break;
                }
            }
            if (isprime) primes.add(i);
        }
        return primes;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sz = 71;
        int n = sc.nextInt();
        int[] numberCount = new int[sz];
        for (int i = 0; i < n; i++) {
            int a = sc.nextInt();
            numberCount[a]++;
        }
        int[] mask = new int[sz];
        List<Integer> primes = getPrimesUnder70();
        for (int i = 1; i < sz; i++) {
            int num = i;
            for (int j = 0; j < primes.size(); j++) {
                while (num % primes.get(j) == 0) {
                    mask[i] ^= 1 << j;
                    num /= primes.get(j);
                }
            }
        }
        int mod = 1_000_000_007;
        long[] dp = new long[1 << primes.size()];
        dp[0] = 1;
        long[] pow2 = new long[n + 1];
        pow2[0] = 1;
        for (int i = 1; i < pow2.length; i++) {
            pow2[i] = 2 * pow2[i - 1] % mod;
        }

        for (int i = 1; i < sz; i++) {
            int count = numberCount[i];
            int msk = mask[i];
            long[] newDp = new long[1 << primes.size()];
            if (count > 0) { // \sum_{i odd} {count \choose i} = \sum_{i even} {count \choose i} = 2^{count - 1}, count > 0
                for (int j = 0; j < 1 << primes.size(); j++) {
                    //odd
                    newDp[j ^ msk] = (newDp[j ^ msk] + dp[j] * pow2[count - 1]) % mod;
                    //even
                    newDp[j] = (newDp[j] + dp[j] * pow2[count - 1]) % mod;
                }
            } else {
                for (int j = 0; j < 1 << primes.size(); j++) {
                    //even (0)
                    newDp[j] = (newDp[j] + dp[j]) % mod;
                }
            }
            dp = newDp;
        }
        System.out.println((dp[0] + mod - 1) % mod);
    }
}
