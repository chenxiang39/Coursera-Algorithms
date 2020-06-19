//堆栈方式演示算术表达式的计算过程
//Week 2 Stack and Queue Application
//注意: java.util.Stack returns 数组 in FIFO order.
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Evaluate {
    public static void main(String[] args){
        LinkedStackOfStrings_Generic<String> ops = new LinkedStackOfStrings_Generic<String>();
        LinkedStackOfStrings_Generic<Double> vals = new LinkedStackOfStrings_Generic<Double>();
        while (!StdIn.isEmpty()){
            String s = StdIn.readString();
            if(s.equals("(")){

            }
            else if(s.equals("+")){
                ops.push(s);
            }
            else if(s.equals("*")){
                ops.push(s);
            }
            else if(s.equals(")")){
                String op = ops.pop();
                if (op.equals("+")){
                    vals.push(vals.pop() + vals.pop());
                }
                else if (op.equals("*")){
                    vals.push(vals.pop() * vals.pop());
                }
            }
            else  vals.push(Double.parseDouble(s));
        }
        StdOut.println(vals.pop());
    }
}
