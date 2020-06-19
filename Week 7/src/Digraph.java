//Week 7 Digraph Search
//Java中的垃圾回收机制用的就是有序图的原理(详见P368)
//网络爬虫使用的是有向图中的广度优先遍历算法
//DAG: Directed acyclic graph （拓扑排序会用到）
//Strong connected : P378
//Strong component is a maximal subset of strongly-connected verticeså
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Digraph {
    private int V;
    private int E;
    private Bag<Integer>[] adj;     //adjacency lists
    public Digraph(int V){
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++){
            adj[v] = new Bag<Integer>();
        }
    }
    public Digraph(In in){

    }
    public void addEdge(int v, int w){
        adj[v].add(w);
    }
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
    public int V(){
        return V;
    }
    public int E(){
        return E;
    }
    //与无向图graph代码完全一样
    public class DepthFirstSearch{
        private boolean[] marked;       //true if path to s;
        public DepthFirstSearch(Digraph G,int s){
            marked = new boolean[G.V()];
            dfs(G,s);
        }
        //constructor marks vertices connected to s
        private void dfs(Digraph G,int v){
            marked[v] = true;
            for (int w : G.adj(v)){
                if (!marked[w]){
                    dfs(G,w);
                }
            }
        }
        //client can ask whether any vertex is connected to s
        public boolean visited(int v){
            return marked[v];
        }
    }
    //topological sort (postorder)
    public class DepthFirstOrder{
        private boolean[] marked;
        private Stack<Integer> reversePost;
        public DepthFirstOrder(Digraph G){
            reversePost = new Stack<Integer>();
            marked = new boolean[G.V()];
            for (int v = 0; v < G.V(); v++){
                if (!marked[v]){
                    dfs(G,v);
                }
            }
        }
        private void dfs(Digraph G,int v){
            marked[v] = true;
            for (int w : G.adj(v)){
                if (!marked[w]){
                    dfs(G,w);       //当运行到最后一点时，即w为最后一个点，此时运行dfs会不进行任何操作，因此直接执行下一行，push
                }
            }
            reversePost.push(v);        //直到这是最后一个点（遍历到的最后一个，即没有可以通过的点）了才将其压进栈中，所以为倒序
        }
        public Iterable<Integer> reversePost(){
            return reversePost;
        }
    }
    public static void main(String[] args){
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        for (int v = 0; v < G.V(); v++){
            for (int w : G.adj(v)){
                StdOut.println(v +"-" + w);
            }
        }
    }
}
