
public class generate1_3 {
	public static void main(String[] args) {
		int n = 2 + (int) (Math.random() * (10));
		int m = 1 + (int) (Math.random() * (20));
		int p=0;
		int q = 0 + (int) (Math.random() * (100));
		System.out.print(n+" "+m+" "+p+" "+q+" "+"\n");
		int[] tmp1 = new int[2*n];
		for (int g = 0; g < tmp1.length; g++) {
			tmp1[g] = 0 + (int) (Math.random() * (200));
			System.out.print(tmp1[g]+" ");
		}
		int[] tmp2 = new int[2*m];
		for (int h=0; h<tmp2.length;h++) {
			tmp2[h] = 2 + (int) (Math.random() * (100));
			System.out.print(tmp2[h]+" ");
		}
		System.out.print("\n");
		String[] tmp3 = new String[q];
		for (int h=0; h<tmp3.length;h++) {
			tmp3[h] = Integer.toString(1+ (int) (Math.random() * (n-1)))+" "+Integer.toString(1+ (int) (Math.random() * (n-1)))+" "+1;
			if(h%23==4) tmp3[h]="C"+(1+(h%4))+" "+Integer.toString(1+ (int) (Math.random() * (n-1)))+" "+1;

			System.out.print(tmp3[h]+"\n");
		}
		
		System.out.print("\n");
	}
}
