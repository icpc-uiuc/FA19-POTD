import java.util.*;
import java.io.*;

public class Main {
    static int mod = 1_000_000_007;
    static int n;
    static int[] d;
    public static int add(int a, int b) {
        return (a + b) % mod;
    }
    public static int multTwo(int a, int b) {
        return (int) ((long) a * b % mod);
    }
    public static int mult(int a, int... b) {
        for (int z: b) {
            a = multTwo(a, z);
        }
        return a;
    }
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        d = new int[n];
        for (int i = 0; i < n; i++) {
            d[i] = sc.nextInt();
        }
        int[][] choice = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            choice[i][0] = choice[i][i] = 1;
        }
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                choice[i][j] = add(choice[i - 1][j - 1], choice[i - 1][j]);
            }
        }
        // previous layer need 1, previous layer need 2, current layer need 1, current layer need 2
        int[][][][][] dp = new int[2][n + 1][n + 1][n + 1][n + 1];
        int prevCount = d[0] - 1;
        int curCount = d[1] - 1;
        // initialize with first two nodes. This way, won't have to worry about the 3-insufficiency of the root
        dp[1][prevCount == 1 ? 1 :0][prevCount == 2 ? 1 :0][curCount == 1 ? 1 : 0][curCount == 2 ? 1 : 0] = 1;
        for (int i = 2; i < n; i++) {
            int cur = i % 2;
            int prev = (i + 1) % 2;
            // clear
            for (int j = 0; j <= n; j++) {
                for (int k = 0; k <= n; k++) {
                    for (int l = 0; l <= n; l++) {
                        for (int m = 0; m <= n; m++) {
                            dp[cur][j][k][l][m] = 0;
                        }
                    }
                }
            }
            for (int prev1 = 0; prev1 <= n; prev1++) {
                for (int prev2 = 0; prev2 <= n; prev2++) {
                    for (int cur1 = 0; cur1 <= n; cur1++) {
                        for (int cur2 = 0; cur2 <= n; cur2++) {
                            if (dp[prev][prev1][prev2][cur1][cur2] == 0) continue;
                            // How many previous/current insufficiency will be consumed
                            for (int prev1Use = 0; prev1Use <= 1 && prev1Use <= prev1; prev1Use++) {
                                for (int prev2Use = 1 - prev1Use; prev1Use + prev2Use <= 1 && prev2Use <= prev2; prev2Use++) {
                                    for (int cur1Use = 0; cur1Use <= d[i] - 1 && cur1Use <= cur1; cur1Use++) {
                                        for (int cur2Use = 0; cur1Use + cur2Use <= d[i] - 1 && cur2Use <= cur2; cur2Use++) {
                                            int remain = d[i] - 1 - cur1Use - cur2Use;
                                            int p1 = prev1 - prev1Use + prev2Use;
                                            int p2 = prev2 - prev2Use;
                                            int c1 = cur1 - cur1Use + (remain == 1 ? 1 : 0) + cur2Use;
                                            int c2 = cur2 - cur2Use + (remain == 2 ? 1 : 0);
                                            dp[cur][p1][p2][c1][c2] =
                                                    add(mult(dp[prev][prev1][prev2][cur1][cur2],
                                                            choice[prev1][prev1Use],
                                                            choice[prev2][prev2Use],
                                                            choice[cur1][cur1Use],
                                                            choice[cur2][cur2Use]),
                                                            dp[cur][p1][p2][c1][c2]
                                                    );
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            // move to next layer.
            for (int cur1 = 0; cur1 <= n; cur1++) {
                for (int cur2 = 0; cur2 <= n; cur2++) {
                    if ((cur1 != 0 || cur2 != 0) && dp[cur][0][0][cur1][cur2] != 0) {
                        dp[cur][cur1][cur2][0][0] = add(dp[cur][cur1][cur2][0][0], dp[cur][0][0][cur1][cur2]);
                    }
                }
            }
        }
        System.out.println(dp[(n - 1) % 2][0][0][0][0]);
    }

    // Java Fast IO.
    public static class Scanner {
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
