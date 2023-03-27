package day11.dvidenconquer;
import java.io.*;
import java.util.*;

public class DivideTest {
	
	private static int callCnt1, callCnt2;
	
	private static long exp1(long x, long n) {
		callCnt1++;
		if(n == 1) return x;
		
		return x * exp1(x, n-1);
	}
	
	private static long exp2(long x, long n) {
		callCnt2++;
		if(n == 1) return x;
		long y= exp2(x, n/2);
		y *= y;
		
		//홀수는 N/2 * N/2 * 1해야하므로
		//ex) 2^9 = 2^4 * 2^4 * 2
		return n%2 == 0 ? y : y * x;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		exp1(2, 30);
		
		exp2(2, 30);
		System.out.println(callCnt1 + ", " + callCnt2);
		//30, 5가 출력됨 -> 눈에띄게 작음
	}
}
