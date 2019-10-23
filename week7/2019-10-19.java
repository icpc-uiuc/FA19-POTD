import java.io.*;
import java.util.*;
 
public class Main {
    public static class Task {
 
        public void swap(int i, int j, int[] aa) {
            int t = aa[i];
            aa[i] = aa[j];
            aa[j] = t;
        }
 
        public void shift(int idx, int[] aa, int shift) {
            int shiftedTo = idx - shift;
            aa[shiftedTo] = aa[idx];
            for (int i = shiftedTo + 1; i <= idx; i++) {
                aa[i]--;
            }
        }
 
        public void solve(Scanner sc, PrintWriter pw) throws IOException {
            int n = sc.nextInt();
            long k = sc.nextLong();
            long minimum = (long) (n + 1) * n / 2;
            long maxShift;
            if (n % 2 == 0) {
                maxShift = (long) (n - 1 + 1) * n / 2 / 2;
            } else {
                maxShift = (long) (n - 1 + 2) * (n / 2) / 2;
            }
            if (k < minimum) {
                pw.println(-1);
                return;
            } else {
                k = Math.min(minimum + maxShift, k);
            }
            long firstShift = n - 1;
            long tmpK = k - minimum;
            int idx = 0;
            int[] second = new int[n];
            for (int i = 0; i < n; i++) {
                second[i] = i;
            }
            while (tmpK > 0) {
                if (tmpK >= firstShift) {
                    tmpK -= firstShift;
                    firstShift -= 2;
                    swap(idx, n - 1 - idx, second);
                    idx++;
                } else {
                    shift(n - 1 - idx, second, (int) tmpK);
                    break;
                }
            }
            pw.println(k);
            for (int i = 0; i < n; i++) {
                pw.print(i + 1 + " ");
            }
            pw.println();
            for (int i = 0; i < n; i++) {
                pw.print(second[i] + 1 + " ");
            }
            pw.println();
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
