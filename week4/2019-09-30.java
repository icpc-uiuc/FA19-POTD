import java.io.*;
import java.util.*;

public class Main {
    public static class Task {
        int mod = 998244353;
        public int add(int a, int b) {
            int c = a + b;
            if (c < 0) return c + mod;
            if (c >= mod) return c - mod;
            return c;
        }
        public int mult(int a, int b) {
            long c = (long) a * b;
            return (int) (c % mod);
        }
        public int add(int... a) {
            int x = 0;
            for (int b: a) x = add(x, b);
            return x;
        }
        public int mult(int... a) {
            int x = 1;
            for (int b: a) x = mult(x, b);
            return x;
        }
        int[] factorial;
        int[] invFactorial;
        public void precompute() {
            factorial = new int[1_000_001];
            factorial[0] = 1;
            for (int i = 1; i < factorial.length; i++) {
                factorial[i] = mult(factorial[i - 1], i);
            }
            invFactorial = new int[factorial.length];
            for (int i = 0; i < invFactorial.length; i++) {
                invFactorial[i] = power(factorial[i], mod - 2);
            }
        }

        public int power(int a, int b) {
            int res = 1;
            while (b != 0) {
                if ((b & 1) != 0) res = mult(res, a);
                a = mult(a, a);
                b >>= 1;
            }
            return res;
        }

        public int nChoosek(int n, int k) {
            return mult(factorial[n], invFactorial[k], invFactorial[n - k]);
        }

        public void solve(Scanner sc, PrintWriter pw) throws IOException {
            precompute();
            int n = sc.nextInt();
            int res0 = 0; // 2 * \sum_{i=1..n} {n \choose i} * power(3, n^2 - n * i + i) * power(-1, i + 1)
            {
                int pow = (int) ((long) n * n % (mod - 1));
                pow = power(3, pow);
                int inc = power(3, mod - n); // 3 ** (1 - n)
                for (int i = 1; i <= n; i++) {
                    pow = mult(pow, inc);
                    int sub = mult(nChoosek(n, i), pow);
                    if (i % 2 == 1) {
                        res0 = add(res0, sub);
                    } else {
                        res0 = add(res0, -sub);
                    }
                }
            }
            res0 = mult(res0, 2);
            int res1 = 0; // 3 * \sum_{i=0..n-1} {n \choose i} * power(-1, i + 1) * (power(1 - power(3, i), n) - power(-power(3, i), n))
            {
                int pow3 = 1;
                for (int i = 0; i < n; i++) {
                    int pow1 = power(add(1, -pow3), n);
                    int pow2 = power(add(0, -pow3), n);
                    int sub = mult(nChoosek(n, i), add(pow1, -pow2));
                    if (i % 2 == 1) {
                        res1 = add(res1, sub);
                    } else {
                        res1 = add(res1, -sub);
                    }
                    pow3 = mult(pow3, 3);
                }
            }
            res1 = mult(res1, 3);
            pw.println(add(res0, res1) % mod);
        }
    }

    // template, actual code is in class Task.
    static long TIME_START, TIME_END;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new FileInputStream("input"));
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(System.out));
//        PrintWriter pw = new PrintWriter(new FileOutputStream("output"));

        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        TIME_START = System.currentTimeMillis();
        Task t = new Task();
        t.solve(sc, pw);
        TIME_END = System.currentTimeMillis();
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        pw.close();
        System.err.println("Memory increased: " + (usedMemoryAfter - usedMemoryBefore) / 1000000);
        System.err.println("Time used: " + (TIME_END - TIME_START) + ".");
    }

    // Faster IO with BufferedReader wrapped with Scanner
    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public Scanner(FileReader s) throws FileNotFoundException {
            br = new BufferedReader(s);
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        public boolean ready() throws IOException {
            return br.ready();
        }
    }
}
