//快速排序
//Week 3 QuickSort
//可以不用额外的空间，比归并排序更快
//最好的情况，将所有的东西(第一个元素总是处于要排列的数组的中间大小)精确分成两半   ~ N * log2(N)
//最坏的情况，打乱后已经将所有项都按顺序排好（会直接划分最小项），但经过随机排序后概率极低 ~ 1/2 * N^2
//平均时间复杂度为N * log2(N)
//这个算法是不稳定的(因为partition这一步可能会做长距离交换)
//处理重复元素时要平方次时间(但可以改进)(三路快排)


import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class QuickSort {
    private static int partition(Comparable[] a, int lo, int hi){       //根据第一个元素的大小对之后的元素进行分区(一边都比第一个元素小，另一边都比第一个元素大)
        int i = lo;         //第一个元素，标杆元素
        int j = hi + 1;
        while(true){        //break退出死循环
            while (less(a[++i], a[lo])){        //find item on left to swap
                if (i == hi)                    //防止指针跑出右端
                    break;
            }
            while (less(a[lo], a[--j])){        //find item on right to swap
                if (j == lo)                    //防止指针跑出左端
                    break;
            }
            if(i >= j)                          //check if pointers cross swap
                break;
            exch(a , i , j);
        }
        exch(a , lo , j);   //将头元素交换到正确的位置（j指针所在位置）
        return j;           //返回之前头元素所在的正确位置
    }
    public static Comparable select(Comparable[] a, int k){         //从数组a中选出第k大的数  (平均复杂度为n级别)
        StdRandom.shuffle(a);                                       //最差的复杂度为 ～ 0.5 * N^2级别（已经按顺序排序好），但随机打乱提供了概率性保证
        int lo = 0, hi = a.length - 1;
        while(hi > lo){
            int j = partition(a, lo, hi);
            if (j < k){
                lo = j + 1;
            }
            if (j > k){
                hi = j - 1;
            }
            else{
                return a[k];
            }
        }
        return a[k];
    }
    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);           //打乱原数组，使得分块以后的数组也是随机的,概率性地保证不出现最差情况
        sort(a, 0, a.length - 1);
    }
    private static void sort(Comparable[] a, int lo, int hi){
//        if( hi <= lo + CUTOFF - 1){                       //在处理小数组时，使用插入排序速度更快,CUTOFF大概在10左右
//            Insertion.sort(a, lo, hi);
//            return;
//        }
        if (hi <= lo)
            return;
//        int m = medianOf3(a, lo, lo + (hi - lo)/2, hi);   //通过取样本的中位数当成整个数组的第一个元素（标杆元素），使左右划分更加均衡（元素多的数组不值得用此方法）
//        exch(a, lo, m);

//        int lt = lo, gt = hi;              //三路划分快速排序（优化重复元素的排序问题）=> 将小于某个值的数全部放在左边，等于某个值的数全部放在中间，大于某个值的数全部放在右边，详情：Week 3 Duplicate Keys
//        Comparable v = a[lo];              //三个指针(i,lt,gt) lt左边的都比lt所指的小, gt右边的都比gt所指的大,i负责扫描是否有元素与lt所指的元素相同
//        int i = lo;
//        while (i <= gt){
//            int cmp = a[i].compareTo(v);
//            if(cmp < 0){
//                exch(a, lt++, i++);
//            }
//            else if (cmp > 0){
//                exch(a, i, gt--);
//            }
//            else {
//                i++;
//            }
//        }
//        sort(a, lo, lt - 1);          //按块排序，it ~ gt都是大小相同的键且顺序处于正确的位置（左边比其小，右边比其大）
//        sort(a, gt + 1, hi);
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);       //左部分继续做相同的排序
        sort(a, j+1, hi);       //右部分继续做相同的排序
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
        Comparable []b = {88,4,95,88,1,7,6,99,88,66};
        sort(b);
        for (Comparable comparable : b) {
            StdOut.print(comparable + " ");
        }
    }
}
