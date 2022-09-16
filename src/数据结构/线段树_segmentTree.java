package 数据结构;

import java.util.Scanner;

public class 线段树_segmentTree {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int q = sc.nextInt();

        SegmentTree st = new SegmentTree(n);
        for (int i = 0; i < q; i++) {
            int com = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();

            if (com == 0)
                st.update(x, y);
            else
                System.out.println(st._find(x, y));
        }
    }
}

class SegmentTree {
    int[] tree;
    int n;

    SegmentTree(int n) {
        for (this.n = 1; this.n < n; )
            this.n *= 2;

        tree = new int[2 * this.n];

        for (int i = 0; i < 2 * this.n; i++)
            tree[i] = Integer.MAX_VALUE;
    }


    void update(int pos, int x) {
        pos += n;
        tree[pos] = x;
        while (pos > 1) {
            pos /= 2;
            tree[pos] = Math.min(tree[pos * 2], tree[pos * 2 + 1]);
        }
    }


    int find(int a, int b, int k, int l, int r) {
        int l_min, r_min;
        // 区间外
        if (r <= a || b <= l)
            return Integer.MAX_VALUE;
        // 包含
        if (a <= l && r <= b)
            return tree[k];
        else {
            l_min = find(a, b, k * 2, l, (l + r) / 2);
            r_min = find(a, b, k * 2 + 1, (l + r) / 2, r);
            return Math.min(l_min, r_min);
        }
    }

    int _find(int x, int y) {
        return this.find(x, y + 1, 1, 0, n);
    }
}
