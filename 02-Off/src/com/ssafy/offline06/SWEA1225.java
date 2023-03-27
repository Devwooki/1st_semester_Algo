package com.ssafy.offline06;
import java.io.*;
import java.util.*;

public class SWEA1225 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		for(int tc = 1 ; tc <= 1 ; ++tc) { 
			br.readLine();
			
			Queue<Integer> q = new ArrayDeque<>();
			st = new StringTokenizer(br.readLine());
			while(st.hasMoreTokens()) {
				q.offer(Integer.parseInt(st.nextToken()));
			}
			
			int cnt = 0;
			while(true) {
				int temp = q.poll();
				
				temp -= (cnt%5)+1;
				System.out.println((cnt%5)+1);
				cnt++;
				if(temp <= 0) {
					temp = 0;
					q.offer(temp);
					break;
				}
				q.offer(temp);
			}
			
			
			sb.append("#" + tc + " " );
			
			while(!q.isEmpty()) {
				sb.append(q.poll() + " ");
			}
			
			sb.append("\n");
			
		}
		System.out.println(sb);
		
	}
}
