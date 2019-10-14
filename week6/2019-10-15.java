import java.io.*;
import java.util.*;

public class Main {
    public static class Task {
        int[] size = new int[1 << 20];
        boolean[] on = new boolean[1 << 20];

        public int dfs(int at) {
            if (size[at] != -1) return size[at];
            int ret = 0;
            if (on[at]) {
                ret = Integer.bitCount(at);
            } else {
                for (int i = 0; i < 20; i++) {
                    if (((1 << i) & at) != 0) {
                        int res = dfs(at ^ (1 << i));
                        ret = Math.max(res, ret);
                    }
                }
            }
            return size[at] = ret;
        }

        public void solve(Scanner sc, PrintWriter pw) throws IOException {
            String s = sc.next();
            int n = s.length();
            int[] strList = new int[20];
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = s.charAt(i) - 'a';
            }
            for (int i = 0; i < n; i++) {
                int t = 1 << arr[i];
                for (int j = 19; j >= 1; j--) {
                    int original = strList[j - 1];
                    if (original != 0 && (original & t) == 0) {
                        strList[j] = original | t;
                        on[strList[j]] = true;
                    } else {
                        strList[j] = 0;
                    }
                }
                strList[0] = t;
                on[t] = true;
            }
            on[0] = false;
            Arrays.fill(size, -1);
            size[0] = 0;
            int best = 0;
            for (int i = 0; i < 1 << 20; i++) {
                if (on[i]) {
                    int other = dfs((1 << 20) - 1 - i);
                    best = Math.max(best, Integer.bitCount(i) + other);
                }
            }
            pw.println(best);

        }
    }

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

