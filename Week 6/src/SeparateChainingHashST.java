//Week 6 Separate chaining


public class SeparateChainingHashST<Key, Value> {
    private int M = 97;  //number of chaining
    private Node[] st = new Node[M];
    private static class Node{
        private Object key;   //no generic array creation(declare key and value of type Object)
        private Object val;
        private Node next;
        public Node(Object key,Object val, Object hashCode){
            this.key = key;
            this.val = val;
        }
    }
    private int hash(Key key){
//        return (Key.hashCode()& 0x7fffffff)% M;
        return 0;
    }
    public Value get(Key key){
        int i = hash(key);
        for(Node x = st[i]; x != null; x = x.next ){
            if(key.equals(x.key)){
                return  (Value) x.val;
            }
        }
        return null;
    }
    public void put(Key key, Value val){
        int i = hash(key);
        for(Node x = st[i]; x != null; x = x.next){
            if(key.equals(x.key)){
                x.val = val;
                return;
            }
        }
        st[i] = new Node(key,val,st[i]);
    }
}
