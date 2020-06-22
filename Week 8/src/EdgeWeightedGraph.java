import edu.princeton.cs.algs4.*;

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
        /**
         * Returns the sum of the edge weights in a minimum spanning tree (or forest).
         * @return the sum of the edge weights in a minimum spanning tree (or forest)
         */
        public double weight() {
            double weight = 0.0;
            for (Edge e : edges())
                weight += e.weight();
            return weight;
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
        //选取最小的边，且不会生成环的情况下
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
    //运行时间：O(E * log2(E))级别，额外空间O（E）级别（最坏情况）
    public class LazyPrimMST{
        private boolean[] marked;   //MST vertices
        private Queue<Edge> mst;    //MST edges
        private MinPQ<Edge> pq;     //PQ of edges
        public LazyPrimMST(EdgeWeightedGraph G){
            pq = new MinPQ<Edge>();
            mst = new Queue<Edge>();
            marked = new boolean[G.V()];
            visit(G,0);         //从第一个点开始
            while (!pq.isEmpty()){
                Edge e = pq.delMin();       //repeatedly delete the min weight edge e = v-w from PQ
                int v = e.either();
                int w = e.other(v);
                if (marked[v] && marked[w]){
                    continue;               // ignore if both endpoints in T
                }
                mst.enqueue(e);             //add edge e to tree
                if (!marked[v]){            //add v or w to tree
                    visit(G,v);
                }
                if (!marked[w]){
                    visit(G,w);
                }
            }
        }
        //标记顶点v并将所有连接v和未被标记顶点的边加入pq
        private void visit(EdgeWeightedGraph G, int v){
            marked[v] = true;
            for (Edge e : G.adj(v)){
                if(!marked[e.other(v)]){
                    pq.insert(e);
                }
            }
        }
        //Prim算法（即时版本）(p403)
        public class PrimMST{
            private Edge[] edgeTo; //距离树最近的边
            private double[] distTo; //distTo[w] = edgeTo[w].weight()
            private boolean[] marked;   //如果v在树中则为true;
            private IndexMinPQ<Double> pq;      //有效的横切边
            public PrimMST(EdgeWeightedGraph G){
                edgeTo = new Edge[G.V()];
                distTo = new double[G.V()];
                marked = new boolean[G.V()];
                for (int v = 0; v < G.V(); v++){
                    distTo[V] = Double.POSITIVE_INFINITY;
                }
                pq = new IndexMinPQ<Double>(G.V());
                distTo[0] = 0.0;
                pq.insert(0,0.0);
                while (!pq.isEmpty()){
                    visit(G, pq.delMin());      //将最近的顶点添加到树中
                }
            }
            //将顶点v添加到树中，更新数据
            private void visit(EdgeWeightedGraph G, int v){
                marked[v] = true;
                for (Edge e : G.adj(v)){
                    int w = e.other(v);
                    if (marked[w]){
                        continue;
                    }
                    if(e.weight() < distTo[w]){
                        //连接w和树的最佳边Edge变为e
                        edgeTo[w] = e;
                        distTo[w] = e.weight();
                        if (pq.contains(w)){
                            pq.changeKey(w, distTo[w]);
                        }
                        else {
                            pq.insert(w, distTo[w]);
                        }
                    }
                }
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
