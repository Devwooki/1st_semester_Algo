package com.ssafy.offline10;
import java.io.*;
import java.util.*;

public class BOJ2839 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		StringBuilder sb = new StringBuilder();
		int sugar = Integer.parseInt(br.readLine());
		
		int cnt = sugar%5;
		if(sugar % 5 == 0 && sugar % 3 == 0) {
			System.out.println(sugar/5);
		}
		else if(sugar == 4 || sugar == 7) System.out.println(-1);
		else {
			if(cnt == 1 || cnt == 3) System.out.println(sugar/5 + 1);
			else if(cnt == 2 || cnt == 4) System.out.println(sugar/5 + 2);
			else System.out.println(sugar/5);
		}
	}
}
