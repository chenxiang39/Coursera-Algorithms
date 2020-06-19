import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

//Week 4 Binary Search tree
//Week 4 Ordered operations in BSTs
//二叉查找树
//每个节点包含四个内容：左子树的根节点(比原根节点小)，右子树的根节点（比原根节点大）,key和value，
//Comparable的key
//查找操作：比如查找H,先跟根节点比，若比根节点大，则跟根节点的右节点比，若比根节点的右节点小，则跟根节点的右节点的做节点比，以此类推
//插入操作：类似于查找操作，当找到null节点时（元素在树中不存在），将需要插入的元素放置在那里
//比较的数字将取决于键输入的顺序
//N个随机节点，查找和插入的平均比较次数为2*ln（N）次
//综合比较次数的效率：见 ST implementations summary.jpg
//查找二叉排序树的最大值：一直遍历右子树，直到下一个右子树为null
//查找二叉排序树的最小值：一直遍历左子树，直到下一个左子树为null
//flooring : largest key <= to a given key
//ceiling : smallest key >= to a given key
//rank : how many keys < k ?
//符号表对各种排序操作的时间复杂度对比：见 ordered symbol table operations summary.jpg
public class BinarySearchTree<Key extends Comparable<Key>, Value>{
    private Node root;    //根节点
    private class Node{
        private Key key;
        private Value val;
        private Node left,right;
        private int count;   // number of nodes in subtree (include root)
        public Node(Key key, Value val){
            this.key = key;
            this.val = val;
        }
        public Node(Key key, Value val,int count){
            this.key = key;
            this.val = val;
            this.count = count;
        }
    }
    //查找key,找到则更新它的值，否则为其创建一个新的节点
    public void put(Key key, Value val){
        root = put(root,key,val);
    }
    //递归实现
    private Node put(Node x, Key key, Value val){
        //如果key存在于以x为根节点的子树中则更新它的值
        //否则将以key和val为键值对的新节点插入到该子树中
        if(x == null){
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            x.left = put(x.left, key, val);
        }
        else if (cmp > 0){
            x.right = put(x.right, key, val);
        }
        else{
            x.val = val;
        }
        x.count = 1 + size(x.left) + size(x.right);
        return x;
    }
    // return Value corresponding to given key
    public Value get(Key key){
        Node x = root;
        while (x != null){
            int cmp = key.compareTo(x.key);
            if (cmp < 0){
                x = x.left;
            }
            else if (cmp > 0){
                x = x.right;
            }
            else {
                return x.val;
            }
        }
        return null;
    }
    public Key min(){
        return min(root).key;
    }
    private Node min(Node x){
        if (x.left == null){
            return x;
        }
        return min(x.left);
    }
    //Hibbard deletion (不对称性，此方法不令人满意，在多次删除后，数的高度是N的开根号级别，不是log2(N)级别)
    //三种情况：（1）删除的节点没有子节点，只需要返回null;
             // (2)有一个子树，类似删除最小项，用子树代替就行
             //（3）有两个子树，找到右子树中的最小节点，将找到的最小节点（使用删除最小点的方法删除它），再将最小点连接到原节点的左子树节点，并删除原节点
    public void delete(Key key){
        root = delete(root,key);
    }
    private Node delete(Node x, Key key){
        if (x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            x.left = delete(x.left, key);       //search for key
        }
        else if (cmp > 0){
            x.right = delete(x.right,key);      //search for key
        }
        else {
            if (x.right == null)
                return x.left;      //被删节点没右子树，则直接返回其左子树
            if (x.left == null)
                return x.right;     //被删节点没左子树，则直接返回其右子树
            Node t = x;  //t表示需要被删除的目标节点
            x = min(t.right);
            x.right = deleteMin(t.right);   //将删除处理过后的右子树接到x上（x为代替原来t的节点位置的节点，即目标节点右子树中最小的元素）
            x.left = t.left;            //x的左子树换为原目标节点的左子树，彻底删除目标节点
        }
        x.count = size(x.left) + size(x.right) + 1;
        return x;
    }
    public Key floor(Key key){
        Node x = floor( root, key);
        if (x == null){
            return null;
        }
        return x.key;
    }
    private Node floor(Node x, Key key){
        if ( x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0){
            return x;
        }
        else if (cmp < 0){           //若比根结点小，则结果肯定在左子树中
            return floor(x.left, key);
        }
        Node t = floor(x.right, key);   //切换到右子树去搜索
        if (t != null){     //右子树存在结果
            return t;
        }
        else {              //若右子树不存在结果，则值为root
            return x;
        }
    }
    public int size(){
        return size(root);
    }
    private int size(Node x){
        if (x == null){
            return 0;
        }
        return x.count;
    }
    public int rank(Key key){
        return rank(key, root);
    }
    private int rank(Key key, Node x){
        if(x == null){
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            return rank(key,x.left);
        }
        else if (cmp > 0){
            return 1 + size(x.left) + rank(key,x.right);
        }
        else {
            return size(x.left);
        }
    }
    public void deleteMin(){
        root = deleteMin(root);
    }
    //删除最小项（返回的是根节点x的左节点（未删除前是其右子树的根结点）
    private Node deleteMin(Node x){
        if (x.left == null){
            return x.right;         //用右边的子树代替原来的节点（最左边的节点）（因是最小节点所以不存在其左子树）
        }
        x.left = deleteMin(x.left); //递归后，将之前的根结点的左节点替换成原来的右节点（原来的左节点进入垃圾回收））
        x.count = 1 + size(x.left) + size(x.right); //修正节点数
        return x;
    }
    public Iterable<Key> iterator(){
        Queue<Key> q = new Queue<Key>();
        inorder(root,q);    //按自然顺序将元素放进队列
        return q;
    }
    private void inorder(Node x, Queue<Key> q){
        if (x == null){
            return;
        }
        inorder(x.left,q);
        q.enqueue(x.key);       //进入队列
        inorder(x.right,q);
    }
    public static void main(String[] args){
        BinarySearchTree tree = new BinarySearchTree();
        tree.put("d",1);
        tree.put("b",2);
        tree.put("c",3);
        tree.put("g",4);
        tree.put("z",5);
        tree.deleteMin();
        for (Object s: tree.iterator()) {
            StdOut.print(s+" ");
        }
//        StdOut.print(tree.size());
    }
}
