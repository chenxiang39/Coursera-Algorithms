//比较器（可根据关键字进行比较）
//Week 3 Comparators
//Comparable数组默认已经实现泛型（Week 2 Sorting Introduction）

import edu.princeton.cs.algs4.StdOut;

import java.util.Comparator;

public class ComparatorEX {
        private static final Comparator<ComparatorEX> BY_Name = new ByName();
        private static final Comparator<ComparatorEX> BY_SECTION = new BySection();
        private String name;
        private int section;
        ComparatorEX(String name, int section){
            this.name = name;
            this.section = section;
        }
        public static void sort(ComparatorEX[] a, Comparator comparator){
            int N = a.length;
            for (int i = 0; i < N; i++){
                for(int j = i; j > 0 && less(comparator, a[j], a[j - 1]); j--){
                    exch(a, j , j - 1);
                }
            }
        }

        private static boolean less(Comparator c, ComparatorEX v, ComparatorEX w){
            return c.compare(v,w) < 0 ;
        }

        private static void exch(Object[] a, int i, int j){
            Object swap = a[i];
            a[i] = a[j];
            a[j] = swap;
        }
        private static class ByName implements Comparator<ComparatorEX>{
            public int compare(ComparatorEX v, ComparatorEX w){
                return v.name.compareTo(w.name);
            }
        }
        private static class BySection implements Comparator<ComparatorEX>{
            public int compare(ComparatorEX v, ComparatorEX w){
                return v.section - w.section;
            }
        }
        public static void main(String[] args){
            ComparatorEX a = new ComparatorEX("d", 22);
            ComparatorEX b = new ComparatorEX("b", 33);
            ComparatorEX c = new ComparatorEX("c", 55);
            ComparatorEX []arr = {a , b , c};
            sort(arr, BY_Name);
            for(int i = 0; i < arr.length ; i ++){
                StdOut.print(arr[i].name+"\n");
            }
        }
}
