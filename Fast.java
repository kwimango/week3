/*************************************************************************
 * Name           : Kenneth Lim <kenneth.lim.cw@gmail.com>
 * Written        : 2014-07-17
 * Compilation    : javac Fast.java
 * Execution      :
 * Dependencies   : StdDraw.java
 *
 * Description: Fast algorithm for determing collinear points
 *              Alg4CS Week 3 coursework 
 * 
 *
 *************************************************************************/
import java.util.Arrays;

// unit test
public class Fast {
    
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
        //go through every point in the array
        for (int i = 0; i < N; i++)
        {
            //get the reference point p
            Point ref = points[i];
            //sort the rest of the array
            Arrays.sort(points, i, N, ref.SLOPE_ORDER);
            
            //System.out.print("SORTED ARRAY: ");
            //for (int c = 0; c < N; c++) {
            //    System.out.print(points[c] + ", ");
            //}  
            //System.out.println();
            
            for (int j = i+1; j < N; j++) {
                int step = 1;
                while (j + step < N) {                    
                    if (ref.SLOPE_ORDER.compare(points[j], points[j+step]) != 0)
                        break;
                    step++;
                }
                if (step >= 2)
                {
                    Point[] result  = new Point[step+1];
                    result[0] = points[i];
                    int counter = 1;
                    for (int t = j; t < j+step; t++) 
                    {
                        result[counter++] = points[t];
                    }
                    Arrays.sort(result);
                    //System.out.println("LENGTH = " + result.length);
                    for (int u = 0; u < result.length; u++) {
                        System.out.print(result[u]);
                        if (u < result.length-1) System.out.print(" -> ");
                        else         System.out.println();
                    }
                    result[0].drawTo(result[result.length-1]);
                }
                
            }
        }
    }
}
