/*************************************************************************
 * Name           : Kenneth Lim <kenneth.lim.cw@gmail.com>
 * Written        : 2014-07-16
 * Compilation    : javac Point.java
 * Execution      :
 * Dependencies   : StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *              Alg4CS Week 3 coursework 
 * 
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    /** 
     * compare points by slope
     */
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder();
    
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }
    
    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * slope between this point (x0, y0) and that point (x1, y1)
     * (y1 - y0) / (x1 - x0)
     * Treat the slope of a horizontal line segment as positive zero
     * Treat the slope of a vertical line segment as positive infinity
     * Yreat the slope of a degenerate line segment (between a point and itself
     * as negative infinity.
     */
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        //horizontal line segment
        if (that.y == this.y)
        {
            if (that.x == this.x) return Double.NEGATIVE_INFINITY; 
            else                  return 0.0;
        }
        else if (that.x == this.x)
            return Double.POSITIVE_INFINITY;
        else
            return (that.y - this.y)*1.0/(that.x - this.x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (this.y > that.y)
            return 1;
        else if (this.y < that.y)
            return -1;
        else //break tie using x-coordinates
        {
            if (this.x > that.x)
                return 1;
            else if (this.x < that.x)
                return -1;
            else
                return 0;
        }
    }
    
    /**
     * The SLOPE_ORDER comparator should compare points by the slopes they 
     * make with the invoking point (x0, y0). Formally, the point (x1, y1) is 
     * less than the point (x2, y2) if and only if the slope 
     * (y1 ? y0) / (x1 ? x0) is less than the slope 
     * (y2 ? y0) / (x2 ? x0). 
     * 
     * Treat horizontal, vertical, and degenerate line segments as in 
     * the slopeTo() method. 
     */
    private class SlopeOrder implements Comparator<Point> {
        public int compare(Point q1, Point q2)
        {
            double slope1 = slopeTo(q1);
            double slope2 = slopeTo(q2);
            if      (slope1 < slope2) return -1;
            else if (slope1 > slope2) return +1;
            else                      return  0;
        }
    }
    
    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }
    

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        Point origin = new Point(0, 0);
        Point q1 = new Point(10, 15);
        Point q2 = new Point(10, 20);
       
        System.out.println("Unit Test Start");
        StdDraw.setCanvasSize(400, 400);
        StdDraw.setXscale(0, 30);
        StdDraw.setYscale(0, 30);
        StdDraw.setPenRadius(.020);
        StdDraw.setPenColor(StdDraw.BLACK);
        origin.draw();
        StdDraw.setPenColor(StdDraw.RED);
        q1.draw();
        q2.draw();
        System.out.println("Unit Test COMPLETE");
    }
}
