//数组实现单向链表
//数组使用泛型（Generic）
//常数时间的操作时间（无循环）
//原始类型int,包装类型Integer
//使用包装类型声明类
//每个基本类型都有一个包装器对象类型
//数组是协变的,泛型是不变的
//Week 2 Stacks && Generic

//public class FixedCapacityStackOfStrings {     //声明Item类型（泛型）
public class FixedCapacityStackOfStrings_Generic<Item> {
//    private String[] s;               //使用泛型
    private Item[] s;
    private int N = 0;

    public FixedCapacityStackOfStrings_Generic(int capacity){       //需要提前给容量是巨大的缺点
//        s = new String[capacity];              //使用泛型
        s = (Item[]) new Object[capacity];      //使用强制类型转换将新建数组设置成item泛型（不能直接声名new Item[capacity]）
    }                                            //尽可能不要在代码中出现强制类型转换
    public boolean isEmpty(){
        return N == 0;
    }
//    public void push(String item){            //使用泛型
    public void push(Item item){
        s[N++] = item;                         //先将item赋值给s[N]，然后再将N+1
    }
//    public String pop(){              //使用泛型
    public Item pop(){
//        String item = s[--N];       //先给N-1，然后再返回s[N]的值
        Item item = s[--N];
        s[N] = null;                //避免对象游离（即不再用已经去掉的String，需要将其内容设置成null）==> 垃圾收集器只有在没有未完成的引用时才能回收内存
        return item;
    }
}
