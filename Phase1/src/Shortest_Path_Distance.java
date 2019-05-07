import java.awt.Point;

import java.util.*;

public class Shortest_Path_Distance{
	
	protected static final double EPS = 1.0e-8;
	static int i = 0;
	private static ArrayList<double[]> list;


	private static int n, m, p, q;		
	static int counter = 0;
	
	
	  private static int INF=Integer.MAX_VALUE;     
      //dist[i][j]=INF<==>顶点i和j之间没有边     
	  private double[][] dist;     
      //顶点i 到 j的最短路径长度，初值是i到j的边的权重       
	  private double[][] path;       
	  private List<Double> result=new ArrayList<Double>();     
	
	
	public static void main(String[] args)  {
		
		list = new ArrayList<double[]>();
		Scanner sc = new Scanner(System.in);
	
		//read the n, m, p, q
		n = sc.nextInt();
	    m = sc.nextInt();
	    p = sc.nextInt();
	    q = sc.nextInt();


	    
	    //n points
	    Point[] points = new Point[1000];
	    
	    
	    
	    //m segments
	    Segment1[] segs = new Segment1[m];

	    //input the points
	    for(i=0; i<n; i++) {
	    	points[i] = new Point(sc.nextInt(), sc.nextInt());
	    }	    
	    
	    //input the number
	    int[] l =new int[m];
	    int[] k =new int[m];
	    for(i=0; i<m; i++) {
	    	l[i] = sc.nextInt()-1;
	    	k[i] = sc.nextInt()-1;
	    	segs[i] = new Segment1(points[l[i]], points[k[i]],l[i],k[i]); 
	    }
	    
	    //create the graphic
	    detect(segs);
	    double[][] matrix = new double[n+counter][n+counter];
	    
	    for(i=0; i<matrix.length; i++) {
	    	for(int j=0; j<matrix.length; j++) {
	    		matrix[i][j] = INF;
	    	}
	    }
	    Shortest_Path_Distance spd = new Shortest_Path_Distance(counter+n);
	    detect2(segs, matrix);
	    for(i=0; i<m; i++) {
	    	matrix[l[i]][k[i]] = points[l[i]].distance(points[k[i]]);
	    	
	    	matrix[k[i]][l[i]] = points[l[i]].distance(points[k[i]]);
	    }
	    
	    
	    //input the start point and the end point 
	    //also output the result in this loop
	    String f;
	    f =  sc.nextLine();
	    
	    for(i=0; i<q; i++) {
	    	f =  sc.nextLine();
	    	//System.out.println("sss:"+f);
	    	String[] tmp = getPoints(f, points);
	    	System.out.println("\n从" + Integer.parseInt(tmp[0]) + "到" + Integer.parseInt(tmp[1]) 
					+ "的距离是:" + spd.reslove(matrix, (Integer.parseInt(tmp[0])-1),(Integer.parseInt(tmp[1])-1)));

	    	//spd.findCheapestPath(Integer.parseInt(tmp[0])-1,Integer.parseInt(tmp[1])-1,matrix);     
	        //List<Double> list1=spd.result;     
	        //System.out.println(Integer.parseInt(tmp[0])+" to "+Integer.parseInt(tmp[1])+",the cheapest path is:");     	        
	        //System.out.println(list1.toString());     
	        //System.out.println(spd.dist[Integer.parseInt(tmp[0])-1][Integer.parseInt(tmp[1])-1]); 
	    //  System.out.println(spd.dist[Integer.parseInt(tmp[0])-1][Integer.parseInt(tmp[1])-1]);
	    }
	    //close sc
	    sc.close();   
	}
public double reslove(double[][] adjMat,int s,int t) {
		
		//判断参数是否正确
		if(s < 0 || t < 0 || s >=adjMat.length || t >= adjMat.length) {
			System.out.println("错误，顶点不在图中!");
			return INF;
		}
		
		//用来记录某个顶点是否已经完成遍历，即替代优先队列的"移出队列"动作
		boolean[] isVisited = new boolean[adjMat.length];
		//用于存储从s到各个点之间的最短路径长度
		double[] d = new double[adjMat.length]; 
		
		//初始化数据
		for(int i=0;i<adjMat.length;i++) {
			isVisited[i] = false;
			d[i] = INF;
		}
		d[s] = 0; //s到s的距离是0 
		isVisited[s] = true; //将s标记为已访问过的
 
		//尚未遍历的顶点数目，替代优先队列是否为空的判断即Queue.isEmpty()
		int unVisitedNum = adjMat.length;
		//用于表示当前所保存的子路径中权重最小的顶点的索引,对应优先队列中,默认是起点
		int index = s; 
		while(unVisitedNum > 0 && index != t) {
			double min = INF;
			for(int i=0;i<adjMat.length;i++) { //取到第i行的最小元素的索引
				if(min > d[i] && !isVisited[i]) {
					min = d[i];
					index = i;
				}
			}
			if(index == t || unVisitedNum == 0) {
				System.out.print(index); //打印最短路径
			} else {
				System.out.print(index + "=>"); //打印最短路径
			}
			for(int i=0;i<adjMat.length;i++) {
				if(d[index] + adjMat[index][i] < d[i]) {
					d[i] = d[index] + adjMat[index][i];
				}
			}
			unVisitedNum --;
			isVisited[index] = true;
		}
		return d[t];
	}


