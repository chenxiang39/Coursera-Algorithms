import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Merge;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;


public class FastCollinearPoints {
    private Point [] pointArr;
    private ArrayList<LineSegment> lineSegmentsArr;         //使用ArrayList是因为无法预知lineSegmentArr的长度，无法实例化
    public FastCollinearPoints(Point[] points) {     // finds all line segments containing 4 or more points
        checkNullPoint(points);
        pointArr = new Point[points.length];      //实例化以防止空指针错误
        for(int i = 0; i < points.length; i++){
            pointArr[i] = points[i];     //拷贝数组内容（非地址），不能直接等于(引用相同的地址导致一个数组改变会引起另一个数组[构造函数的参数]改变)
        }
        checkRepeatedPoint(pointArr);
        lineSegmentsArr = new ArrayList<LineSegment>(); //实例化以防止空指针错误
        Arrays.sort(pointArr);   //将点按坐标大小排序(以便排除重复线段)
        for(int i = 0; i < pointArr.length; i ++){
            Point [] AftPointArr = new Point[pointArr.length - 1 - i];  //在被选中点之后的点的集合
            Point [] PrePointArr = new Point[i];;     //在被选中点之前的点的集合
            for(int j = 0; j < i; j++){
                PrePointArr[j] = pointArr[j];
            }
            for(int j = 0; j < pointArr.length - i - 1; j++){
                AftPointArr[j] = pointArr[i + j + 1 ];
            }
            AddSegment(PrePointArr, AftPointArr, pointArr[i]);
        }
    }
    private boolean IsSubSegment(Double PossibleSlope,Double[] AuxPreSlopeArr_Sorted){  //二分查找
        int lo = 0;
        int hi = AuxPreSlopeArr_Sorted.length - 1;
        while (lo <= hi){
            int mid = lo + (hi - lo)/2;
            if(PossibleSlope < AuxPreSlopeArr_Sorted[mid]){
                hi = mid - 1;
            }
            else if(PossibleSlope > AuxPreSlopeArr_Sorted[mid]){
                lo = mid + 1;
            }
            else {
                return true;
            }
        }
        return false;
    }
    private void AddSegment(Point[] PrePointArr,Point[] AftPointArr,Point ChosedPoint){
        Point [] AuxAftPointArr = new Point[AftPointArr.length]; //备份在被选中的点后面的点的集合
        Double [] PreSlopedArr = new Double[PrePointArr.length]; //在被选中的点与前面的点的斜率的集合
        for(int i = 0 ; i < AftPointArr.length; i++){
            AuxAftPointArr[i] = AftPointArr[i];
        }
        Arrays.sort(AuxAftPointArr,ChosedPoint.slopeOrder());       //让后面的点按与被选中的点的斜率大小排序
        for(int i = 0 ; i < PrePointArr.length; i++){
            PreSlopedArr[i] = ChosedPoint.slopeTo(PrePointArr[i]);
        }
        Merge.sort(PreSlopedArr); //对斜率进行排序
        //检查后面是否有共线的点
        int head = 0; //设置头节点
        int tail = 1; // 设置尾节点
        while(head < AuxAftPointArr.length){
            //尾节点扫描后面与头节点的斜率是否相同
            while(tail < AuxAftPointArr.length && ChosedPoint.slopeTo(AuxAftPointArr[head]) == ChosedPoint.slopeTo(AuxAftPointArr[tail])){
                tail++;
            }
            if(tail - head >= 3){
                if(! IsSubSegment(ChosedPoint.slopeTo(AuxAftPointArr[head]), PreSlopedArr)){
                    lineSegmentsArr.add(new LineSegment(ChosedPoint,AuxAftPointArr[tail - 1]));
                }
            }
            head = tail;
            tail++;
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
    public int numberOfSegments() {        // the number of line segments
        return lineSegmentsArr.size();
    }
    public LineSegment[] segments(){
        return lineSegmentsArr.toArray(new LineSegment[numberOfSegments()]);
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}
