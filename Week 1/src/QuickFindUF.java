
//QuickFind (Week 1)

public class QuickFindUF {
    private int[] id;  //给每个元素一个id,一样表示互相连接
    public QuickFindUF(int N){
        id = new int[N];
        for (int i = 0 ; i < N ; i ++){
            id[i] = i;
        }
    }

    public boolean connected(int p,int q){
        return id[p] == id[q];      //找起来快，只需看Id数组是否相等
    }

    public void union(int p, int q){
        int pid = id[p];
        int qid = id[q];
        for (int i = 0;i < id.length;i++){
            if(id[i] == pid)        //连接起来太慢，改一次要遍历一次数组
                id[i] = qid;
        }
    }
};

