import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

public class EdgeWeightedGraph{
    private int V;
    private Bag<Edge>[] adj;        //same as Graph, but adjacency lists of Edges instead of integers
    public EdgeWeightedGraph(int V){
        this.V = V;
        adj = (Bag<Edge>[])new Bag[V];
        for (int v = 0; v < V; v++){
            adj[v] = new Bag<Edge>();
        }
    }
    public void addEdge(Edge e){
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
    }
    public int V(){
        return V;
    }
    public Iterable<Edge> adj(int v){
        return adj[v];
    }
    public Iterable<Edge> edges(){
        Bag<Edge> b = new Bag<>();
        for (int v = 0; v < V; v++){
            for (Edge e : adj[v]){
                if (e.other(v) > v){
                    b.add(e);
                }
            }
        }
        return b;
    }
    public class Edge implements Comparable<Edge>{
        private int v,w;        //两个点
        private double weight;
        public Edge(int v, int w, double weight){
            this.v = v;             //constructor
            this.w = w;
            this.weight = weight;
        }
        public int either(){
            return v;               //either endpoint
        }
        public int other(int vertex){
            if (vertex == v){
                return w;           //other endpoint
            }
            else {
                return v;
            }
        }
        public int compareTo(Edge that){
            if (this.weight < that.weight){
                return -1;
            }
            else if(this.weight > that.weight){
                return 1;
            }
            else {
                return 0;
            }
        }
        //Kruskal Algorithm
        public class KruskalMST{
            private Queue<Edge> mst = new Queue<Edge>();
            public KruskalMST(EdgeWeightedGraph G){
                MinPQ<Edge> pq= new MinPQ<>();      //build priority queue
                for (Edge e : G.edges()){
                    pq.insert(e);
                }
                UF uf = new UF(G.V());
                while (!pq.isEmpty() && mst.size() < G.V() - 1){
                    Edge e = pq.delMin();           //从weight最小的开始加起
                    int v = e.either();         //greedily add edges to MST
                    int w =  e.other(v);
                    if (!uf.connected(v,w)){        //edge v-w does not create cycle
                        uf.union(v,w);              //merge sets;
                        mst.enqueue(e);             //add edges to MST
                    }
                }
            }
            public Iterable<Edge> edges(){
                return mst;
            }
        }
    }
    public static void main(String[] args){
//        In in = new In(args[0]);
//        EdgeWeightedGraph G = new EdgeWeightedGraph(in);
//        MST mst = new MST(G);
//        for (Edge e : mst.edges()){
//            StdOut.println(e);
//        }
//        StdOut.printf("%.2f\n", mst.weight());
    }
}
