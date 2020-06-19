//归并排序
//Week 3 mergesort
//需要至多N * log2(N)次比较和6 * N * log2(N)次数组访问
//需要额外的空间
//这个算法是稳定的（即使键值相等的情况下，归并排序也会取左边的值以保证排序的稳定）
//处理重复元素时经常需要比较1/2 * N * log2(N) ~ N * log2(N) 次
//最好和最坏的情况时间复杂度都为N * log2(N)

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class MergeSort {
    private static Comparable[] aux;
    private static ArrayList<Comparable> repeatPointArr = new ArrayList<Comparable>();   //数组中重复的点（分为一组）(实例化以避免空指针异常)
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi){   //aux means auxiliary array

//        assert isSorted(a, lo, mid);        // precondition: a[lo..mid] sorted, 不要用于外部参数的检查！
//        assert isSorted(a, mid + 1, hi);    // precondition: a[mid + 1..hi] sorted, 不要用于外部参数的检查！

        int i = lo, j = mid + 1;     //初始时，i指向于前半部分的开始，j指向后半部分的开始
        for(int k = lo; k <= hi ; k++){
            aux[k] = a[k];                      //copy the array
        }
        for(int k = lo; k <= hi ; k++){
            if (i > mid){           //前半部分没元素了（走完了）
                a[k] = aux[j++];    //只进后半部分的元素
            }
            else if (j > hi){       //后半部分没元素了（走完了）
                a[k] = aux[i++];    //只进前半部分的元素
            }
            else if(less(aux[j], aux[i])){      //谁小先进排好序的数组
                a[k] = aux[j++];
            }
            else{
                if(aux[j] == aux[i]){
                    if(!repeatPointArr.contains(aux[j])){
                        repeatPointArr.add(aux[j]);
                    }
                }
                a[k] = aux[i++];
            }
        }
//        assert isSorted(a, lo, hi);       // postcondition: a[lo..hi] sorted, 不要用于外部参数的检查！
    }
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi){
        //此处不要创建辅助数组！因为那会多出许多额外的小数组的花费(递归中不要创建数组)
        if (hi <= lo)
            return;
//      if(hi <= lo + CUTOFF - 1){                //进行切分对小于某个数值的小数组采用插入排序(小数量数组使用归并排序太复杂，还会生成很多小数组，归并调用时也会产生开销)
//            Insertion.sort(a, lo, hi);
//            return;
//      }
        int mid = lo + (hi - lo) / 2;
        sort(a, aux,lo, mid);       //层层递归
        sort(a,aux,mid + 1, hi);
//         if (!less(a[mid + 1], a[mid])){          //如果前一半的最大数比后一半的最小数小(两部分都已经排序好)，则跳过合并（每个归并前检查，线性复杂度）
//            return;
//         }
        merge(a, aux, lo, mid, hi);     //先归并最左边的两个
    }
    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a, aux, 0, a.length - 1);
    }
    public int Get_repeatPointGroupAmount(){
        return repeatPointArr.size();
    }
    private static boolean less(Comparable v,Comparable w){
        return v.compareTo(w)<0;
    }
    public static void main(String[] args){
        MergeSort a = new MergeSort();
        Comparable []b = {88,4,99,88,1,7,6,99,88};
        a.sort(b);
        for(int i = 0; i < b.length; i++){
            StdOut.print(b[i]+" ");
        }
        StdOut.print("\n" + a.Get_repeatPointGroupAmount());
    }
}
