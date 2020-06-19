//Week 6 linear probing
//详见书第301页
public class linear_probing<Key,Value> {
    private int M = 30001;
    private Value[] vals = (Value[]) new Object[M];             //数组无法泛型
    private Key[] keys = (Key[]) new Object[M];
    private int hash(Key key){
        //        return (Key.hashCode()& 0x7fffffff)% M;
        return 0;
    }
    public void put(Key key, Value val){
        int i;
        for (i = hash(key); keys[i] != null; i = (i+1) % M){
            if(keys[i].equals(key)){
                break;
            }
            keys[i] = key;
            vals[i] = val;
        }
    }
    public Value get(Key key){
        for (int i = hash(key); keys[i] != null; i = (i+1) % M){
            if (key.equals(keys[i])){
                return vals[i];
            }
        }
        return null;
    }
}
