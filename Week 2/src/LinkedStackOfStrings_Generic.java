//单项链表
//后进先出(LIFO)
//链表使用泛型（Generic）<==> 数组直接声明Item类型
//常数时间的操作时间（无循环）
//Week 2 Stacks && Generic

//public class LinkedStackOfStrings {
public class LinkedStackOfStrings_Generic<Item> { //声明Item类型（泛型）
    private Node first = null;
    private class Node{                 //inner class
        //String item;
        Item item;
        Node next;
    }
//    public String pop(){              //使用泛型
    public Item pop(){                  //出栈(first指针向后)
//        String item = first.item;      //使用泛型
        Item item = first.item;
        first = first.next;
        return item;
    }
//    public void push(String item){    //使用泛型
    public void push(Item item){         //进栈(first指针向前)
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
    }
    public boolean isEmpty(){
        return first == null;
    }
    public static void main(String[] args){

    }
};

