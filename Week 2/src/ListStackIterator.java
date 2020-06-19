//链表堆栈迭代器
//Week 2 Iterators
//Iterable => 有一个方法能返回一个iterator
//Java中的iterator中包含hasNext(),next(),remove()方法,其中remove()方法不推荐使用，会产生调试隐患.
//Java中LinkedList实现(自带方法)访问给定索引的元素需要线性时间，而不是数组中的常数时间
//编译器实现函数的方式是使用栈
//当有函数被调用时，整个局部环境和返回地址入栈,之后函数返回时返回地址和环境变量出栈,有个栈包含全部的信息,无论函数调用的是否是它本身,这都无关。
//数学表达式中,一个堆栈放操作数，一个堆栈放操作符
import java.util.Iterator;
public class ListStackIterator<Item> implements Iterable<Item>{
    public class Node{
        Item item;
        Node next;
    }
    private Node first = null;
    public Iterator<Item> iterator(){
        return new ListStackIt();
    }
    private class ListStackIt implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){
            return current != null;
        }
        public void remove(){
            // not supported;
        }
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}