	/*
	public void findCheapestPath(double begin,double end,double[][] matrix){     
        floyd(matrix);     
        result.add(begin);     
        findPath(begin,end);     
        result.add(end);     
    }    
	public void findPath(double fff,double j){     
        double k= path[(int)fff][(int)j];     
        if(k==-1)return;     
        findPath(fff,k);   //递归  
        result.add(k);     
        findPath(k,j);     
    } 
	  public  void floyd(double[][] matrix){     
	        int size=matrix.length;     
	        //initialize dist and path     
	        for(int i=0;i<size;i++){     
	            for(int j=0;j<size;j++){     
	                path[i][j]=-1;     
	                dist[i][j]=matrix[i][j];     
	                System.out.println("The dist[i][j] is: "+dist[i][j]+"  i is:"+i+"  j is"+j);
	            }     
	        }     
	        for(int k=0;k<size;k++){     
	            for(int ggg=0;ggg<size;ggg++){     
	                for(int j=0;j<size;j++){     
	                    if(dist[ggg][k]!=INF&&     
	                        dist[k][j]!=INF&&     
	                        dist[ggg][k]+dist[k][j]<dist[ggg][j]){     
	                        dist[ggg][j]=dist[ggg][k]+dist[k][j];     
	                        path[ggg][j]=k;     
	                    }     
	                }     
	            }     
	        }     
	             
	    }  
	    */  
	  public Shortest_Path_Distance(int size){   //构造函数  
	        this.path=new double[size][size];     
	        this.dist=new double[size][size];     
	    }   
	
	public static void detect(Segment1[] segs) {
		double[] Intersection = new double[2];
		
		for(i=0; i<segs.length-1;i++) {
			 for(int j =i+1; j<segs.length; j++){
				Intersection = getIntersection(segs[i],segs[j]);
				if(Intersection[0]!=-1 &&Intersection[1]!=-1) {
					//System.out.printf("%.5f %.5f\n",Intersection[0],Intersection[1]);
					list.add((Intersection));
					counter++;
					//System.out.println("n is :"+n);
					//System.out.println("matrix is :"+matrix.length);
					
				}else{
			        //System.out.println("NA");
		        }				
			}
		}
		if(counter == 0) System.out.println("No intersection");
		//else Collections.sort(list, new myComparator());
		else Collections.sort(list, new myComparator());
		//for(double[] i : list) System.out.printf("%.5f %.5f\n",i[0],i[1]);
		//System.out.printf("%.5f %.5f\n",Intersection[0],Intersection[1]);
	}
	
	
	public static void detect2(Segment1[] segs, double[][] matrix) {
		double[] Intersection = new double[2];
		int cnt =0;
		for(i=0; i<segs.length-1;i++) {
			 for(int j =i+1; j<segs.length; j++){
				Intersection = getIntersection(segs[i],segs[j]);
				if(Intersection[0]!=-1 &&Intersection[1]!=-1) {
					cnt++;
					matrix[n+cnt-1][segs[i].pid] = getDistance(Intersection, segs[i].p);
					matrix[segs[i].pid][n+counter-1] = getDistance(Intersection, segs[i].p);
					matrix[n+cnt-1][segs[i].qid] = getDistance(Intersection, segs[i].q);
					matrix[segs[i].qid][n+counter-1] = getDistance(Intersection, segs[i].q);
					matrix[n+cnt-1][segs[j].pid] = getDistance(Intersection, segs[j].p);
					matrix[segs[j].pid][n+counter-1] = getDistance(Intersection, segs[j].p);
					matrix[n+cnt-1][segs[j].qid] = getDistance(Intersection, segs[j].q);
					matrix[segs[j].qid][n+counter-1] = getDistance(Intersection, segs[j].q);
					    
				}else{
			        //System.out.println("NA");
		        }				
			}
		}
	}
	
