import java.awt.Point;
import java.util.*;

public class Detection_of_intersection {
	protected static final double EPS = 1.0e-8;
	static int i = 0;
	static int counter=0;
	public static void main(String[] args) {
		
		int n, m, p, q;		
		Scanner sc = new Scanner(System.in);
	
		//read the n, m, p, q
		n = sc.nextInt();
	    m = sc.nextInt();
	    p = sc.nextInt();
	    q = sc.nextInt();

	    
	    //n points
	    Point[] points = new Point[n];
	    
	    //m segments
	    Segment[] segs = new Segment[m];
	    
	    //input the points

	    for(i=0; i<n; i++) {
	    	points[i] = new Point(sc.nextInt(), sc.nextInt());
	    }
	    int l,k;
	    //input the number
	    for(i=0; i<m; i++) {
	    	l = sc.nextInt()-1;
	    	k = sc.nextInt()-1;

	    	segs[i] = new Segment(points[l], points[k]); 
	    }
	    
	    //close sc
	    sc.close();
	    //System.out.println(points[4].getX());
	    
	    detect(segs);
	    
	}
	
	
	
	public static void detect(Segment[] segs) {
		double[] Intersection = new double[2];
		
		for(i=0; i<segs.length-1;i++) {
			 for(int j =i+1; j<segs.length; j++){
				Intersection = getIntersection(segs[i],segs[j]);
				if(Intersection[0]!=-1 &&Intersection[1]!=-1) {
					System.out.printf("%.5f %.5f\n",Intersection[0],Intersection[1]);
					counter++;
				}else{
			        System.out.println("NA");
		        }				
			}
		}
		 System.out.println("The number of the intersections is: "+ counter);
	}
	
	public static double[] getIntersection(Segment a, Segment b) {
		double[] c = new double[2];
		c[0] = -1;
		c[1] = -1;
		//get the determinant
		double det = Math.abs((a.q.getX()-a.p.getX())*(b.p.getY()-b.q.getY())+
				(b.q.getX()-b.p.getX())*(a.q.getY()-a.p.getY()));
		//System.out.println(det);
		//step1
		if( (det <= EPS) && (det >= EPS) ) {
		    return c;
		}
		
		//step2
		double s =((b.p.getY()-b.q.getY())*(b.p.getX()-a.p.getX())+
				(b.q.getX()-b.p.getX())*(b.p.getY()-a.p.getY()))/det;
		 
		//System.out.println(s);
		double t = ((a.p.getY()-a.q.getY())*(b.p.getX()-a.p.getX())+
				(a.q.getX()-a.p.getX())*(b.p.getY()-a.p.getY()))/det;
		//System.out.println(t);
		//step3
		if((0<s && s<1) && (0<t && t<1)){
			//step4
			c[0] = a.p.getX()+(a.q.getX()-a.p.getX())*s;
			c[1] = a.p.getY()+(a.q.getY()-a.p.getY())*s;
			//System.out.println(c[0]);
			//System.out.println(c[1]);
			return c;
		}else{
			return c;
		}
	}	
}


class Segment{
	  public Point p = new Point(0,0);
	  public Point q = new Point(0,0);

	  public Segment(Point p, Point q){
	    this.p = p;
	    this.q = q;
	  }
}