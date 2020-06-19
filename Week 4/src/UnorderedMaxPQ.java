//不排序的优先队列实现(可以删除最大元素的队列)
//Week 4 APIs and Elementary Implementations



public class UnorderedMaxPQ <Key extends Comparable<Key>> {
    private Key[] pq;       //pq[i] == "i"th element on pq
    private int N;          // number of elements on pq

    public UnorderedMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key x) {     //时间复杂度为1
        pq[N++] = x;
    }

    public Key delMax() {               //删除最大的元素，时间复杂度为N
        int max = 0;
        for (int i = 1; i < N; i++) {       //查找最大值
            if (less(max, i)) {
                max = i;
            }
        }
        exch(pq, max, N - 1);       //将最大值交换到最后
        return pq[--N];
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
    }
    public static void main(String[] args){

    }
}
