//置换
//Week 2 assignment

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args){
        RandomizedQueue<String> test = new RandomizedQueue<String>();
        int showNumber = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()){
            String item = StdIn.readString();
            test.enqueue(item);
        }
        while (showNumber > 0){
            StdOut.print(test.dequeue()+"\n");
            showNumber--;
        }
    }
}