// 二分法检索(Week 1 Order-of-Growth Classification)
// 数组事先已经由小到大排好序
// 平均效率：log2(N)
public class BinarySearch {
    public static int binarySearchImplement(int[] a ,int key){
        int lo = 0,hi = a.length - 1;
        while(lo <= hi){
            int mid = lo + (hi-lo)/2;       //不直接写成(lo + hi)/2是为了防止java整数溢出
            if (key < a[mid]) hi = mid - 1;
            else if(key > a[mid]) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
    public static void main(String[] args){
        int Inte[] ={1,2,3,4,5,6,7,8};
        BinarySearch a = new BinarySearch();
        System.out.println(binarySearchImplement(Inte,2));
        System.out.println(binarySearchImplement(Inte,0));
        System.out.println(binarySearchImplement(Inte,9));
    }
}
