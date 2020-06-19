//队列
//先进先出(FIFO)
//Week 2 Queues
//优秀的模块化编程的指导原则就是我们应当欢迎编译时错误，避免运行时错误。

public class QueueOfStrings {
    private Node first ;
    private Node last ;
    private class Node {
        String item;
        Node next;
    }
    public void enqueue(String item){       //进队列（last指针向后）
        Node oldlast = last;
        Node last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty())
            first = last;
        else
            oldlast.next = last;
    }

    public String dequeue(){           //出队列（first指针向后）
        String item = first.item;
        first = first.next;
        if(isEmpty())
            last = null;
        return item;
    }

    boolean isEmpty(){
        return first == null;
    }
}
