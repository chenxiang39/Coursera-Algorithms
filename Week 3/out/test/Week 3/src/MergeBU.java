//自底向上的归并排序
//Week 3 Bottom-up Mergesort
//自底向上的归并排序时间复杂度为log2(N),而每一轮需要进行N次比较，因此总复杂度为N * log2(N)
import edu.princeton.cs.algs4.StdOut;

public class MergeBU {
    private static Comparable[] aux;
    private static void merge(Comparable[] a, int lo, int mid, int hi){
        int i = lo;
        int j = mid + 1;
        for(int k = lo; k <= hi ; k++){
            aux[k] = a[k];                      //copy the array
        }
        for(int k = lo; k <= hi ; k++){
            if(i > mid){
                a[k] = aux[j++];
            }
            else if(j > hi){
                a[k] = aux[i++];
            }
            else if(less(aux[j],aux[i])){
                a[k] = aux[j++];
            }
            else{
                a[k] = aux[i++];
            }
        }
    }
    private static boolean less(Comparable v, Comparable w){
        return v.compareTo(w) < 0;
    }
    public static void sort(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N];
        for(int merge_size = 1; merge_size < N; merge_size = 2 * merge_size){          //时间复杂度N * log2(N)
            for(int lo = 0; lo < N - merge_size; lo += 2 * merge_size){
                merge(a, lo, lo + merge_size - 1, Math.min(lo + 2 * merge_size - 1, N - 1));
            }
        }
    }
    public static void main(String[] args){
        Comparable []b = {3,4,5,2,1,7,6,99,88};
        sort(b);
        for(int i = 0; i < b.length; i++){
            StdOut.print(b[i]+" ");
        }
    }
}
