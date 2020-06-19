//Percolation (Week 1,Union-find Assignment)


import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // creates n-by-n grid, with all sites initially blocked
    private boolean [] MonteCarloSquare;   //boolean的内存占用为1个字节, int的内存占用为4个字节,故选用boolean类型节约内存
    private final int VirtualTop;
    private final int VirtualBottom;
    private final int N;
    private int CountOfOpenSites = 0;
    private  WeightedQuickUnionUF WQU;
    private  WeightedQuickUnionUF BackupWQU;
    public Percolation(int n){
        if (n < 1){
            throw new IllegalArgumentException("error");
        }
        this.N = n;
        WQU = new WeightedQuickUnionUF(n*n+2);
        BackupWQU = new WeightedQuickUnionUF(n*n+1);            //复制一个Unit,使其不连接底部虚拟点（否则判断isfull会出错）
        MonteCarloSquare = new boolean[n*n+2];
        for(int i = 0;i < n*n+2;i++){
            MonteCarloSquare[i] = false;     // false -> block，true -> open
        }
        VirtualTop = n*n;
        VirtualBottom = n*n+1;
    }
    private void validate(int row, int col){
        if (row < 1 || col < 1 || row > N || col > N ){
            throw new IllegalArgumentException("R:"+row+"  C:"+col+"\n");
        }
    }
    private boolean returnTF_validate(int row, int col){                //为了使IsOpen方法能抛出异常，而不是返回false(用在open方法的检查中)
        if (row < 1 || col < 1 || row > N || col > N ){
            return false;
        }
        return true;
    }
    private int changeIndex(int row, int col){
        return (row - 1)* N + col - 1;
    }
    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        validate(row,col);
        if(isOpen(row,col)){
            return;
        }
        MonteCarloSquare[changeIndex(row,col)] = true;
        CountOfOpenSites++;
        if(row == 1){
            WQU.union(changeIndex(row,col),VirtualTop);
            BackupWQU.union(changeIndex(row,col),VirtualTop);
        }
        if(row == N){
            WQU.union(changeIndex(row,col),VirtualBottom);
        }
        //四个方向检查是否可以连接（连接opened点）
        if(returnTF_validate(row-1,col)&&isOpen(row-1,col)){
            WQU.union(changeIndex(row-1,col),changeIndex(row,col));
            BackupWQU.union(changeIndex(row-1,col),changeIndex(row,col));
        }
        if(returnTF_validate(row+1,col)&&isOpen(row+1,col)){
            WQU.union(changeIndex(row+1,col),changeIndex(row,col));
            BackupWQU.union(changeIndex(row+1,col),changeIndex(row,col));
        }
        if(returnTF_validate(row,col+1)&&isOpen(row,col+1)){
            WQU.union(changeIndex(row,col+1),changeIndex(row,col));
            BackupWQU.union(changeIndex(row,col+1),changeIndex(row,col));
        }
        if(returnTF_validate(row,col-1)&&isOpen(row,col-1)){
            WQU.union(changeIndex(row,col-1),changeIndex(row,col));
            BackupWQU.union(changeIndex(row,col-1),changeIndex(row,col));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        validate(row,col);
        return MonteCarloSquare[changeIndex(row,col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        validate(row,col);
        if(isOpen(row,col)){
            return BackupWQU.connected(changeIndex(row,col),VirtualTop);        //只要连接虚拟顶部就算full的点
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
        return CountOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates(){
        return WQU.connected(VirtualTop,VirtualBottom);
    }

    // test client (optional)
    public static void main(String[] args){

    }
}
