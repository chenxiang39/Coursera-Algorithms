//Percolation (Week 1,Union-find Assignment)

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    // perform independent trials on an n-by-n grid
    private  double []percolationthreshold;
    private int N;
    private double meanV = 0;
    private double stddevV = 0;
    private double confoLo = 0;
    private double confoHi = 0;
    public PercolationStats(int n, int trials){
        valide(n,trials);
        percolationthreshold = new double[trials];
        for(int i = 0 ; i<trials;i++){
            Percolation test = new Percolation(n);
            while(!test.percolates()){
                int randomRow = StdRandom.uniform(1,n+1);
                int randomCol = StdRandom.uniform(1,n+1);
                test.open(randomRow,randomCol);
            }
            percolationthreshold[i] = (double) test.numberOfOpenSites()/(n*n);
        }
        meanV = StdStats.mean(percolationthreshold);
        stddevV = StdStats.stddev(percolationthreshold);
        confoHi = meanV + (1.96*stddevV)/Math.sqrt(trials);
        confoLo = meanV - (1.96*stddevV)/Math.sqrt(trials);
    }
    private void valide(int n,int T){
        if(n < 1 || T < 1){
            throw new IllegalArgumentException("error");
        }
    }
    // sample mean of percolation threshold
    public double mean(){
        return meanV;
    }

    // sample standard deviation of percolation threshold
    public double stddev(){
        return stddevV;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return confoLo;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return confoHi;
    }

    // test client (see below)
    public static void main(String[] args){
        int n = StdIn.readInt();
        int T = StdIn.readInt();
        PercolationStats test = new PercolationStats(n,T);
        StdOut.print("mean                         = "+test.mean()+"\n");
        StdOut.print("stddev                       = "+test.stddev()+"\n");
        StdOut.print("95% confidence interval      = ["+test.confidenceLo()+", "+test.confidenceHi()+"]"+"\n");
    }
}
