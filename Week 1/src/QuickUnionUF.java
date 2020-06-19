
//QuickUnion (Week 1)

public class QuickUnionUF {
    private int[] id;   //指向父亲节点
    public QuickUnionUF(int N){
        id = new int[N];
        for (int i = 0;i < N; i++){
            id[i] = i;
        }
    }

    private int root(int i){  //遍历父亲节点（爬树）,输出树的根结点
        while(i != id[i])
            i = id[i];     // 找root太慢，树可能太高（N级别）
        return i;
    }

    public boolean connected(int p,int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){
        int i = root(p);     //连接起来快，只要把p的根结点设置成q的根结点即可(但root方法是N级别,所以union的方法是N级别)
        int j = root(q);
        id[i] = j;
    }
}
