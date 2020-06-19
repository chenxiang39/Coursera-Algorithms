//最小值优先队列的思想
//Week 4 APIs and Elementary implementations
//保留队列前M个最大值

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Transaction;

public class MinPQ_thought {
//    MinPQ<Transaction> pq = new MinPQ<Transaction>();
//    while (StdIn.hasNextLine()){
//        String line = StdIn.readLine();
//        Transaction item = new Transaction(line);
//        pq.insert(item);
//        if (pq.size() > M){               //当pq的大小大于M时，删除最小数，以此达到其总能保存前M大的交易
//            pq.delMin();                  //用排序的方法删除最小的值的时间复杂度为Nlog2(N),空间要求N,没有额外空间去存储,所以不采用
//        }                                 //采用二叉堆队列的时间复杂度为Nlog2(M),空间要求M
//    }
}
