import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.Stack;

public class PointSET {
    // construct an empty set of points
    private SET<Point2D> pointset;
    public PointSET(){
        pointset = new SET<Point2D>();
    }
    private void volidateNull(Object obj){
        if(obj == null){
            throw new IllegalArgumentException("Argument is null!");
        }
    }
    // is the set empty?
    public boolean isEmpty(){
        return pointset.isEmpty();
    }
    // number of points in the set
    public int size(){
        return pointset.size();
    }
    // add the point to the set (if it is not already in the set)
    public void insert(Point2D p){
        volidateNull(p);
        pointset.add(p);
    }
    // does the set contain point p?
    public boolean contains(Point2D p){
        volidateNull(p);
        return pointset.contains(p);
    }
    // draw all points to standard draw
    public void draw(){
        for(Point2D p : pointset){
            p.draw();
        }
    }
    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect){
        volidateNull(rect);
        Stack<Point2D> pointCollection = new Stack<>();
        for(Point2D p: pointset){
            if(rect.contains(p)){
                pointCollection.push(p);
            }
        }
        return pointCollection;
    }
    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D p){
        volidateNull(p);
        Point2D nearestPoint = null;
        for (Point2D point: pointset)
        {
            if ((nearestPoint == null) || (p.distanceSquaredTo(point) < p.distanceSquaredTo(nearestPoint)))  // 遍历找到距离最小的点
            {
                nearestPoint = point;
            }
        }

        return nearestPoint;
    }
    // unit testing of the methods (optional)
    public static void main(String[] args){

    }
}