public class TrieST<Value> {
    private static final int R = 256; //extended ASCII
    private Node root = new Node();
    private static class Node{
        private Object value;       //use Object instead of Value since no generic array creation in Java
        private Node[] next = new Node[R];  //R代表基数，类似26个字母，就是26，可以立刻找到对应的字母（A->0,B->1,..）
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
        Node x = get(root, key, 0);
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
}
