//Weighted-QuickUnion (Week 1,Quick-Union improvements)

public class WeightedQuickUnionUF {
    private int []id;    //指向父亲节点
    private int []sz;   //树的节点数(树的深度为Log2(N)级别)
    public WeightedQuickUnionUF(int N){
        id = new int[N];
        sz = new int[N];
        for(int i = 0;i < N;i++){
            id[i] = i;
            sz[i] = 1;  //初始化每个size为1
        }
    };
    private int root(int i){       //遍历父亲节点（爬树）,输出树的根结点
        while(i != id[i])          //树的深度小了，所以找父节点的速度也为Log2(N)级别
        {
//          id[i] = id[id[i]];    加了这行就是路径压缩，将每个路径上的节点指向其路径上的祖父节点，使爬树更快
            i = id[i];
        }
        return i;
    }

    public boolean connected(int p,int q){
        return root(p) == root(q);
    }

    public void union(int p, int q){     //Log2(N)级别，root方法的原因
        int i = root(p);
        int j = root(q);
        if(i == j) return;
        if(sz[i] < sz[j]){
            id[i] = j;              //永远是节点少的树设置根结点到节点多的树的根节点（减慢树的深度）
            sz[j] += sz[i];
        }
        else{
            id[j] = i;
            sz[i] += sz[j];
        }
    }
}
