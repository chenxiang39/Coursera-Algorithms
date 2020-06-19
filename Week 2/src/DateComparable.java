//Comparable接口的实现
//Week 2 Sorting Introduce


import edu.princeton.cs.algs4.StdOut;

public class DateComparable implements Comparable<DateComparable> {
    private final int month, day, year;
    public DateComparable(int m,int d,int y){
        month = m;
        day = d;
        year = y;
    }
    public int compareTo(DateComparable that){
        if(this.year > that.year)
            return 1;
        if(this.year < that.year)
            return -1;
        if(this.month > that.month)
            return 1;
        if(this.month < that.month){
            return -1;
        }
        if(this.day > that.day){
            return 1;
        }
        if(this.day < that.day){
            return -1;
        }
        return 0;
    }
    public static void main(String[] args){
        DateComparable a = new DateComparable(1,2,1999);
        DateComparable b = new DateComparable(2,3,1998);
        StdOut.print(a.compareTo(b));
    }
}
