import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class ShortestPath {
    private DirectedEdge[] edgeTo;
    private double[] distTo;
    public ShortestPath(EdgeWeightedDigraph G, int s){

    }
    //length of shortest push from s to v
    public double distTo(int v){
        return distTo[v];
    }
    //shortest path from s to v
    public Iterable<DirectedEdge> pathTo(int v){
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]){
            path.push(e);
        }
        return path;
    }
    //边的松弛，更新边，因为有更短的边能从v -> w,详见p417,418
    private void relax(DirectedEdge e){
        int v = e.from();
        int w = e.to();
        if (distTo[w] > distTo[v] + e.weight()){
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }
    public class DirectedEdge{
        private final int v,w;
        private final double weight;
        //weighted edge v->w
        public DirectedEdge (int v, int w, double weight){
            this.v = v;
            this.w = w;
            this.weight = weight;
        }
        //vertex v
        public int from(){
            return v;
        }
        //vertex w
        public int to(){
            return w;
        }
        //weight of this edge
        double weight(){
            return weight;
        }
    }
    public class EdgeWeightedDigraph{
        private final int V;
        private final Bag<DirectedEdge>[] adj;
        //edge-weighted digraph with V vertices
        public EdgeWeightedDigraph(int V){
            this.V = V;
            adj = (Bag<DirectedEdge>[]) new Bag[V];
            for (int v = 0; v < V; v++){
                adj[v] = new Bag<DirectedEdge>();
            }
        }
        //add weighted directed edge e
        public void addEdge(DirectedEdge e){
            int v = e.from();
            adj[v].add(e);
        }
        //edges pointing from v
        public Iterable<DirectedEdge> adj(int v){
            return adj[v];
        }
        //number of vertices
        public int V(){
            return V;
        }
    }
    //Dijkstra算法（最短路径）
    public class DijkstraSP{
        private DirectedEdge[] edgeTo;
        private double[] distTo;
        private IndexMinPQ<Double> pq;          //查看p422
        public DijkstraSP(EdgeWeightedDigraph G, int s){
            edgeTo = new DirectedEdge[G.V()];
            distTo = new double[G.V()];
            pq = new IndexMinPQ<Double>(G.V());
            for (int v = 0; v < G.V(); v++){
                distTo[v] = Double.POSITIVE_INFINITY;       //初始化时，将所有点距离初始点的距离都设置成无限大
            }
            distTo[s] = 0.0;        //与初始点的距离为0
            pq.insert(s, 0.0);      //存放点及离初始点的距离
            while (!pq.isEmpty()){
                int v = pq.delMin();        //relax vertices in order of distance from s
                for (DirectedEdge e : G.adj(v)){
                    relax(e);
                }
            }
        }
        //边的松弛，更新边，因为有更短的边能从v -> w
        private void relax(DirectedEdge e){
            int v = e.from();
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()){
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                // update PQ
                if (pq.contains(w)){
                    pq.decreaseKey(w, distTo[w]);           //修正失效边
                }
                else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }
    //Single-source: find the shortest paths from s to every vertex
    public static void main(String[] args){
//        ShortestPath sp = new ShortestPath(G,s);
//        for(int v = 0; v < G.V(); v++){
//            StdOut.printf("%d to %d (%.2f):", s, v, sp.distTo(v));
//            for (DirectedEdge e : sp.pathTo(v)){
//                StdOut.print(e + " ");
//            }
//            StdOut.println();
//        }
    }
}

