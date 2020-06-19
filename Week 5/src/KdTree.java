import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree {
    private int size;   //Node amount
    private Node root;  //Node root
    public KdTree(){
        size = 0;
        root = null;
    }
    private void volidateNull(Object obj){
        if(obj == null){
            throw new IllegalArgumentException("Argument is null!");
        }
    }
    // is the set empty?
    public boolean isEmpty(){
        return size == 0;
    }
    // number of points in the set
    public int size(){
        return size;
    }
    // add the point to the set (if it is not already in the set)

    public void insert(Point2D p) {
        volidateNull(p);
        if(root == null){
            root = new Node(p,null,1);
            size++;
        }
        else {
            if(contains(p)){    //重复插入
                return;
            }
            int parentHeight = 1;  // 遍历点插入的层数(父节点所在的层数),根据插入的层数决定是横线还是竖线
            Node parent = root;
            while(true){
                if(shouldSearchLeftNode(parent,p,parentHeight)){
                    if(parent.leftNode == null){
                        parent.leftNode = new Node(p,parent,parentHeight + 1);
                        size++;
                        break;
                    }
                    else {
                        parent = parent.leftNode;
                        parentHeight++;
                    }
                }
                else {
                    if(parent.rightNode == null){
                        parent.rightNode = new Node(p,parent,parentHeight + 1);
                        size++;
                        break;
                    }
                    else {
                        parent = parent.rightNode;
                        parentHeight++;
                    }
                }
            }
        }
    }
    private boolean shouldSearchLeftNode(Node parentNode,Point2D p,int parentHeight){
        if(parentHeight % 2 != 0){             //如果父节点在奇数层
            return parentNode.point.x() >= p.x();
        }
        else {
            return parentNode.point.y() >= p.y();
        }
    }
    // does the set contain point p?
    public boolean contains(Point2D p){
        volidateNull(p);
        if(root == null){
            return false;
        }
        else {
            Node target = root;
            int height = 1;     //从第一层开始搜索
            while (target != null){
                if(target.point.compareTo(p) == 0){
                    return true;
                }
                else if(shouldSearchLeftNode(target,p,height)){
                    target = target.leftNode;
                    height++;
                }
                else {
                    target = target.rightNode;
                    height++;
                }
            }
        }
        return false;
    }
    // draw all points to standard draw
    private void draw(Node Point){
        if(Point == null){
            return;
        }
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        Point.point.draw();
        StdDraw.setPenRadius();
        if(Point.height%2 == 0){
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.line(Point.rectSpace.xmin(),Point.point.y(),Point.rectSpace.xmax(),Point.point.y());
        }
        else {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.line(Point.point.x(),Point.rectSpace.ymin(),Point.point.x(),Point.rectSpace.ymax());
        }
//        StdDraw.pause(50);
        draw(Point.leftNode);
        draw(Point.rightNode);
    }
    public void draw(){
        draw(root);
    }
    //递归搜索
    private void pushToStack(Stack<Point2D> pointset, Node current, RectHV rect){
        if(!current.rectSpace.intersects(rect)){                 //不相交则不继续遍历
            return;
        }
        if(current.point.x() >= rect.xmin() && current.point.x() <= rect.xmax() && current.point.y() >= rect.ymin() && current.point.y() <= rect.ymax()){
            pointset.push(current.point);
        }
        if(current.leftNode != null){
            pushToStack(pointset,current.leftNode,rect);
        }
        if(current.rightNode != null){
            pushToStack(pointset, current.rightNode, rect);
        }
    }
    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV rect){
        volidateNull(rect);
        Stack<Point2D> pointset = new Stack<>();
        if(root != null){
            pushToStack(pointset,root,rect);
        }
        return pointset;
    }
    // a nearest neighbor in the set to point p; null if the set is empty
    private Node nearest(Node SearchPoint, Point2D TargetPoint, Node nearestNode){
        if(SearchPoint == null){
            return nearestNode;
        }
        double currentDis = SearchPoint.point.distanceSquaredTo(TargetPoint);       //防止负数出现
        double nearestDis = nearestNode.point.distanceSquaredTo(TargetPoint);
        if(currentDis <= nearestDis){
            nearestDis = currentDis;
            nearestNode = SearchPoint;
        }
        if(SearchPoint.rectSpace.distanceSquaredTo(TargetPoint) < nearestDis){             //查询点的距离离被搜点的长方形的距离 < 已知最短距离则进行搜索
            //如果搜索的点是竖线
            if (SearchPoint.height % 2 != 0){
                if(TargetPoint.x() < SearchPoint.point.x()){
                    if (SearchPoint.leftNode != null){
                        nearestNode = nearest(SearchPoint.leftNode,TargetPoint,nearestNode);
                    }
                    if (SearchPoint.rightNode != null){
                        nearestNode = nearest(SearchPoint.rightNode,TargetPoint,nearestNode);
                    }
                }
                else {
                    if (SearchPoint.rightNode != null){
                        nearestNode = nearest(SearchPoint.rightNode,TargetPoint,nearestNode);
                    }
                    if (SearchPoint.leftNode != null){
                        nearestNode = nearest(SearchPoint.leftNode,TargetPoint,nearestNode);
                    }
                }
            }
            else {
                if(TargetPoint.y() < SearchPoint.point.y()){
                    if (SearchPoint.leftNode != null){
                        nearestNode = nearest(SearchPoint.leftNode,TargetPoint,nearestNode);
                    }
                    if (SearchPoint.rightNode != null){
                        nearestNode = nearest(SearchPoint.rightNode,TargetPoint,nearestNode);
                    }
                }
                else {
                    if (SearchPoint.rightNode != null){
                        nearestNode = nearest(SearchPoint.rightNode,TargetPoint,nearestNode);
                    }
                    if (SearchPoint.leftNode != null){
                        nearestNode = nearest(SearchPoint.leftNode,TargetPoint,nearestNode);
                    }
                }
            }
        }
        return nearestNode;
    }
    public Point2D nearest(Point2D p){
        volidateNull(p);
        Node nearestPoint = null;
        if(root != null){
            nearestPoint = nearest(root,p,root);
        }
        if(nearestPoint == null){
            return null;
        }
        return nearestPoint.point;
    }
    private class Node{
        private Point2D point;
        private Node leftNode;
        private Node rightNode;
        private RectHV rectSpace;
        int height;
        private Node(Point2D point,Node parent,int height){
            volidateNull(point);
            this.point = point;
            leftNode = null;
            rightNode = null;
            this.height = height;       //奇数为竖，偶数为横
            rectSpace = getRectFromParent(point,parent);
        }
    }
    private RectHV getRectFromParent(Point2D current,Node parent){
        if(parent == null){
            return new RectHV(0,0,1,1);         //新的长方形
        }
        else {
            //父亲节点为竖
            if ((parent.height) % 2 != 0) {
                //父在左
                if (parent.point.x() < current.x()) {
                    return new RectHV(parent.point.x(), parent.rectSpace.ymin(), parent.rectSpace.xmax(), parent.rectSpace.ymax());
                } else {
                    return new RectHV(parent.rectSpace.xmin(), parent.rectSpace.ymin(), parent.point.x(), parent.rectSpace.ymax());
                }
            }
            else {
                if (parent.point.y() < current.y()) {
                    return new RectHV(parent.rectSpace.xmin(), parent.point.y(), parent.rectSpace.xmax(), parent.rectSpace.ymax());
                } else {
                    return new RectHV(parent.rectSpace.xmin(), parent.rectSpace.ymin(), parent.rectSpace.xmax(), parent.point.y());
                }
            }
        }
    }
    // unit testing of the methods (optional)
    public static void main(String[] args) {

    }
}
