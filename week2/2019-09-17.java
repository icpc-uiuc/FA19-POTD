public class Main {
    public static class RMQ {
        int n;
        int[] a;
        int[][] sparse;
        public RMQ(int[] a) {
            this.a = Arrays.copyOf(a, a.length);
            this.n = a.length;
            this.sparse = new int[20][n];
            for (int i = 0; i < n; i++) {
                sparse[0][i] = a[i];
            }
            for (int i = 1; i < 20; i++) {
                for (int j = 0; j < n; j++) {
                    sparse[i][j] = Math.max(sparse[i - 1][j], sparse[i - 1][Math.min(n - 1, j + (1 << (i - 1)))]);
                }
            }
        }
        public int query(int a, int b) { // [a, b]
            int numbers = b - a + 1;
            int pow2 = 0;
            while ((1 << pow2) <= numbers) pow2++;
            pow2--;
            return Math.max(sparse[pow2][a], sparse[pow2][b + 1 - (1 << pow2)]);
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        if (n == 1) {
            System.out.println(a[0]);
            return;
        }
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            if (i == 0) b[i] = Math.max(a[i], a[i + 1]);
            else if (i == n - 1) b[i] = Math.max(a[i], a[i - 1]);
            else b[i] = Math.min(a[i], Math.max(a[i - 1], a[i + 1]));
        }
        RMQ A = new RMQ(a);
        RMQ B = new RMQ(b);
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k <= n - 1; k++) {
            if (n - k == 1) {
                sb.append(A.query(0, n - 1));
            } else if ((n - k) % 2 == 0) {
                int minimum = (n - k - 1) / 2;
                int maximum = (n + k) / 2;
                int sol = A.query(minimum, maximum);
                sb.append(sol).append(" ");
            } else {
                int minimum = (n - k) / 2;
                int maximum = (n + k) / 2;
                int sol = B.query(minimum, maximum);
                sb.append(sol).append(" ");
            }
        }
        System.out.println(sb.toString());
    }
}
