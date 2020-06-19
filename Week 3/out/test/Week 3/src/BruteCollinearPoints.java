import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;

public class BruteCollinearPoints {
    private Point [] pointArr;
    private ArrayList<LineSegment> lineSegmentsArr;         //使用ArrayList是因为无法预知lineSegmentArr的长度，无法实例化
//    private LineSegment[] lineSegmentsArr;
//    private int numberOfSegments = 0;
    public BruteCollinearPoints(Point[] points){     // finds all line segments containing 4 points
        checkNullPoint(points);
        pointArr = new Point[points.length];      //实例化以防止空指针错误
        for(int i = 0; i < points.length; i++){
            pointArr[i] = points[i];     //拷贝数组内容（非地址），不能直接等于(引用相同的地址导致一个数组改变会引起另一个数组[构造函数的参数]改变)
        }
        checkRepeatedPoint(pointArr);
        lineSegmentsArr = new ArrayList<LineSegment>(); //实例化以防止空指针错误
        for (int i = 0; i < pointArr.length ; i++){             //暴力查找共线的四个点
            for(int j = i + 1; j < pointArr.length; j++){
                for(int k = j + 1; k < pointArr.length; k++){
                    if(pointArr[i].slopeTo(pointArr[j]) == pointArr[i].slopeTo(pointArr[k])){
                        for(int l = k + 1; l < pointArr.length ; l ++){
                            if(pointArr[i].slopeTo(pointArr[j]) == pointArr[i].slopeTo(pointArr[l])){
                                lineSegmentsArr.add(new LineSegment(pointArr[i],pointArr[l]));
//                                lineSegmentsArr[numberOfSegments] = new LineSegment(pointArr[i],pointArr[j]);
//                                numberOfSegments++;
                            }
                        }
                    }
                }
            }
        }
    }
    private void checkNullPoint(Point[] points){
        if (points == null) {
            throw new IllegalArgumentException("has null point!");
        }
        for (Point point : points) {
            if (point == null) {
                throw new IllegalArgumentException("has null element!");
            }
        }
    }
    private void checkRepeatedPoint(Point[] points){
        Merge.sort(points);
        for(int i = 0 ; i < points.length - 1; i ++){
            if(points[i].slopeTo(points[i + 1]) == Double.NEGATIVE_INFINITY){
                throw new IllegalArgumentException("has repeat points!");
            }
        }
    }
    public int numberOfSegments(){              //the number of line segments
        return lineSegmentsArr.size();
//        return numberOfSegments;
    }
    public LineSegment[] segments(){            //the line segments
        return lineSegmentsArr.toArray(new LineSegment[numberOfSegments()]);        //toArray的解释：https://www.cnblogs.com/goldenVip/p/5427182.html
//        return lineSegmentsArr;
    }
    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
