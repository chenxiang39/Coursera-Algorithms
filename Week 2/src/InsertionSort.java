//插入排序
//Week 2 Insertion Sort
//双指针，i的右边为未知的内容，左边为排序好的数组；j负责将i及i之前的数组按升序排列(不断交换使i之前的数列保持有序)
//最好的情况是已经升序排好的数组，只要比较N - 1次且不进行任何交换(对于元素数量小的数组使用插入排序是一个好的选择)
//最坏的情况是降序排列好的数组，要比较1/2*N^2级别次并且进行1/2*N^2级别次的交换
//对部分有序的数组是线性时间开销（部分有序的概念是逆序对的数量小于等于常数*N）=>交换的次数与逆序对的次数相等（除了第一次）
//这个排序是稳定的(从不越过相同值的记录)

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class InsertionSort {
    public static void sort(Comparable[] a){
        int N = a.length;
        for(int i = 0; i < N; i++){
            for(int j = i; j > 0;j--){
                if(less(a[j],a[j-1])){          //如果后一个比前一个小，则交换（直到后一个比前一个大或相等则停止交换）（升序排列）
                    exch(a,j,j-1);
                }
                else break;
            }
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
        InsertionSort a = new InsertionSort();
        Comparable []b = {3,4,5,2,1,7,6,99,88};
        sort(b);
        for (Comparable comparable : b) {
            StdOut.print(comparable + " ");
        }
    }
}
