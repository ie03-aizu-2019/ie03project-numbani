import java.awt.Point;
import java.util.*;

public class Shortest_Path_Distance {
	protected static final double EPS = 1.0e-8;
	static int i = 0;
	private static ArrayList<double[]> list;
	private static int n, m, p, q;
	static int counter = 0;
	private static int INF = Integer.MAX_VALUE;

	public static void main(String[] args) {
		list = new ArrayList<double[]>();
		Scanner sc = new Scanner(System.in);

		// read the n, m, p, q
		n = sc.nextInt();
		m = sc.nextInt();
		p = sc.nextInt();
		q = sc.nextInt();

		// n points
		Point[] points = new Point[1000];

		// m segments
		Segment1[] segs = new Segment1[m];

		// input the points
		for (i = 0; i < n; i++) {
			points[i] = new Point(sc.nextInt(), sc.nextInt());
		}

		// input the number
		int[] l = new int[m];
		int[] k = new int[m];
		for (i = 0; i < m; i++) {
			l[i] = sc.nextInt() - 1;
			k[i] = sc.nextInt() - 1;
			segs[i] = new Segment1(points[l[i]], points[k[i]], l[i], k[i]);
		}

		// create the graphic
		detect(segs);
		double[][] matrix = new double[n + counter][n + counter];
		Jcpoint[] jcpoints = new Jcpoint[list.size()];

		for (i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				matrix[i][j] = INF;
			}
		}
		for (i = 0; i < m; i++) {
			matrix[l[i]][k[i]] = points[l[i]].distance(points[k[i]]);
			matrix[k[i]][l[i]] = points[l[i]].distance(points[k[i]]);
		}

		jcpoints = changethematrix(segs, matrix, jcpoints);
		InsToMatrix(jcpoints, matrix);

		Shortest_Path_Distance spd = new Shortest_Path_Distance();

		// input the start point and the end point
		// also output the result in this loop
		String f;
		f = sc.nextLine();

