import java.util.Scanner;

public class UnionFindQuiz2 {
    private int []id;
    private int []sz;
    private int N;
    Scanner input = new Scanner(System.in);
    public UnionFindQuiz2(int N){
        id = new int[N];
        sz = new int[N];
        this.N = N;
        for(int i = 0;i < N;i++){
            id[i] = i ;
            sz[i] = 1 ;
        }
    }
    private int root(int i){
        while(i != id[i]){
            id[i] = id[id[i]];
            i = id[i];
        }
        return i;
    }
    public boolean connected(int p,int q){
        return root(p) == root(q);
    }
    public void union(int p,int q){
        int i = root(p);
        int j = root(q);
        if(i == j)
            return;
        if(sz[i] >= sz[j]){
            id[j] = i;
            sz[i] += sz[j];
        }
        else{
            id[i] = j;
            sz[j] += sz[i];
        }
    }
    public int find(int p){
        int Max = root(p);
        for(int i = 0;i < N;i++){
            if (connected(i,p)){
                if (i > Max){
                    Max = i;
                }
            }
        }
        return Max;
    }

    public void FindCommand(){
        int needFind;
        System.out.print("Find Number：\n");
        needFind = input.nextInt();
        System.out.print("Max Number：\n");
        System.out.print(find(needFind));
        System.out.print("\n");
    }
    public void UnionCommand(){
        int p = 0;
        int q = 0;
        while(p != 999 && q != 999){
            System.out.print("p：\n");
            p = input.nextInt();
            System.out.print("q：\n");
            q = input.nextInt();
            if (p == 999 || q == 999){
                break;
            }
            union(p,q);
        }
        Start();
    }
    public void Start(){
        int Request;
        System.out.print("please input request: 1.find 2.union(stop when input '999')\n");
        Request = input.nextInt();
        if(Request == 1){
            FindCommand();
        }
        else if (Request == 2){
            UnionCommand();
        }
    }
    public static void main(String[] args){
        System.out.print("N：\n");
        Scanner input = new Scanner(System.in);
        UnionFindQuiz2 test = new UnionFindQuiz2(input.nextInt());
        test.Start();
    }
}
