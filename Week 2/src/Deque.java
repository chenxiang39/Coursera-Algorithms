//双端队列
//Week 2 assignment

import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first;  //头节点，是第一个元素
    private Node last;   //尾节点，是最后一个元素
    private int size;    //节点数

    // construct an empty deque
    public Deque(){
        first = null;
        last = null;
        size = 0;
    }
    private class Node{
        Item item;      //节点内容
        Node next;      //后一个节点
        Node prev;      //前一个节点
    }

    private class DequeIt implements Iterator<Item>{
        private Node current = first;
        public boolean hasNext(){
            return current != null;
        }
        public void remove(){
            throw new UnsupportedOperationException("unsupported operation!");
        }
        public Item next(){
            if(!hasNext()) {
                throw new NoSuchElementException("no more items!");         //iterator的判断空指针
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
    // is the deque empty?
    public boolean isEmpty(){
        return size == 0;
    }
    private void validateNullInput(Item item){              //需要private
        if (item == null) throw new IllegalArgumentException("input the null argument!");
    }
    private void validateNoSuch(){           //需要private
        if(isEmpty()) throw new NoSuchElementException("no more items!");
    }
    // return the number of items on the deque
    public int size(){
        return size;
    }

    // add the item to the front
    public void addFirst(Item item){
        validateNullInput(item);
        Node newItem = new Node();
        newItem.item = item;
        Node oldFirst = first;
        first = newItem;
        if(isEmpty()){
            last = first;
        }
        else {
            first.next = oldFirst;
            oldFirst.prev = first;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item){
        validateNullInput(item);
        Node newItem = new Node();
        newItem.item = item;
        Node oldlast = last;
        last = newItem;
        if(isEmpty()){
            first = last;
        }
        else {
            oldlast.next = last;
            last.prev = oldlast;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst(){
        validateNoSuch();
        size--;
        Node resultNode = first;
        first = first.next;
        if(first != null){               //防止空指针.prev的错误
            first.prev = null;
        }
        else{
            last = first;                //全部remove完时将last指针置空
        }
        return resultNode.item;
    }

    // remove and return the item from the back
    public Item removeLast(){
        validateNoSuch();
        size--;
        Node resultNode = last;
        last = last.prev;
        if(last != null){               //防止空指针.next的错误
            last.next = null;
        }
        else {
            first = last;               //全部remove完时将first指针置空
        }
        return resultNode.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
        return new DequeIt();
    }

    // unit testing (required)
    public static void main(String[] args){
        Deque<Double> test = new Deque<Double>();
        test.addFirst(0.1);
        test.addLast(0.2);
        test.removeLast();
        StdOut.print(test.size()+"\n");
        for (Object s : test){
            StdOut.print(s+" ");
        }
    }

}