		for (i = 0; i < q; i++) {
			f = sc.nextLine();
			String[] tmp = getPoints(f, points);
			if (tmp[0] == tmp[1])
				System.out.println("It is 0\n");
			else {
				double dnmd = spd.reslove(matrix, (Integer.parseInt(tmp[0]) - 1), (Integer.parseInt(tmp[1]) - 1));
				if (dnmd != INF)
					System.out.println(String.format("\n%.5f", dnmd));
			}
		}
		// close sc
		sc.close();
	}

	public double reslove(double[][] adjMat, int s, int t) {
		// �жϲ����Ƿ���ȷ
		if (s < 0 || t < 0 || s >= adjMat.length || t >= adjMat.length) {
			System.out.print("NA");
			return INF;
		}
		// ������¼ĳ�������Ƿ��Ѿ���ɱ�������������ȶ��е�"�Ƴ�����"����
		boolean[] isVisited = new boolean[adjMat.length];
		// ���ڴ洢��s��������֮������·������
		double[] d = new double[adjMat.length];
		int[] pre = new int[adjMat.length];
		for (int i = 0; i < pre.length; i++)
			pre[i] = i;
		// ��ʼ������
		for (int i = 0; i < adjMat.length; i++) {
			isVisited[i] = false;
			d[i] = INF;
		}
		d[s] = 0; // s��s�ľ�����0
		isVisited[s] = true; // ��s���Ϊ�ѷ��ʹ���

		// ��δ�����Ķ�����Ŀ��������ȶ����Ƿ�Ϊ�յ��жϼ�Queue.isEmpty()
		int unVisitedNum = adjMat.length;
		// ���ڱ�ʾ��ǰ���������·����Ȩ����С�Ķ��������,��Ӧ���ȶ�����,Ĭ�������
		int index = s;
		while (unVisitedNum > 0 && index != t) {
			double min = INF;
			for (int i = 0; i < adjMat.length; i++) { // ȡ����i�е���СԪ�ص�����
				if (min > d[i] && !isVisited[i]) {
					min = d[i];
					index = i;
				}
			}
			/*
			 * if(index == t || unVisitedNum == 0) { System.out.print(index); //��ӡ���·�� }
			 * else { System.out.print(index + "=>"); //��ӡ���·�� }
			 */
			for (int i = 0; i < adjMat.length; i++) {
				if (d[index] + adjMat[index][i] < d[i]) {
					d[i] = d[index] + adjMat[index][i];
					pre[i] = index;
				}
			}
			unVisitedNum--;
			isVisited[index] = true;
		}
		printPath(t, s, pre);
		return d[t];
	}

	void printPath(int v, int s, int[] pre) {
		if (v == s) {
			printtt(v);
			return;
		}
		printPath(pre[v], s, pre);
		printtt(v);
	}

	void printtt(int x) {
		if (x + 1 > n) {
			System.out.print("C" + (x - n + 1) + " ");
		} else
			System.out.print(x + 1 + " ");
	}

	public static boolean nottheendpoint(double[] Inters, Segment1[] segs) {
		double[] jiaodian = Inters;
		for (int i = 0; i < segs.length; i++) {
			if (jiaodian[0] == segs[i].p.x && jiaodian[1] == segs[i].p.y)
				return false;
			if (jiaodian[0] == segs[i].q.x && jiaodian[1] == segs[i].q.y)
				return false;
		}
		return true;
	}

	public static void detect(Segment1[] segs) {
		double[] Intersection = new double[2];
		for (i = 0; i < segs.length - 1; i++) {
			for (int j = i + 1; j < segs.length; j++) {
				Intersection = getIntersection(segs[i], segs[j]);
				if (Intersection[0] != -1 && Intersection[1] != -1) {
					if (nottheendpoint(Intersection, segs)) {
						list.add((Intersection));
						counter++;
					}
				} else {
					// System.out.println("NA");
				}
			}
		}
		if (counter == 0)
			System.out.println("No intersection");
		else {
		}
		Collections.sort(list, new myComparator());
	}

	public static Jcpoint[] changethematrix(Segment1[] segs, double[][] matrix, Jcpoint[] jcpoints) {
		double[] Intersection = new double[2];
		Jcpoint[] jc = new Jcpoint[jcpoints.length];
		int cnt = 0;
		for (i = 0; i < segs.length - 1; i++) {
			for (int j = i + 1; j < segs.length; j++) {
				Intersection = getIntersection(segs[i], segs[j]);
				if (Intersection[0] != -1 && Intersection[1] != -1) {
					jc[cnt] = new Jcpoint(cnt, i, j);
					cnt++;
					matrix[n + cnt - 1][segs[i].pid] = getDistance(Intersection, segs[i].p);
					matrix[segs[i].pid][n + cnt - 1] = getDistance(Intersection, segs[i].p);
					matrix[n + cnt - 1][segs[i].qid] = getDistance(Intersection, segs[i].q);
					matrix[segs[i].qid][n + cnt - 1] = getDistance(Intersection, segs[i].q);
					matrix[n + cnt - 1][segs[j].pid] = getDistance(Intersection, segs[j].p);
					matrix[segs[j].pid][n + cnt - 1] = getDistance(Intersection, segs[j].p);
					matrix[n + cnt - 1][segs[j].qid] = getDistance(Intersection, segs[j].q);
					matrix[segs[j].qid][n + cnt - 1] = getDistance(Intersection, segs[j].q);

				} else {
					// System.out.println("NA");
				}
			}
		}
		return jc;
	}

	public static void InsToMatrix(Jcpoint[] jcpoints, double[][] matrix) {
		Jcpoint[] jc = jcpoints;
		for (i = 0; i < jc.length - 1; i++) {
			for (int j = i + 1; j < jc.length; j++) {
				if (jc[i].seg1num == jc[j].seg1num || jc[i].seg2num == jc[j].seg2num) {
					double tmpresult = getDoubleDistance(list.get(i), list.get(j));
					matrix[n + i][n + j] = tmpresult;
					matrix[n + j][n + i] = tmpresult;
				}
			}
		}
	}

	public static double getDistance(double[] pt1, Point pt) {
		double res1 = 0;
		res1 = Math.sqrt(Math.pow(pt1[0] - pt.getX(), 2) + Math.pow(pt1[1] - pt.getY(), 2));
		return res1;
	}

	public static double getDoubleDistance(double[] pt1, double[] pt2) {
		double result = 0;
		result = Math.sqrt(Math.pow(pt1[0] - pt2[0], 2) + Math.pow(pt1[1] - pt2[1], 2));
		return result;
	}

	public static double[] getIntersection(Segment1 a, Segment1 b) {
		double[] c = new double[2];
		c[0] = -1;
		c[1] = -1;
		// get the determinant
		double det = Math.abs((a.q.getX() - a.p.getX()) * (b.p.getY() - b.q.getY())
				+ (b.q.getX() - b.p.getX()) * (a.q.getY() - a.p.getY()));
		// step1
		if ((det <= EPS) && (det >= EPS)) {
			return c;
		}
		// step2
		double s = ((b.p.getY() - b.q.getY()) * (b.p.getX() - a.p.getX())
				+ (b.q.getX() - b.p.getX()) * (b.p.getY() - a.p.getY())) / det;

		double t = ((a.p.getY() - a.q.getY()) * (b.p.getX() - a.p.getX())
				+ (a.q.getX() - a.p.getX()) * (b.p.getY() - a.p.getY())) / det;
		// step3
		if ((0 < s && s < 1) && (0 < t && t < 1)) {
			// step4
			c[0] = a.p.getX() + (a.q.getX() - a.p.getX()) * s;
			c[1] = a.p.getY() + (a.q.getY() - a.p.getY()) * s;
			return c;
		} else {
			return c;
		}
	}

	public static String[] getPoints(String str, Point[] points) {
		String[] temp;
		int ks;
		String delimeter = " "; // ָ���ָ��ַ�
		temp = str.split(delimeter); // �ָ��ַ���
		if (temp[0].startsWith("C")) {
			ks = Integer.parseInt(temp[0].substring(1));
			temp[0] = String.valueOf(ks + n);
		}
		if (temp[1].startsWith("C")) {
			ks = Integer.parseInt(temp[1].substring(1));
			temp[1] = String.valueOf(ks + n);
		}
		return temp;
	}

}

class Segment1 {
	public Point p = new Point(0, 0);
	public Point q = new Point(0, 0);
	public int pid;
	public int qid;

	public Segment1(Point p, Point q, int pid, int qid) {
		this.p = p;
		this.q = q;
		this.pid = pid;
		this.qid = qid;
	}
}

class Jcpoint {
	public int pnum = -1;
	public int seg1num = -1;
	public int seg2num = -1;

	public Jcpoint(int ppp, int xxx, int zzz) {
		this.pnum = ppp;
		this.seg1num = xxx;
		this.seg2num = zzz;
	}
}
