import java.util.*;
import java.io.*;


public class Main {

    public static boolean compareAB(int[] A, int[] B) {
        return Arrays.equals(shuffleSort(Arrays.copyOf(A, A.length)), shuffleSort(Arrays.copyOf(B, B.length)));
    }

    public static int[] shuffleSort(int[] x) {
        Random rnd = new Random();
        int n = x.length;
        for (int i = 0; i < n; i++) {
            int r = rnd.nextInt(n - i) + i;
            int tmp = x[i];
            x[i] = x[r];
            x[r] = tmp;
        }
        Arrays.sort(x);
        return x;
    }

    public static class SegmentTree {
        int[] nodes;
        int n;
        int half;
        public SegmentTree(int n) {
            this.n = n;
            half = 1;
            while (half < n) half *= 2;
            nodes = new int[half * 2];
        }
        public void update(int i, int x) {
            nodes[i += half] += x;
            while (i != 1) {
                int root = i / 2;
                int left = root << 1;
                int right = left | 1;
                nodes[root] = Math.min(nodes[left], nodes[right]);
                i = root;
            }
        }

        public int queryMin(int idx, int l, int r, int L, int R) {
            if (r <= L || l >= R) return Integer.MAX_VALUE;
            if (L <= l && r <= R) return nodes[idx];
            return Math.min(queryMin(idx << 1, l, (l + r) / 2, L, R),
                    queryMin(idx << 1 | 1, (l + r) / 2, r, L, R));
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(System.out));
        int T = sc.nextInt();
        outer:
        for (int _ = 0; _ < T; _++) {
            int n = sc.nextInt();
            int[] A = new int[n];
            int[] B = new int[n];
            for (int i = 0; i < n; i++) {
                A[i] = sc.nextInt() - 1;
            }
            for (int i = 0; i < n; i++) {
                B[i] = sc.nextInt() - 1;
            }
            if (!compareAB(A, B)) {
                pw.println("NO");
                continue;
            }
            SegmentTree st = new SegmentTree(n);
            for (int i = 0; i < n; i++) {
                st.update(i, A[i]);
            }
            Map<Integer, List<Integer>> stack = new HashMap<>();
            for (int i = n - 1; i >= 0; i--) {
                int a = A[i];
                if (!stack.containsKey(a)) {
                    List<Integer> arr = new ArrayList<>();
                    arr.add(i);
                    stack.put(a, arr);
                } else {
                    stack.get(a).add(i);
                }
            }
            for (int b: B) {
                List<Integer> arr = stack.get(b);
                int stackTop = arr.get(arr.size() - 1);
                int stackMinimum = st.queryMin(1, 0, st.half, 0, stackTop + 1);
                boolean isMinimum = b == stackMinimum;
                if (!isMinimum) {
                    pw.println("NO");
                    continue outer;
                } else {
                    arr.remove(arr.size() - 1);
                    st.update(stackTop, Integer.MAX_VALUE / 2);
                }
            }
            pw.println("YES");
        }
        pw.close();
    }

    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s){  br = new BufferedReader(new InputStreamReader(s));}

        public Scanner(FileReader s) throws FileNotFoundException {br = new BufferedReader(s);}

        public String next() throws IOException
        {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {return Integer.parseInt(next());}

        public long nextLong() throws IOException {return Long.parseLong(next());}

        public String nextLine() throws IOException {return br.readLine();}

        public double nextDouble() throws IOException { return Double.parseDouble(next()); }

        public boolean ready() throws IOException {return br.ready();}
    }
}

