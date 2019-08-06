import java.util.Scanner;

public class generate1_1 {
	public static void main(String[] args) {
		int n = 2 + (int) (Math.random() * (198 + 1));
		int m = 1 + (int) (Math.random() * (100 + 1));
		int q=0;
		int p=0;
		System.out.print(n+" "+m+" "+p+" "+q+" "+"\n");
		int[] tmp1 = new int[2*n];
		Scanner sc = new Scanner(System.in);
		System.out.println("Input the max of x,y: \n");
		int ppp=sc.nextInt();
		for (int g = 0; g < tmp1.length; g++) {
			tmp1[g] = 0 + (int) (Math.random() * (ppp + 1));
			System.out.print(tmp1[g]+" ");
		}
		int[] tmp2 = new int[2*m];
		for (int h=0; h<tmp2.length;h++) {
			tmp2[h] = 2 + (int) (Math.random() * (n-1));
			System.out.print(tmp2[h]+" ");
		}
		System.out.print("\n");
	}
}
