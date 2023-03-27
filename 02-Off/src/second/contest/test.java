package second.contest;

public class test {
	public static void main(String[] args) {
		//System.out.println(Math.toDegrees());
	      double radian1 = Math.atan2(2, 2); // 45 1사분면
	      double radian2 = Math.atan2(2, -2); // 135 2사분면
	      double radian3 = Math.atan2(-2, -2); // -135 3사분면
	      double radian4 = Math.atan2(-2, 2); // -45 4사분면
	      System.out.println(radian1 * 180 / Math.PI);
	      System.out.println(radian2 * 180 / Math.PI);
	      System.out.println(radian3 * 180 / Math.PI);
	      System.out.println(radian4 * 180 / Math.PI);
	      
	      radian1 = Math.atan(4/3); // 45 1사분면
//	      radian2 = Math.atan(2, -2); // 135 2사분면
//	      radian3 = Math.atan(-2, -2); // -135 3사분면
//	      radian4 = Math.atan(-2, 2); // -45 4사분면
	      System.out.println(radian1 * 180 / Math.PI);
	      
	      
	      double radian = Math.atan2(-2,-2);
	      
	      System.out.println("====================");
	      int width = 0 - 0;
	      int height = 0 - (-2);

	      radian1 = Math.atan2(height, width);
	      System.out.println(radian1 * 180 / Math.PI);
	}

}
