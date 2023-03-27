package com.ssafy.offline10;
import java.io.*;
import java.util.*;

public class BOJ3040 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int[] small = new int[9];
		int sum = 0;
		for(int i = 0 ; i < 9 ; ++i) {
			small[i] = Integer.parseInt(br.readLine());
			sum += small[i];
		}
		
		int filter1 = 0, filter2 = 0;
		out:
		for(int i = 0 ; i < 8 ; ++i) {
			for(int j = i +1 ; j < 9 ; ++j){
				
				int temp =sum; 
				temp -= (small[i] + small[j]);
				if(temp == 100) {
					filter1 = i;
					filter2 = j;
					break out;
				}
			}
		}
		
		for(int i = 0 ; i < 9 ; ++i) {
			if(i == filter1 || i== filter2) continue;
			System.out.println(small[i]);
		}
	}
}
