//Week 7 Graph
//DFS marks all vertices connected to s in time proportional to the sum of their degrees.
//After DFS, can find vertices connected to s in constant time and can find a path to s(if one exists) in time proportional to its length
//BFS computes shortest paths (fewest number of edges) from s to all other vertices in a graph in time proportional to E + V.
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class Graph {
    private int V;      //vertices
    private Bag<Integer>[] adj;
    private int E;      //edges
    //create an empty graph with V vertices
    public Graph(int V){
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++){
            adj[v] = new Bag<Integer>();            //no edge
        }
    }
    //create a graph from input stream
    public Graph(In in){

    }
    //add an edge v-w
    void addEdge(int v,int w){
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }
    //vertices adjacent to v
    public Iterable<Integer> adj(int v){
        return adj[v];
    }
    //number of vertices
    public int V(){
        return V;
    }
    //number of edges
    public int E(){
        return E;
    }
    //String representation
//    String toString(){
//
//    }
    //compute the degree of V(跟v相交的边的数)
    public static int degree(Graph G,int v){
        int degree = 0;
        for (int w : G.adj(v)){
            degree++;
        }
        return degree;
    }
    //compute maximum degree
    public static int maxDegree(Graph G){
        int max = 0;
        for(int v = 0; v < G.V();v++){
            if (degree(G, v) > max){
                max = degree(G,v);
            }
        }
        return max;
    }
    //compute average degree
    public static double averageDegree(Graph G){
        return 2.0 * G.E()/G.V();
    }
    //count self-loops
    public static int numberOfSelfLoops(Graph G){
        int count = 0;
        for (int v = 0; v < G.V(); v++){
            for (int w : G.adj(v)){
                if(w == v){         //自己与自己靠近
                    count++;
                }
            }
        }
        return count/2;         //each edge counted twice
    }
    //深度优先遍历
    public class DepthFirstPaths{
        private boolean[] marked;       //是否调用过dfs()
        private int[] edgeTo;       //if v connected to s,edgeTo[v] = previous
        private int s;      //vertex on path from s to v
        public DepthFirstPaths(Graph G, int s){
            marked = new boolean[G.V()];
            dfs(G,s);
        }
        private void dfs(Graph G,int v){
            marked[v] = true;
            for (int w : G.adj(v)){
                if(!marked[w]){
                    dfs(G,w);
                    edgeTo[v] = w;
                }
            }
        }
        //is there a path from s to v ?
        boolean hasPathTo(int v){
            return marked[v];
        }
        //path from s to v; null if no such path
        public Iterable<Integer> pathTo(int v){
            if(!hasPathTo(v)){
                return null;
            }
            Stack<Integer> path = new Stack<Integer>();
            for(int x = v; x != s; x = edgeTo[x]){
                path.push(x);
            }
            path.push(s);           //最后压起始点进栈
            return path;
        }
    }
    //广度优先遍历
    public class BreadthFirstPaths{
        private boolean[] marked;
        private int[] edgeTo;
        //s => 起始点
        private void bfs(Graph G, int s){
            Queue<Integer> q = new Queue<Integer>();
            q.enqueue(s);
            marked[s] = true;
            while (!q.isEmpty()){
                int v = q.dequeue();
                for(int w : G.adj(v)){
                    if(!marked[w]){
                        q.enqueue(w);
                        marked[w] = true;
                        edgeTo[w] = v;
                    }
                }
            }
        }
    }
    //连接组件
    public class Connected_Components{
        private boolean marked[];
        private int[] id;       //id[v] = id of component containing V
        private int count;      //number of components
        public Connected_Components(Graph G){
            marked = new boolean[G.V()];
            id = new int[G.V()];
            for (int v = 0; v < G.V(); v++){
                if(!marked[v]){
                    dfs(G,v);
                    count++;
                }
            }
        }
        public int count(){
            return count;
        }
        public int id(int v){
            return id[v];
        }
        private void dfs(Graph G,int v){
            marked[v] = true;
            id[v] = count;      //all vertices discovered in same call of dfs have same id
            for (int w : G.adj(v)){
                if(!marked[w]){
                    dfs(G,w);
                }
            }
        }
    }
    public static void main(String[] args){
        In in = new In(args[0]);
        Graph G = new Graph(in);
        for(int v = 0; v < G.V(); v++){
            for (int w : G.adj(v)){
                StdOut.println(v + "-" + w);
            }
        }
//        Paths paths = new Paths(G,s);
//        for (int v = 0; v < G.V(); v++){
//            if(paths.hasPathTo(v)){
//                StdOut.println(v);
//            }
//        }
    }
}
