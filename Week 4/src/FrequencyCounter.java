import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdIn;

//Week 4 Symbol Table API
//频率计数器
//从标准输入的字符串序列中打印出现频率最高的那个词
public class FrequencyCounter {
     public static void main(String[] args){
         int minlen = Integer.parseInt(args[0]); //转成整数，关系的最小长度
         ST<String,Integer> st = new ST<String,Integer>();      //Symbol Table
         while (!StdIn.isEmpty()){
             String word = StdIn.readString();
             if(word.length() < minlen){
                 continue;              //忽略短字符串，单词太短
             }
             if (!st.contains(word)){
                 st.put(word,1);        //增加第一个单词(若从未出现，则频率设置为1)
             }
             else{
                 st.put(word,st.get(word) + 1);     //频率增加1
             }
         }
     }
}
