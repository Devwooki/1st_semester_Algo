package com.ssafy.offline01;

public class Quiz01 {

	public static void main(String[] args) {
		System.out.println(solve1("korea", 0, ""));
		//k,o,r,e,a나오게 출력
		
		
		System.out.println(solve2(100, ""));
		//2진수로 나오게
		
		//System.out.println(solve3(4,"", 0));
		solve3(3,"");
//		for(int i = 0 ; i < (int)Math.pow(2, 3) ; ++i) {
//			System.out.println(String.format("%03d", Integer.parseInt(solve2(i, ""))));
//		}

	}
	
	private static String solve1(String str, int idx, String result) {
		if(idx == str.length()-1) return result + str.charAt(idx);
		return solve1(str, idx+1, result + str.charAt(idx)+",");
	}
	
	private static String solve2(int i, String string) {
//		if(i == 1 ) return new StringBuilder(string + "1").reverse().toString();
//		return solve2(i/2, string+(i%2));
		
		if(i == 1) return i+string;
		if(i == 0) return "0";
		return solve2(i/2, (i%2) + string);
	} 
	
	private static String solve3(int num, String string, int now) {
		if(now == (int)Math.pow(2, num)) return string;
		return solve3(num, string + String.format("%020d\n", Integer.parseInt(solve2(now, ""))).substring(20-num, 21), now+1);
	}

	private static void solve3(int num, String s) {
		if(num == 0) {
			System.out.println(s);
			return;
		}
		solve3(num-1, s+'0');
		solve3(num-1, s+'1');
	}
	
	
}
