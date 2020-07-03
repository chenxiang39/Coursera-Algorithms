import edu.princeton.cs.algs4.Queue;
public class TrieST<Value> {
    private static final int R = 256; //extended ASCII
    private Node root = new Node();
    private static class Node{
        private Object value;       //use Object instead of Value since no generic array creation in Java
        private Node[] next = new Node[R];  //R代表基数，类似26个字母，就是26，可以立刻找到对应的字母（A->0,B->1,..）, next存的都是字母，且按ABCDE..排列
    }
    //create an empty symbol table
    public TrieST(){

    }
    //put Key-value into the symbol table
    public void put(String key, Value val){
        root = put(root,key,val,0);
    }
    private Node put(Node x, String key, Value val, int d){
        if (x == null){
            x = new Node();
        }
        if (d == key.length()){
            x.value = val;
            return x;
        }
        char c = key.charAt(d);         //第d个字符对应的关键字
        x.next[c] = put(x.next[c],key,val,d + 1);
        return x;
    }
    public boolean contains(String key){
        return get(key) != null;
    }
    //return value paired with given key
    public Value get(String key){
        Node x = get(root, key, 0); //定位到key所在的节点
        if (x == null){
            return null;
        }
        return (Value) x.value; //cst needed
    }
    private Node get(Node x, String key, int d){
        if (x == null){
            return null;
        }
        if(d == key.length()){
            return x;
        }
        char c = key.charAt(d);
        return get(x.next[c], key, d + 1);
    }
    //delete key and corresponding value
    public void delete(String key){

    }
    //all keys
    public Iterable<String> keys(){
        Queue<String> queue = new Queue<>();
        collect(root, "", queue);
        return queue;
    }
    //prefix : sequence of characters on path from root to x
    private void collect(Node x,String prefix, Queue<String> q){
        if (x == null){
            return;
        }
        if (x.value != null){
            q.enqueue(prefix);
        }
        for (char c = 0 ; c < R; c++){
            collect(x.next[c],prefix + c, q);
        }
    }
    //keys having s as a prefix
    public Iterable<String> keysWithPrefix(String prefix){
        Queue<String> queue = new Queue<>();
        Node x = get(root, prefix,0);       //定位到含prefix的节点
        collect(x,prefix,queue);            //遍历之后所有的节点
        return queue;
    }
//    //keys that match s (Where is a wildcard)
//    public Iterable<String> keysThatMatch(String query){
//
//    }
    private int search(Node x, String query, int d, int length){
        if (x == null){
            return length;
        }
        if (x.value != null){
            length = d;     //不等于结束遍历，只是遇到了value，如后面还有value，则更新length
        }
        if(d == query.length()){
            return length;
        }
        char c = query.charAt(d);
        return search(x.next[c], query,d + 1, length);
    }
    //longest key that is a prefix of s
    public String longestPrefixOf(String query){
        int length = search(root, query,0 ,0);
        return query.substring(0, length);
    }
}
