import java.util.*;

public class Main {
    public static void main(String[] args) {
        // read in input
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt() - 1;
        }
        for (int i = 0; i < n; i++) {
            b[i] = sc.nextInt() - 1;
        }
        // when iterating from 1 ... n, what elements are in A not in B, and vice versa
        Set<Integer> aExtra = new HashSet<>();
        Set<Integer> bExtra = new HashSet<>();
        // indexs where aExtra.size() == (bElem.bExtra() ==) 0
        List<Integer> pivots = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int aElem = a[i], bElem = b[i];
            if (bExtra.contains(aElem)) bExtra.remove(aElem);
            else aExtra.add(aElem);
            if (aExtra.contains(bElem)) aExtra.remove(bElem);
            else bExtra.add(bElem);
            if (aExtra.size() == 0 && bExtra.size() == 0) {
                pivots.add(i + 1);
            }
        }
        if (pivots.size() >= m) {
            System.out.println("YES");
            // resulting string that's sorted.
            char[] A = new char[n];
            int idx = 0;
            int startingChar = 'a';
            for (int pivot : pivots) {
                while (idx < pivot) {
                    A[idx++] = (char) startingChar;
                }
                if (startingChar < 'z') startingChar += 1;
            }
            // reconstruct the string by a.
            char[] s = new char[n];
            for (int i = 0; i < n; i++) {
                s[a[i]] = A[i];
            }
            StringBuilder sb = new StringBuilder();
            for (char c : s) sb.append(c);
            System.out.println(sb.toString());
        } else {
            System.out.println("NO");
        }
    }
}
