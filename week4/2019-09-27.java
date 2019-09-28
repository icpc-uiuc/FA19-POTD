import java.io.*;
import java.util.*;

public class Main {
    public static class Task {

        public class SegmentTree {
            long[] nodeSum;
            long[] nodePrefixMaxSum;
            int[] nodePrefixMaxSumIndex;
            int n;
            int baseVal;
            public SegmentTree(int n) {
                this.n = 1;
                this.baseVal = 1;
                while (this.baseVal < n) this.baseVal *= 2;
                nodeSum = new long[this.baseVal * 2];
                nodePrefixMaxSum = new long[this.baseVal * 2];
                nodePrefixMaxSumIndex = new int[this.baseVal * 2];
                for (int i = 0; i < n; i++) {
                    nodePrefixMaxSumIndex[i + baseVal] = i + 1;
                }
            }

            public void modify(int index, long val) {
                index += this.baseVal;
                nodeSum[index] += val;
                nodePrefixMaxSum[index] += val;
                while (index != 1) {
                    index /= 2;
                    int leftChild = index << 1;
                    int rightChild = leftChild | 1;
                    nodeSum[index] = nodeSum[leftChild] + nodeSum[rightChild];
                    if (nodePrefixMaxSum[leftChild] < nodeSum[leftChild] + nodePrefixMaxSum[rightChild]) {
                        nodePrefixMaxSum[index] = nodeSum[leftChild] + nodePrefixMaxSum[rightChild];
                        nodePrefixMaxSumIndex[index] = nodePrefixMaxSumIndex[rightChild];
                    } else {
                        nodePrefixMaxSum[index] = nodePrefixMaxSum[leftChild];
                        nodePrefixMaxSumIndex[index] = nodePrefixMaxSumIndex[leftChild];
                    }
                }
            }

            public long[] getMaxPrefixSum(int index) { // return {index, value}
                index += baseVal;
                long bestPrefixSum = nodePrefixMaxSum[index];
                long bestPrefixIndex = nodePrefixMaxSumIndex[index];
                while (index != 1) {
                    int parent = index >> 1;
                    int leftChild = parent << 1;
                    int rightChild = leftChild | 1;
                    if (leftChild == index) {
                        if (nodeSum[leftChild] + nodePrefixMaxSum[rightChild] > bestPrefixSum) {
                            bestPrefixSum = nodeSum[leftChild] + nodePrefixMaxSum[rightChild];
                            bestPrefixIndex = nodePrefixMaxSumIndex[rightChild];
                        }
                    }
                    index = parent;
                }
                return new long[]{bestPrefixIndex, bestPrefixSum};
            }
        }

        public void solve(Scanner sc, PrintWriter pw) throws IOException {
            int n = sc.nextInt();
            int[][] segs = new int[n][3];
            Set<Integer> diff = new HashSet<>();
            for (int i = 0; i < n; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int c = sc.nextInt();
                segs[i][0] = Math.min(x, y);
                segs[i][1] = Math.max(x, y);
                segs[i][2] = c;
                diff.add(x);
                diff.add(y);
            }
            List<Integer> arrDiff = new ArrayList<>(diff);
            Collections.sort(arrDiff);
            Map<Integer, Integer> actualCoordinateToVirtual = new HashMap<>();
            int[] virtualCoordinateNextSpace = new int[arrDiff.size() - 1];
            for (int i = 0; i < arrDiff.size(); i++) {
                actualCoordinateToVirtual.put(arrDiff.get(i), i);
                if (i < arrDiff.size() - 1) virtualCoordinateNextSpace[i] = arrDiff.get(i + 1) - arrDiff.get(i);
            }
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 2; j++) {
                    segs[i][j] = actualCoordinateToVirtual.get(segs[i][j]);
                }
            }

            SegmentTree st = new SegmentTree(arrDiff.size());
            for (int i = 0; i < arrDiff.size() - 1; i++) {
                st.modify(i + 1, -virtualCoordinateNextSpace[i]);
            }
            for (int[] seg : segs) {
                st.modify(seg[1], seg[2]);
            }
            Arrays.sort(segs, new Comparator<int[]>() {
                @Override
                public int compare(int[] t1, int[] t2) {
                    return t1[0] - t2[0];
                }
            });
            int sIdx = 0;
            long maxSol = 0;
            int bestStart = 1_000_000_001, bestEnd = 1_000_000_001;
            for (int i = 0; i < arrDiff.size(); i++) {
                long[] query = st.getMaxPrefixSum(i);
                int rightIndex = (int) query[0];
                long sum = query[1];
                if (sum > maxSol) {
                    maxSol = sum;
                    bestStart = arrDiff.get(i);
                    bestEnd = arrDiff.get(rightIndex - 1);
                }
                while (sIdx < n && segs[sIdx][0] == i) {
                    st.modify(segs[sIdx][1], -segs[sIdx][2]);
                    sIdx++;
                }
                if (i < arrDiff.size() - 1)
                    st.modify(i + 1, virtualCoordinateNextSpace[i]);
            }
            System.out.println(maxSol);
            System.out.print(bestStart + " ");
            System.out.print(bestStart + " ");
            System.out.print(bestEnd + " ");
            System.out.println(bestEnd);
        }
    }

    // template, actual code is in class Task.
    static long TIME_START, TIME_END;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new FileInputStream("Test.in"));
        PrintWriter pw = new PrintWriter(new BufferedOutputStream(System.out));
//        PrintWriter pw = new PrintWriter(new FileOutputStream("Test.out"));

        Runtime runtime = Runtime.getRuntime();
        long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
        TIME_START = System.currentTimeMillis();
        Task t = new Task();
        t.solve(sc, pw);
        TIME_END = System.currentTimeMillis();
        long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();
        pw.close();
//        System.err.println("Memory increased: " + (usedMemoryAfter - usedMemoryBefore) / 1000000);
//        System.err.println("Time used: " + (TIME_END - TIME_START) + ".");
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