	public static double getDistance(double[] pt1,Point pt) {
		double res1 =0;
		res1 =Math.sqrt(Math.pow(pt1[0]-pt.getX(),2) + Math.pow(pt1[1]-pt.getY(),2));
		return res1;
	}
	
	public static double[] getIntersection(Segment1 a, Segment1 b) {
		double[] c = new double[2];
		c[0] = -1;
		c[1] = -1;
		//get the determinant
		double det = Math.abs((a.q.getX()-a.p.getX())*(b.p.getY()-b.q.getY())+
				(b.q.getX()-b.p.getX())*(a.q.getY()-a.p.getY()));
		//step1
		if( (det <= EPS) && (det >= EPS) ) {
		    return c;
		}
		
		//step2
		double s =((b.p.getY()-b.q.getY())*(b.p.getX()-a.p.getX())+
				(b.q.getX()-b.p.getX())*(b.p.getY()-a.p.getY()))/det;

		double t = ((a.p.getY()-a.q.getY())*(b.p.getX()-a.p.getX())+
				(a.q.getX()-a.p.getX())*(b.p.getY()-a.p.getY()))/det;

		//step3
		if((0<s && s<1) && (0<t && t<1)){
			//step4
			c[0] = a.p.getX()+(a.q.getX()-a.p.getX())*s;
			c[1] = a.p.getY()+(a.q.getY()-a.p.getY())*s;

			return c;
		}else{
			return c;
		}
	}
	
	
	/*
	public static double[] getNumber(String str, Point[] points) {
		double[] arr = new double[2];
		if(str.startsWith("C")) {
			str = str.substring(1);
			arr =  list.get(Integer.parseInt(str));	
			System.out.println("CCCCC");
		}
		else {
			arr[0]=points[Integer.parseInt(str)].getX(); 
			System.out.println("arr[0] is:"+arr[0]);
			arr[1]=points[Integer.parseInt(str)].getY(); 
			System.out.println("arr[1] is:"+arr[1]);
		}
		return arr;
	}
	*/
	
	
	public static String[] getPoints(String str, Point[] points){
		String[] temp;
		int ks;
		String delimeter = " ";  // 指定分割字符
	    temp = str.split(delimeter); // 分割字符串
	    if(temp[0].startsWith("C")) {
			ks = Integer.parseInt(temp[0].substring(1));
			temp[0] = String.valueOf(ks+n);
	    }
		if(temp[1].startsWith("C")) {
			ks = Integer.parseInt(temp[1].substring(1));
					temp[1] = String.valueOf(ks+n);
		}
	    //System.out.println("temp[0] is:"+temp[0]);
		
		//System.out.println("the str is"+temp[0]);
		//System.out.println("the temp1 is:"+temp[2]);
		//System.out.println("the sss number is:"+str1[1]);
		return temp;
	}	
	
}
class Segment1{
	  public Point p = new Point(0,0);
	  public Point q = new Point(0,0);
	  public int pid;
	  public int qid;
	  public Segment1(Point p, Point q, int pid, int qid){
	    this.p = p;
	    this.q = q;
	    this.pid = pid;
	    this.qid = qid;
	  }
}

