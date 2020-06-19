//Week 4 assignment
//A*算法介绍：https://blog.csdn.net/qq_36946274/article/details/81982691
//给定实例存在解，或给定实例的“Twin”实例存在解。
//因此，整个算法要求构造一个给定实例的Twin实例，然后同时在两个实例中运行搜索算法：如果给定实例找到了解，那么就输出解；
//如果Twin实例找到了解，那么说明给定实例不存在解，输出null

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;


public class Solver {
    private boolean isSolved;       //是否有解
    private SearchNode currentNode ;    //定义堆中的优先值最小的点（方便循环）

    private class SearchNode implements Comparable<SearchNode>{         //使其实现Comparable接口，这样能让二叉堆知道如何识别最小优先值
        private SearchNode preNode;         //之前的父亲棋盘节点
        private Board currentBoard;     //当前棋盘
        private int priority;       //优先权值
        private int moves;       //移动步数
        public SearchNode(Board currentBoard, SearchNode preNode) {
            this.currentBoard = currentBoard;
            this.preNode = preNode;
            if(preNode == null){
                moves = 0;
            }
            else {
                moves = preNode.moves + 1;          //前置节点的步数加1
            }
            priority = currentBoard.manhattan() + moves;
        }
        public int compareTo(SearchNode otherNode) {
            return Integer.compare(this.getPriority(), otherNode.getPriority());
        }
        public int getPriority() {
            return priority;
        }
        public Board getBoard() {
            return currentBoard;
        }
        public int getMoves() {
            return moves;
        }
        public SearchNode getPreNode() {
            return preNode;
        }
    };

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial){
        SearchNode currentTwinNode ;    //定义兄弟堆中的优先值最小的点（方便循环）(不在Solver类里直接定义的原因是减少内存使用量)
        if (initial == null){
            throw new IllegalArgumentException("Board is null!");
        }
        isSolved = false;
        SearchNode initalNode = new SearchNode(initial,null); //定义初始节点
        SearchNode initalTwinNode = new SearchNode(initial.twin(),null); //定义初始节点的兄弟节点
        MinPQ<SearchNode> minPQNodeTree = new MinPQ<>();        //定义初始最小二叉堆
        MinPQ<SearchNode> minPQNodeTwinTree = new MinPQ<>();    //定义初始兄弟节点最小二叉堆
        minPQNodeTree.insert(initalNode);
        minPQNodeTwinTree.insert(initalTwinNode);
        boolean IsFinished = false; //定义查找是否结束（原节点和兄弟节点中必有一个能找到解）
        while (IsFinished == false ){
            currentNode = minPQNodeTree.delMin(); //取出最小二叉堆中优先值最小的点
            currentTwinNode = minPQNodeTwinTree.delMin();
            if (currentNode.getBoard().isGoal()){
                IsFinished = true;
                isSolved = true;        //找到解
            }
            else
                InsertNeighbourNode(currentNode,minPQNodeTree);
            if (currentTwinNode.getBoard().isGoal()){
                IsFinished = true;
            }
            else
                InsertNeighbourNode(currentTwinNode,minPQNodeTwinTree);
        }
    }
    private void InsertNeighbourNode(SearchNode originalNode, MinPQ<SearchNode> minPQ_Tree){     //插入orginalNode的邻居节点到最小二叉树中
        Iterable<Board> neighbourList = originalNode.getBoard().neighbors();        //获取邻居
        for (Board neighbour : neighbourList){
            if(originalNode.getPreNode() == null || neighbour.equals(originalNode.getPreNode().getBoard()) != true ){        //如果邻居节点中有和orginalNode的前置节点相同的点则不能加入二叉堆
                minPQ_Tree.insert(new SearchNode(neighbour,originalNode));
            }
        }
    }
    // is the initial board solvable? (see below)
    public boolean isSolvable(){
        return isSolved;
    }

    // min number of moves to solve initial board
    public int moves(){
        if(isSolvable() == false){
            return -1;
        }
        else{
            return currentNode.getMoves();
        }
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution(){
        if (isSolvable() == false){
            return null;
        }
        Stack<Board> BoardStack = new Stack<Board>(); //用堆栈是因为有前置节点
        SearchNode TargetNode = currentNode; //设置目标节点（已排好序）最先进栈，出栈时正好按最开始的点开始向后进行
        while (TargetNode != null){
            BoardStack.push(TargetNode.getBoard());
            TargetNode = TargetNode.getPreNode();
        }
        return BoardStack; //因栈方法实现了Iterator方法,故可直接返回
    }

    // test client (see below)
    public static void main(String[] args){
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);
        // solve the puzzle
        Solver solver = new Solver(initial);
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}