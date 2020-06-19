//反复倍增法创建大小自动调整的数组队列
//运算数量级为N
//Week 2 Resizing Arrays
//均摊复杂度分析：https://www.jianshu.com/p/4721a67a01f2

public class ResizingArrayStackOfStrings {
    private String[] s;             //------8 bytes * array size
    private int N = 0;              //------4 bytes
    public ResizingArrayStackOfStrings(){
        s = new String[1];
    }
    public void push(String item){
        if(N == s.length){
            resize(2 * s.length);       //栈满了就将栈的容量加倍
        }
        s[N++] = item;
    }
    public String pop(){
        String item = s[--N];       //先给N-1，然后再返回s[N]的值
        s[N] = null;                //避免对象游离（即不再用已经去掉的String，需要将其内容设置成null）==> 垃圾收集器只有在没有未完成的引用时才能回收内存
        if(N > 0 && N == s.length/4) resize(s.length/2);  //当栈被填满1/4时，将栈的容量减半 ==>不在1/2时减半是为了避免抖动现象
        return item;
    }
    public void resize(int capacity){
        String[] copy = new String[capacity];
        for (int i = 0;i < N;i++){
            copy[i] = s[i];
        }
        s = copy;
    }
}
