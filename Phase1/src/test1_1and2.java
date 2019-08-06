import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

public class test1_1and2 {
	static int counter;
	static Segment2[] seggg;
	static int xxx, yyy;
	static double[][] ress;

	public static void main(String[] args) {
		int size_of_the_result = 0;

		int n, m, p, q;
		Scanner sc = new Scanner(System.in);
		xxx = sc.nextInt();
		// read the n, m, p, q
		n = sc.nextInt();
		m = sc.nextInt();
		p = sc.nextInt();
		q = sc.nextInt();
		counter = m;
		// n points
		Point[] points = new Point[n];
		// m segments
		Segment2[] segs = new Segment2[m];
		seggg = segs;
		// input the points
		for (int i = 0; i < n; i++) {
			points[i] = new Point(sc.nextInt(), sc.nextInt());
		}
		int l, k;
		// input the number
		for (int i = 0; i < m; i++) {
			l = sc.nextInt() - 1;
			k = sc.nextInt() - 1;
			segs[i] = new Segment2(points[l], points[k]);
		}
		size_of_the_result = sc.nextInt();
		yyy = size_of_the_result;
		System.out.println(yyy);
		if (yyy > 0) {
			double[][] result = new double[size_of_the_result][2];
			ress = result;
			for (int i = 0; i < size_of_the_result; i++) {
				result[i][0] = Double.parseDouble(sc.next());
				System.out.println(result[i][0]);
				result[i][1] = Double.parseDouble(sc.next());
				System.out.println(result[i][1]);
			}
		}
		sc.close();

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				// �������ڶ���
				MyFrame frame = new MyFrame();
				// ��ʾ����
				frame.setVisible(true);
			}
		});
	}

	public static class MyFrame extends JFrame {
		public static final String TITLE = "Javaͼ�λ���";
		public static final int WIDTH = 1000;
		public static final int HEIGHT = 1000;
		public MyFrame() {
			super();
			initFrame();
		}

		private void initFrame() {
			// ���� ���ڱ��� �� ���ڴ�С
			setTitle(TITLE);
			setSize(WIDTH, HEIGHT);

			// ���ô��ڹرհ�ť��Ĭ�ϲ���(����ر�ʱ�˳�����)
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

			// �Ѵ���λ�����õ���Ļ������
			setLocationRelativeTo(null);

			// ���ô��ڵ��������
			MyPanel panel = new MyPanel(this);
			setContentPane(panel);
		}
	}

	public static class MyPanel extends JPanel {

		private MyFrame frame;

		public MyPanel(MyFrame frame) {
			super();
			this.frame = frame;
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			drawLine(g);
		}

		private void drawLine(Graphics g) {
			frame.setTitle("The figure");
			// ���� Graphics �ĸ���, ��Ҫ�ı� Graphics �Ĳ���,
			// �������ʹ�ø���, ����Ӱ�쵽 Graphics ԭ�е�����
			Graphics2D g2d = (Graphics2D) g.create();
			// �����
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			// ���û�����ɫ
			g2d.setColor(Color.RED);
			Segment2[] sss = seggg;
			int n = counter;
			double[][] kkk = ress;
			// 1. ��������߶�: ��(20, 50), ��(200, 50)
			for (int i = 0; i < n; i++) {
				g2d.drawLine(sss[i].p.x * xxx, sss[i].p.y * xxx, sss[i].q.x * xxx, sss[i].q.y * xxx);
			}
			if (yyy > 0) {
				for (int i = 0; i < yyy; i++) {
					System.out.println(yyy);
					g2d.fillRect((int) (kkk[i][0] * xxx), (int) (kkk[i][1] * xxx), 10, 10);
				}
			}
			// �Լ������ĸ�������Ҫ���ٵ�
			g2d.dispose();
		}
	}
}

class Segment2 {
	public Point p = new Point(0, 0);
	public Point q = new Point(0, 0);

	public Segment2(Point p, Point q) {
		this.p = p;
		this.q = q;
	}
}