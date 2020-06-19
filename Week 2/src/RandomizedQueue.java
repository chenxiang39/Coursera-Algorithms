//随机队列
//Week 2 assignment

//Q1:
//Parallel iterators means that two threads at the same time iterate over same queue.
//The idea is that at the same time there are two different users of same queue, hence they run in parallel.

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] QueueArr;           //队列数组
    private int size;               //队列元素数量
    // construct an empty randomized queue
    public RandomizedQueue(){
        QueueArr = (Item[])new Object[1];
        size = 0;

    }
    private void validateNullInput(Item item){       //需要private
        if (item == null) throw new IllegalArgumentException("input the null argument!");
    }
    private void validateNoSuch(){       //需要private
        if(isEmpty()) throw new NoSuchElementException("no more items!");
    }
    private class RandomizedQueueIt implements Iterator<Item>{
        private int currentSize;
        private Item[] currentQueueArr = (Item[]) new Object[size];
        RandomizedQueueIt(){
            currentSize = size;
            for(int i = 0; i < size ; i++){
                 currentQueueArr[i] = QueueArr[i];          //拷贝数组内容（非地址），不能直接等于(currentQueueArr = QueueArr)(引用相同的地址导致一个数组改变会引起另一个数组改变),且过滤掉原来数组中含null的部分
            }
            StdRandom.shuffle(currentQueueArr);         //在调用iterator时将数组打乱来解决Q1(每次调用Iterator时改变数组顺序)
        }
        public void remove(){
            throw new UnsupportedOperationException("unsupported operation!");
        }
        public boolean hasNext(){
            return currentSize > 0 ;
        }
        public Item next(){
            if(!hasNext()) {
                throw new NoSuchElementException("no more items!");         //iterator的判断空数组
            }
            return currentQueueArr[--currentSize];
        }
    }
    private void resize(int capacity){       //需要private
        Item[] copy = (Item[])new Object[capacity];
        for(int i = 0;i < size;i++){
            copy[i] = QueueArr[i];
        }
        QueueArr = copy;
    }

    // is the randomized queue empty?
    public boolean isEmpty(){
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size(){
        return size;
    }

    // add the item
    public void enqueue(Item item){
        validateNullInput(item);
        if(size == QueueArr.length){
            resize(QueueArr.length * 2);
        }
        QueueArr[size++] = item;
    }

    // remove and return a random item
    public Item dequeue(){
        validateNoSuch();
        if (size == QueueArr.length/4){
            resize(QueueArr.length/2);
        }
        int needRemoveIndex = StdRandom.uniform(0,size);
        Item needRemoveItem = QueueArr[needRemoveIndex];
//        for(int i = needRemoveIndex ; i < size - 1; i++){
//            QueueArr[i] = QueueArr[i + 1];                        //废弃方法,消耗时间过长
//        }
        if (needRemoveIndex != size - 1){
            QueueArr[needRemoveIndex] = QueueArr[size - 1];             //将末尾的数组元素移到被删元素的位置,节约时间
        }
        QueueArr[size - 1] = null;              //释放空间
        size--;
        return needRemoveItem;
    }

    // return a random item (but do not remove it)
    public Item sample(){
        validateNoSuch();
        int needReturnIndex = StdRandom.uniform(0,size);
        Item needReturnItem = QueueArr[needReturnIndex];
        return needReturnItem;
    }
    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
        return new RandomizedQueueIt();
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<String> test = new RandomizedQueue<String>();
        test.enqueue("a");
        test.enqueue("b");
        test.enqueue("c");
        test.enqueue("d");
        test.enqueue("e");
        test.enqueue("f");
        StdOut.print("sample : " + test.sample() + "\n");
        StdOut.print("dequeue : " + test.dequeue() + "\n");
        StdOut.print("size : " + test.size() + "\n");
        for (Object s : test) {
            if (s != null)
                StdOut.print(s + " ");
        }
        StdOut.print("\n");
        for (Object z : test) {
            if (z != null)
                StdOut.print(z + " ");
        }
    }
}