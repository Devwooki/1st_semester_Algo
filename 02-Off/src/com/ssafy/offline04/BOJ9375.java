package com.ssafy.offline04;

import java.io.*;
import java.util.*;

public class BOJ9375 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc = 1 ; tc <= TC ; ++tc) {
			HashMap<String, Integer > map = new HashMap<>();
			int N = Integer.parseInt(br.readLine());
			
			
			for(int i = 0 ; i < N ; ++i) {
				st = new StringTokenizer(br.readLine());
				
				st.nextToken(); //옷 종류는 필요없음
				String category= st.nextToken().toString();
				
				map.put(category, map.getOrDefault(category, 0) +1 );
			}
			
			int result = 1;
			for(Integer list : map.values()) {
				result *= (list +1);
			}
			
			System.out.println((result -1));
	
		}
		
	}
}
