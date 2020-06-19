//希尔排序
//Week 2 Shellsort
//g-有序的数组经过h-排序后依然是g-有序的（g > h）=>数组经过7-sort排序后，再经过3-sort排序后,此数组依然符合7-sorted.
//对于3x+1的增量序列最坏情况下比较的次数是N^3/2级别
//这个排序是不稳定的（有长距离交换）
import edu.princeton.cs.algs4.StdOut;

public class ShellSort {
    public static void sort(Comparable[] a){
        int N = a.length;
        int h = 1;
        while(h < N/3){
            h = h * 3 + 1;   //3X + 1的增量顺序   ..直接将h值设置到最大
        }
        while(h >= 1){
            // h-sort the array
            for(int i = h; i < N; i++){            // 插入排序
                for(int j = i; j >= h; j = j - h){
                    if(less(a[j],a[j - h])){
                        exch(a, j, j - h);
                    }
                }
            }
            h = h / 3;
        }
    }
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    private static void exch(Comparable []a,int i,int j){
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    public static void main(String[] args){
        ShellSort a = new ShellSort();
        Comparable []b = {3,4,5,2,1,7,6,99,88};
        a.sort(b);
        for(int i = 0; i < b.length; i++){
            StdOut.print(b[i]+" ");
        }
    }
}
