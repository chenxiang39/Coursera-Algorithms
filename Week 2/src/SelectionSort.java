//选择排序
//Week 2 Selection sort
//移动开销最小，线性次数的交换（因此与原数列本身顺序无关）
//N^2级别次的比较和N级别次的交换
//这个排序是不稳定的(有可能把记录越过有相同值的记录)

import edu.princeton.cs.algs4.StdOut;

public class SelectionSort {
    public static void sort(Comparable[] a){
        int N = a.length;
        for(int i = 0; i < N; i++){
            int min = i;
            for(int j = i + 1; j < N;j++){      //先找到最小的，再将最小的和第一个交换；其次找倒数第二小的，与第二个交换；以此类推
                if(less(a[j],a[min])){
                    min = j;
                }
            }
            exch(a,i,min);
        }
    }
    private static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }
    private static void exch(Comparable[] a, int i, int j){
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    public static void main(String[] args){
        SelectionSort a = new SelectionSort();
        Comparable []b = {3,4,5,2,1,7,6,99,88};
        a.sort(b);
        for(int i = 0; i < b.length; i++){
            StdOut.print(b[i]+" ");
        }
    }
}
