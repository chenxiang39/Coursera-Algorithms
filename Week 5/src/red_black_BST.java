//Week 5 Red-Black BST
//红黑树，比BST的优势在于能保持平衡（不会出现树很高，元素却很少的情况）
//红链接均为左链接，完美黑色平衡（所有null节点到root的黑色链接数量相同）
//没有任何一个节点同时和两条红链接相连
//不允许连续两个红链接
//最长路径（红黑路径都有）不超过全部是黑色路径的两倍长
//Height of tree is <= 2*log2(N) in the worst case
//每个节点只被一条链路所指

public class red_black_BST<Key extends Comparable<Key>, Value> {
    private Node root;
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node {
        private Key key;
        private Value val;
        private Node left,right;
        boolean color;   //color of parent link
        private int count;
        public Node(Key key, Value val){
            this.key = key;
            this.val = val;
        }
        public Node(Key key, Value val, Boolean color){
            this.key = key;
            this.val = val;
            this.color = color;
        }
    }
    public Value get(Key key){                //like elemtentary BST
        Node x = root;
        while (x != null){
            int cmp = key.compareTo(x.key);
            if (cmp < 0){
                x = x.left;
            }
            else if (cmp > 0){
                x = x.right;
            }
            else{
                return x.val;
            }
        }
        return null;
    }
    private boolean isRed (Node x){
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }
    //保持黑平衡
    private Node rotateLeft(Node h){        //让原本处于右链接为红色的状态转为左链接为红色的状态
        //assert isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    private Node rotateRight(Node h){        //同上相反
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }
    //当有两条红链接从某一节点连接出去时
    private void flipColors(Node h){
        assert !isRed(h);   //前一个若为红的话，则转换为2-3树即出现了4-node的节点，是不对的。
        assert isRed(h.left);
        assert isRed(h.right);
        h.color = RED;      //将链接变成红色
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
    private Node put(Node h, Key key, Value val){
        if(h == null){
            return new Node(key,val,RED);
        }
        int cmp = key.compareTo(h.key);
        if(cmp < 0){
            h.left = put(h.left,key,val);
        }
        else if(cmp > 0){
            h.right = put(h.right,key,val);
        }
        else{
            h.val = val;
        }
        if (isRed(h.right) && !isRed(h.left)){
            h = rotateLeft(h);                  //修正右倾现象
        }
        if (isRed(h.left)&& isRed(h.left.left)){
            h = rotateRight(h);
        }
        if (isRed(h.left) && isRed(h.right)){
            flipColors(h);
        }
        return h;
    }
}
