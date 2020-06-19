//Week 4 Symbol Table API & Elementary Implementation
////所有java的数据类型都支持equal操作（但默认实现只是测试引用是否相等）
////key值使用不可变的数据类型(符号表不这样做)
////equals方法内需要传的参数类型应该是Object类,不是一个特定的类
////ST时间复杂度总结：Elementary Implementation 最后
////使用链表方式将新键值队添加进符号表中
//比较方法的设计：
//1.两者引用是否相等
//2.检查是否为null
//3.检查两个变量的类型是否相同
//4.如果是原始类型，使用==比较；如果是object类型，使用equals比较；如果是数组，则遍历每一个变量进行比较（不要使用A.equals(B)比较,可以使用Array.equals(A,B)）比较
//public class SymbolTable {
//    public SymbolTable(){
//
//    }
//    void put(Key key, Value val){
//
//    }
//    private int rank(Key key){      //二进制查找
//        int lo = 0; int hi = N - 1;
//        while (lo <= hi){
//            int mid = lo + (hi - lo)/2;
//            int cmp = key.compareTo(keys[mid]);
//            if (cmp < 0) hi = mid - 1;
//            if (cmp > 0) lo = mid + 1;
//            else if (cmp == 0) return mid;
//        }
//        return lo;
//    }
//    public Value get(Key key){
//          if(isEmpty()){
//              return null;
//          }
//          int i = rank(key);
//          if (i < N && key[i].compareTo(key) == 0){     //键是否存在
//              return vals[i];
//          }
//          else{
//              return null;
//          }
//    }
//    void delete(Key key){
//
//    }
//}
