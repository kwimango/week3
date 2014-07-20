/*************************************************************************
 * Name           : Kenneth Lim <kenneth.lim.cw@gmail.com>
 * Written        : 2014-07-17
 * Compilation    : javac Brute.java
 * Execution      :
 * Dependencies   : StdDraw.java
 *
 * Description: Brute force method to determine collienar
 *              Alg4CS Week 3 coursework 
 * 
 *
 *************************************************************************/

import java.util.Arrays;

// unit test
public class Brute {
    
    // return 1 if match, 0 if do not match
    private static byte match(Point p, Point q, Point r, Point s)
    {
        if (p.slopeTo(q) == p.slopeTo(r))
            if (p.slopeTo(r) == p.slopeTo(s))
                return 1;
        return 0;

        
    }
        
    public static void main(String[] args) {
        In in = new In(args[0]);      // input file
        int N = in.readInt();         // N inputs
        
        Point[] points = new Point[N];

        int count = 0;
        StdDraw.setCanvasSize(400, 400);
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.setPenRadius(.010);
        StdDraw.setPenColor(StdDraw.BLACK);

        //read all the data into an array
        while (!in.isEmpty()) {
            int x = in.readInt();
            int y = in.readInt();
            points[count] = new Point(x, y);
            points[count].draw();
            count++;
        }
        
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(.001);
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int k = j+1; k < N; k++) {
                    for (int l = k+1; l < N; l++) {
                        if (match(points[i], points[j], points[k], points[l]) == 1)
                        {
                            Point[] result = new Point[4];
                            result[0] = points[i];
                            result[1] = points[j];
                            result[2] = points[k];
                            result[3] = points[l];
                            //apply insertion sort to sort the points
                            Arrays.sort(result);
                            Point max = result[0];
                            Point min = result[3];
                            
                            for (int s = 0; s < 4; s++) {
                                System.out.print(result[s]);
                                if (s < 3) System.out.print(" -> ");
                                else         System.out.println();
                            }
                            min.drawTo(max);
                        }
                    }
                }
            }
        }
    }
}