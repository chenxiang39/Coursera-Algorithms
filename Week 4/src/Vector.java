//不可变数据模版
//Week 4 Binary Heaps

public class Vector {           //不会覆盖实例方法
    private final int N;        //private 和 final 修饰
    private final double[] data;
    public Vector(double[] data){
        this.N = data.length;
        this.data = new double[N];
        for (int i = 0; i < N; i++){    //抵御可变实例的复制
            this.data[i] = data[i];
        }
    }
    //没有方法能改变实例变量
}
