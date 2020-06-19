//数组堆栈迭代器
//Week 2 Iterators
//Iterable => 有一个方法能返回一个iterator
//Java中的iterator中包含hasNext(),next(),remove()方法,其中remove()方法不推荐使用，会产生调试隐患.

import java.util.Iterator;
public class ArrayStackIterator<Item> implements Iterable<Item>{
    private int N = 0;
    private Item[] s;
    public Iterator<Item> iterator(){
        return new ArrayStackIt();
    }
    private class ArrayStackIt implements Iterator<Item>{
        private int i = N;
        public boolean hasNext(){
            return i > 0;
        }
        public void remove(){
            //not supported;
        }
        public Item next(){
            return s[--i]; //先给i-1，再调用i的值
        }
    }
}
