//堆排序
//Week 4 Heapsort
//Nlog2(N)级别复杂度，不稳定，最坏情况也是Nlog2(N),无需额外空间（此处用了辅助数组只是为了方便数组下标的转换(从1开始)））
//算法复杂度参考：https://blog.csdn.net/zhangxiaoyu_sy/article/details/75000151
//内部循环比快速排序多
//此算法不稳定，对缓存的使用不好，容易浪费缓存
//堆的构建是线性时间（<= 2 * N ）次比较和交换
//堆排序是使用了（<= 2 * N * log2(N)）次比较和交换
import edu.princeton.cs.algs4.StdOut;


public class HeapSort {
    public static void sort(Comparable[] pq){
        Comparable[] auxpq = ChangeArray(pq);       //初始化辅助数组让数组的初始下标改变，方便运算
        int N = auxpq.length - 1;       //辅助数组第一个元素是null，故size多了一个所以这里要减1表示元素个数
        for (int k = N/2; k >= 1; k--){         //初始化时将堆（任意排序的数组集合）变成最大二叉堆(自底向上修正顺序)
            sink(auxpq, k, N);                  //不停地执行下沉操作（将一个个父亲节点确定）
        }
        while (N > 1){
            exch(auxpq, 1, N);     //将堆中的根节点（即值最大的点设置与最后一个点进行交换），需要输出最大值故需要交换）
            sink(auxpq, 1, --N);    //下沉之前交换的根结点并将元素总数（N）-1
        }
        for(int i = 0; i < pq.length; i++){         //将辅助数组赋值原数组
            pq[i] = auxpq[i + 1];
        }
    }
    private static void sink(Comparable[] pq,int k, int N){    //下沉操作
        while ( 2 * k <= N){
            int j = 2 * k;
            if( j < N && less(pq, j,j + 1)){
                j++;
            }
            if(!less(pq,k,j)){
                break;
            }
            exch(pq, k,j);
            k = j;
        }
    }
    private static boolean less(Comparable[] pq, int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }
    private static void exch(Comparable[] pq, int i, int j){
        Comparable aux = pq[i];
        pq[i] = pq[j];
        pq[j] = aux;
    }
    public static Comparable[] ChangeArray(Comparable[] pq){        //将数组的下标从0开始改成从1开始
        Comparable[] auxpq = new Comparable[pq.length + 1];
        auxpq[0] = null;
        for(int i = 0; i < pq.length; i++){
            auxpq[i + 1] = pq[i];
        }
        return auxpq;
    }
    public static void main(String[] args){
        Comparable []b = {3,4,5,2,1,7,6,99,88,99,7};
        sort(b);
        for(int i = 0; i < b.length; i++){
            StdOut.print(b[i]+" ");
        }
    }
}
