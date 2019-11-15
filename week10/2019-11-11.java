//package com.company;
 
import java.io.*;
import java.util.*;
 
public class Main {
    public static class Task {
        public class DisjointSet {
            int[] a;
 
            public DisjointSet(int n) {
                a = new int[n];
                Arrays.fill(a, -1);
            }
 
            public int find(int x) {
                if (a[x] < 0) return x;
                return a[x] = find(a[x]);
            }
 
            public boolean union(int x, int y) {
                int rx = find(x), ry = find(y);
                if (rx == ry) return false;
                a[rx] = ry;
                return true;
            }
        }
 
        public void solve(Scanner sc, PrintWriter pw) throws IOException {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int extra = 0;
            DisjointSet djs = new DisjointSet(n);
            for (int i = 0; i < m; i++) {
                int a = sc.nextInt() - 1;
                int b = sc.nextInt() - 1;
                if (!djs.union(a, b)) extra++;
            }
            pw.println(extra);
        }
    }
 
    static long TIME_START, TIME_END;
 
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new FileInputStream("input"));
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(System.out));
//        PrintWriter pw = new PrintWriter(new FileOutputStream("input"));
 
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
