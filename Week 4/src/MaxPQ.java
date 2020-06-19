//使用二叉堆实现最大值优先队列（最大二叉堆）
//父元素必须比两个子元素大
//Week 4 Binary Heaps


import edu.princeton.cs.algs4.StdOut;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;    //元素从1开始算，pq[0]为空，减少计算量
    private int N;       //元素的数量

    public MaxPQ(int capacity){
        pq = (Key[]) new Comparable[capacity + 1];
    }
    public boolean isEmpty(){
        return N == 0;
    }
    public void insert(Key key){        //插入元素(如果发生容量溢出则使用resizing array(Week 2 内容))
        pq[++N] = key;                  //现将元素插入二叉堆的最后一个
        swim(N);                        //将插入的元素进行上浮操作,使其符合最大二叉堆的规则
    }
    public Key delMax(){                //删除最大值(如果删的是空值则报错)
        Key max = pq[1];                //根结点为最大节点
        exch(1 , N--);               //交换二叉堆最后的节点和最大节点并将N - 1
        sink(1);                     //对根结点进行下沉操作
        pq[N + 1] = null;               //将之前的最后一个位置清空防止变量游离
        return max;
    }
    public void swim(int k){                   //上浮操作(子节点比父亲节点大)
        while (k > 1 && less(k/2, k)){      //如果k不是根节点且k的父亲节点比k小
            exch(k, k/2);                   //交换k与其父亲节点
            k = k/2;                           //将k缩小一半(提高层数)
        }
    }
    public void sink(int k){                   //下沉操作(父亲节点比某个子节点(或所有子节点)小)
        while (2 * k <= N){
            int j = 2 * k;                      //设置j为子节点（初始为左孩子）下标
            if ( j < N && less(j,j+1)){      //比较两个孩子谁大，将j赋值到值大的孩子节点上
                j++;
            }
            if(!less(k,j)){                     //直到点比大的那个孩子节点大
                break;
            }
            exch(k,j);                          //交换孩子节点与k的数值
            k = j;                              //将k设置成大的孩子节点(降低层数)（仅改变位置，并非内容）
        }
    }
    private boolean less(int i, int j){
        return pq[i].compareTo(pq[j]) < 0;
    }
    private void exch(int i, int j){
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }
}
