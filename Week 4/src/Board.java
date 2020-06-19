//Week 4 assignment

import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class Board {
    private final int[][] board;   //二维数组表示的板
    private final int N;     //板的宽度
    // create a board from an n-by-n array of tiles,
    // where tiles[col][row] = tile at (col, row)
    public Board(int[][] tiles){
        if (tiles == null){
            throw new IllegalArgumentException("tiles is not existed");
        }
        N = tiles.length;
        board = new int[N][N];
        for (int row = 0; row < N ; row++){
            for(int col = 0; col < N ; col++){
                board[row][col] = tiles[row][col];
            }
        }
    }

    // string representation of this board
    public String toString(){
        String result;
        result = N + "\n";
        for (int row = 0; row < N ; row++){
            for(int col = 0; col < N ; col++){
                result = result + board[row][col] + " ";
            }
            result = result + "\n";
        }
        return result;
    }

    // board dimension n
    public int dimension(){
        return N;
    }

    // number of tiles out of place
    public int hamming(){
        int hammingAmount = 0;
        for (int row = 0; row < N ; row++){
            for(int col = 0; col < N ; col++){
                if (board[row][col] != N * row + col + 1 && board[row][col] != 0){
                    hammingAmount++;
                }
            }
        }
        return hammingAmount;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan(){
        int manhattanSum = 0;
        for (int row = 0; row < N ; row++){
            for(int col = 0; col < N ; col++){
                if (board[row][col] != N * row + col + 1&& board[row][col] != 0){
                    int posX = 0 ;      //不在正确位置上的点的正确位置（列）
                    int posY = 0 ;      //不在正确位置上的点的正确位置（行）
                    posY = board[row][col] / N * N < board[row][col] ? board[row][col] / N  : board[row][col] / N - 1;  //三目运算推测点的行位置
                    posX = board[row][col] - posY * N - 1;
                    int manh = Math.abs(posY - row) + Math.abs(posX - col);
                    manhattanSum += manh;
                }
            }
        }
        return manhattanSum;
    }

    // is this board the goal board?
    public boolean isGoal(){
        for (int row = 0; row < N ; row++){
            for(int col = 0; col < N ; col++){
                if (board[row][col] != N * row + col + 1&& board[row][col] != 0){
                    return false;
                }
            }
        }
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y){
        if (y == null){
            return false;
        }
        if (y == this){
            return true;
        }
        if (! (y instanceof Board)){
            return false;
        }
        Board objBoard = (Board) y;
        if (objBoard.N != this.N){
            return false;
        }
        for (int row = 0; row < N ; row++){
            for(int col = 0; col < N ; col++){
                if (this.board[row][col] != objBoard.board[row][col]){
                   return false;
                }
            }
        }
        return true;
    }
    //复制棋盘
    private int[][] copy(){
        int [][] COPYTILE = new int[N][N];
        for (int row = 0; row < N ; row++){
            for(int col = 0; col < N ; col++){
                COPYTILE[row][col] = board[row][col];
            }
        }
        return COPYTILE;
    }
    //交换棋盘位置
    private Board swap(int Original_col, int Original_row,int New_col,int New_row){
//        int[][] TargetBoard = board;    //备份原棋盘,此方法备份原棋盘错误，会导致原棋盘也被改变，因为地址一样！！
        int[][] TargetBoard = copy();
        int aux = TargetBoard[Original_row][Original_col];
        TargetBoard[Original_row][Original_col] = TargetBoard[New_row][New_col];
        TargetBoard[New_row][New_col] = aux;
        Board SwapBoard = new Board(TargetBoard);
        return SwapBoard;
    }
    // all neighboring boards
    public Iterable<Board> neighbors(){
        ArrayList<Board> neighborlist = new ArrayList<>();      //因为ArrayList中实现了Iterator方法，故可以直接返回ArrayList类型
        for (int row = 0; row < N ; row++){
            for(int col = 0; col < N ; col++){
                if (board[row][col] == 0){          //找到空白区域
                    if( row > 0){               //上
                        Board upboard = swap(col,row,col,row - 1);
                        neighborlist.add(upboard);
                    }
                    if( row < N - 1){               //下
                        Board lowboard = swap(col,row,col,row + 1);
                        neighborlist.add(lowboard);
                    }
                    if( col > 0){               //左
                        Board leftboard = swap(col,row,col - 1,row);
                        neighborlist.add(leftboard);
                    }
                    if( col < N - 1){               //右
                        Board leftboard = swap(col,row,col + 1,row);
                        neighborlist.add(leftboard);
                    }
                }
            }
        }
        return neighborlist;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin(){
        int col = 0, row = 0;
        int col2 = 0, row2 = 1;
        //如果扫描到了空的板
        if(board[row][col] == 0){
            col = 1;
        }
        if(board[row2][col2] == 0){
            col2 = 1;
        }
        Board twinboard = swap(col,row,col2,row2);      //将原棋盘的一块交换位置
        return twinboard;
    }

    // unit testing (not graded)
    public static void main(String[] args){
        int [][]blocks = new int[3][3];

        blocks[0][0] = 8;
        blocks[0][1] = 1;
        blocks[0][2] = 3;

        blocks[1][0] = 4;
        blocks[1][1] = 0;
        blocks[1][2] = 2;

        blocks[2][0] = 7;
        blocks[2][1] = 6;
        blocks[2][2] = 5;
        Board board = new Board(blocks);


        System.out.println(board.manhattan());

    }
}
