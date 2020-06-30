////String in Java
//public final class String implements Comparable<String>{
//    private char[] value; // characters
//    private int offset;  // index of first char in array
//    private int length;    // length of string
//    private int hash;       //cache of hashCode()
//    public int length(){
//        return length;
//    }
//    public char charAt(int i){
//        return value[i + offset];
//    }
//    private String(int offset, int length, char[] value){
//        this.offset = offset;
//        this.length = length;
//        this.value = value;
//    }
//    public String substring(int from, int to){
//        return new String(offset + from, to - from, value);   //value : copy of reference to original char array
//    }
////    键索引计数法p455,时间分析p458
//    public void key_indexed_counting(String[] a, int R){
//        int N = a.length;
//        String[] aux = new String[N];
//        int[] count = new int[R + 1];
//        for (int i = 0; i < N; i++){
//            count[a[i].key() + 1]++;
//        }
//        for (int r = 0; r < R; r++){
//            count[r + 1] += count[r];
//        }
//        for (int i = 0; i < N; i++){
//            aux[count[a[i].key()]++] = a[i];
//        }
//        for (int i = 0; i < N; i++){
//            a[i] = aux[i];
//        }
//    }
//}
